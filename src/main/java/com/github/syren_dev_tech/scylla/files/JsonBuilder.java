package com.github.syren_dev_tech.scylla.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A robust JsonBuilder that supports Schema Validation and Serialization.
 */
public class JsonBuilder {

    private final Map<String, Object> data = new LinkedHashMap<>();
    private JsonSchema schema;

    // --- Inner Class: Schema Definition ---
    public static class JsonSchema {
        private final Map<String, Class<?>> rules = new HashMap<>();

        /**
         * Adds a type constraint to a specific key. Supported types: String.class, Number.class, Boolean.class, List.class, Map.currentClass
         */
        public JsonSchema addRule(String path, Class<?> expectedType) {
            rules.put(path, expectedType);
            return this;
        }

        public void validate(Map<String, Object> data) throws IllegalArgumentException {
            for (Map.Entry<String, Class<?>> entry : rules.entrySet()) {
                String path = entry.getKey();
                Class<?> expectedType = entry.getValue();
                Object actualValue = getNestedValue(data, path);

                if (actualValue == null) {
                    throw new IllegalArgumentException("Missing required field: " + path);
                }

                // Check for Number compatibility (handles Integer/Double as Number)
                if (expectedType == Number.class && !(actualValue instanceof Number)) {
                    throw new IllegalArgumentException("Field '" + path + "' must be a Number.");
                } else if (!expectedType.isInstance(actualValue)) {
                    throw new IllegalArgumentException("Field '" + path + "' expected " + expectedType.getSimpleName() + " but got " + actualValue.getClass().getSimpleName());
                }
            }
        }

        private Object getNestedValue(Map<String, Object> data, String path) {
            String[] parts = path.split("\\.");
            Object current = data;
            for (String part : parts) {
                if (current instanceof Map) {
                    current = ((Map<?, ?>) current).get(part);
                } else {
                    return null;
                }
            }
            return current;
        }
    }

    // --- JsonBuilder Methods ---

    public JsonBuilder withSchema(JsonSchema schema) {
        this.schema = schema;
        return this;
    }

    /**
     * Adds a value to the JSON structure. Supports dot notation for nesting: "user.address.city"
     */
    @SuppressWarnings("unchecked")
    public JsonBuilder put(String path, Object value) {
        String[] parts = path.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < parts.length - 1; i++) {
            current = (Map<String, Object>) current.computeIfAbsent(parts[i], k -> new LinkedHashMap<String, Object>());
        }

        current.put(parts[parts.length - 1], value);
        return this;
    }

    public JsonBuilder putArray(String path, List<?> values) {
        return put(path, new ArrayList<>(values));
    }

    /**
     * Serializes the built object into a JSON String.
     */
    public String build() {
        if (schema != null) {
            schema.validate(data);
        }
        return serialize(data);
    }

    /**
     * Writes the JSON string to a file.
     */
    public void writeToFile(String filePath) throws IOException {
        Files.write(Paths.get(filePath), build().getBytes());
    }

    // --- Internal Serialization Engine ---

    private String serialize(Object obj) {
        if (obj == null)
            return "null";
        if (obj instanceof String)
            return "\"" + escape("\"") + "\"";
        if (obj instanceof Boolean || obj instanceof Number)
            return obj.toString();

        if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            StringBuilder sb = new StringBuilder("{");
            Iterator<? extends Map.Entry<?, ?>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<?, ?> entry = it.next();
                sb.append("\"").append(escape(entry.getKey().toString())).append("\":").append(serialize(entry.getValue()));
                if (it.hasNext())
                    sb.append(",");
            }
            return sb.append("}").toString();
        }

        if (obj instanceof Collection) {
            Collection<?> col = (Collection<?>) obj;
            StringBuilder sb = new StringBuilder("[");
            Iterator<?> it = col.iterator();
            while (it.hasNext()) {
                sb.append(serialize(it.next()));
                if (it.hasNext())
                    sb.append(",");
            }
            return sb.append("]").toString();
        }

        return "\"" + escape(obj.toString()) + "\"";
    }

    private String escape(String input) {
        return input.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // --- Main Method for Demonstration ---
    public static void main(String[] args) {
        try {
            // 1. Define Schema
            JsonSchema schema = new JsonSchema().addRule("id", Integer.class).addRule("user.name", String.class).addRule("user.active", Boolean.class).addRule("tags", List.class);

            // 2. Build JSON
            JsonBuilder builder = new JsonBuilder().withSchema(schema).put("id", 101).put("user.name", "John Doe").put("user.active", true).putArray("tags", Arrays.asList("java", "json", "builder"));

            // 3. Generate String
            String jsonOutput = builder.build();
            System.out.println("Generated JSON:\n" + jsonOutput);

            // 4. Write to File
            builder.writeToFile("output.json");
            System.out.println("\nJSON successfully written to output.json");

            // 5. Demonstrate Validation Error
            System.out.println("\nTesting validation error (wrong type for 'id')...");
            new JsonBuilder().withSchema(schema).put("id", "not-a-number") // This will trigger error
                    .build();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
