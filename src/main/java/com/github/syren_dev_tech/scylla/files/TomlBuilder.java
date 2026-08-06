package com.github.syren_dev_tech.scylla.files;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A robust TomlBuilder that supports Schema definition, nested tables, comments, and type safety.
 */
public class TomlBuilder {

    // --- Internal Data Structures ---

    enum TomlType {
        STRING, INTEGER, DOUBLE, BOOLEAN, TABLE
    }

    private static class TomlNode {
        String key;
        Object value;
        TomlType type;
        String description;
        Map<String, TomlNode> children = new LinkedHashMap<>();
        boolean isTable = false;

        TomlNode(String key, TomlType type) {
            this.key = key;
            this.type = type;
        }
    }

    private final TomlNode rootNode;
    private final TomlNode currentScope;
    private final String prefix;

    // Constructor for the root builder
    public TomlBuilder() {
        this.rootNode = new TomlNode("root", TomlType.TABLE);
        this.currentScope = this.rootNode;
        this.prefix = "";
    }

    // Private constructor used for creating sub-builders (nested tables)
    private TomlBuilder(TomlNode root, TomlNode currentScope, String prefix) {
        this.rootNode = root;
        this.currentScope = currentScope;
        this.prefix = prefix;
    }

    // --- Builder API (Writing) ---

    public TomlBuilder addString(String key, String value, String description) {
        return addValue(key, value, TomlType.STRING, description);
    }

    public TomlBuilder addInteger(String key, long value, String description) {
        return addValue(key, value, TomlType.INTEGER, description);
    }

    public TomlBuilder addDouble(String key, double value, String description) {
        return addValue(key, value, TomlType.DOUBLE, description);
    }

    public TomlBuilder addBoolean(String key, boolean value, String description) {
        return addValue(key, value, TomlType.BOOLEAN, description);
    }

    /**
     * Creates a nested table and returns a new builder scoped to that table.
     */
    public TomlBuilder addTable(String key, String description) {
        TomlNode newNode = new TomlNode(key, TomlType.TABLE);
        newNode.isTable = true;
        newNode.description = description;

        // Add the new node to the current scope
        currentScope.children.put(key, newNode);

        // Return a new builder pointing to this new node as the scope
        String newPrefix = prefix.isEmpty() ? key : prefix + "." + key;
        return new TomlBuilder(this.rootNode, newNode, newPrefix);
    }

    private TomlBuilder addValue(String key, Object value, TomlType type, String description) {
        TomlNode node = new TomlNode(key, type);
        node.value = value;
        node.description = description;
        currentScope.children.put(key, node);
        return this;
    }

    // --- Output Logic (Serialization) ---

    public String build() {
        StringBuilder sb = new StringBuilder();
        renderNode(sb, rootNode, 0, false);
        return sb.toString().trim();
    }

    private void renderNode(StringBuilder sb, TomlNode node, int indent, boolean isRoot) {
        String indentation = "  ".repeat(indent);

        for (TomlNode child : node.children.values()) {
            // 1. Handle Description as Comment
            if (child.description != null && !child.description.isEmpty()) {
                sb.append(indentation).append("# ").append(child.description).append("\n");
            }

            if (child.isTable) {
                // 2. Handle Table Headers [table_name]
                sb.append("\n").append(indentation).append("[").append(child.key).append("]\n");
                renderNode(sb, child, indent + 1, false);
            } else {
                // 3. Handle Key-Value Pairs
                sb.append(indentation).append(child.key).append(" = ");
                renderValue(sb, child.value, child.type);
                sb.append("\n");
            }

            // Add spacing after a description or node for readability
            if (child.description != null)
                sb.append("\n");
        }
    }

    private void renderValue(StringBuilder sb, Object value, TomlType type) {
        if (type == TomlType.STRING) {
            String str = value.toString().replace("\"", "\\\"");
            sb.append("\"").append(str).append("\"");

        } else {
            sb.append(value);
        }
    }

    // --- IO Operations ---

    public void saveToFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Path parent = path.getParent();

        // 1. Check if a parent directory exists in the path (e.g., "config/sub/file.toml")
        // 2. If it does, and that parent doesn't exist on disk, create all missing directories.
        if (parent != null && Files.notExists(parent)) {
            System.out.println("[IO] Creating missing directories: " + parent.toAbsolutePath());
            Files.createDirectories(parent);
        }

        // 3. Write the file content
        Files.write(path, build().getBytes());
        System.out.println("[IO] File successfully saved to: " + path.toAbsolutePath());
    }

    public static TomlBuilder loadFromFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new TomlBuilder().parse(content);
    }

    // --- Parser Logic (Simplified) ---

    public TomlBuilder parse(String content) {
        String[] lines = content.split("\n");
        TomlNode currentTable = this.rootNode;

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#"))
                continue;

            if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                String tableName = trimmed.substring(1, trimmed.length() - 1);
                TomlNode newNode = new TomlNode(tableName, TomlType.TABLE);
                newNode.isTable = true;
                rootNode.children.put(tableName, newNode); // Simplified: all tables added to root
                currentTable = newNode;
            } else if (trimmed.contains("=")) {
                String[] parts = trimmed.split("=", 2);
                String key = parts[0].trim();
                String valStr = parts[1].trim();

                TomlNode node = new TomlNode(key, TomlType.STRING);
                node.value = parseRawValue(valStr);
                currentTable.children.put(key, node);
            }
        }
        return this;
    }

    private Object parseRawValue(String val) {
        val = val.trim();
        if (val.startsWith("\"") && val.endsWith("\""))
            return val.substring(1, val.length() - 1);
        if (val.equalsIgnoreCase("true"))
            return true;
        if (val.equalsIgnoreCase("false"))
            return false;
        try {
            if (val.contains("."))
                return Double.parseDouble(val);
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            return val;
        }
    }

    // --- Main Method for Demonstration ---

    public static void main(String[] args) throws Exception {
        // 1. BUILDER MODE
        TomlBuilder builder = new TomlBuilder();

        builder.addString("app_name", "ConfigPro", "The name of the application").addInteger("version", 2, "Major version number").addBoolean("debug_mode", true, "Enable verbose logging");

        // Create a nested table
        TomlBuilder dbTable = builder.addTable("database", "Database settings");
        dbTable.addString("host", "127.0.0.1", "Host address").addInteger("port", 5432, "Connection port");

        // Output result
        String tomlOutput = builder.build();
        System.out.println("--- GENERATED TOML ---");
        System.out.println(tomlOutput);

        // 2. FILE MODE
        String filename = "config.toml";
        builder.saveToFile(filename);
        System.out.println("\nSaved to " + filename);

        // 3. LOADER MODE
        System.out.println("\n--- LOADING FROM FILE ---");
        TomlBuilder loaded = TomlBuilder.loadFromFile(filename);
        System.out.println(loaded.build());
    }
}

