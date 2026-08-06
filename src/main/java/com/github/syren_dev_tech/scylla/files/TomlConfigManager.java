package com.github.syren_dev_tech.scylla.files;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Interface for receiving updates when the TOML file changes.
 */
interface TomlChangeListener {
    void onChanged(TomlBuilder updatedBuilder);
}


/**
 * Manages a TOML configuration that can be watched for external changes.
 */
public class TomlConfigManager {
    private final Path configPath;
    private TomlBuilder currentBuilder;
    private final List<TomlChangeListener> listeners = new ArrayList<>();
    private final ExecutorService watcherExecutor = Executors.newSingleThreadExecutor();
    private boolean watching = false;

    // Private constructor: All logic flows through one place to avoid duplication
    private TomlConfigManager(String filePath, TomlBuilder fallback) throws IOException {
        this.configPath = Paths.get(filePath);

        if (fallback != null && Files.notExists(configPath)) {
            System.out.println("[Manager] File missing. Creating default at: " + configPath.toAbsolutePath());
            fallback.saveToFile(filePath);
        }

        // Now we just load the file (either the existing one or the one we just created)
        // This will throw an exception if the file is still missing and no fallback was provided.
        this.currentBuilder = TomlBuilder.loadFromFile(filePath);
    }

    public static TomlConfigManager load(String filePath, TomlBuilder builder) throws IOException {
        return new TomlConfigManager(filePath, builder);
    }

    public void addListener(TomlChangeListener listener) {
        listeners.add(listener);
    }

    public TomlBuilder getConfig() {
        return currentBuilder;
    }

    // --- (The rest of the Watcher logic remains exactly the same as previous answer) ---

    public void startWatching() {
        if (watching)
            return;
        watching = true;
        watcherExecutor.submit(() -> {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                Path directory = configPath.getParent();
                if (directory == null)
                    directory = Paths.get(".");
                directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                while (watching) {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        Path changedFile = (Path) event.context();
                        if (changedFile.getFileName().equals(configPath.getFileName())) {
                            Thread.sleep(100); // Brief pause for OS write lock
                            reloadConfig();
                        }
                    }
                    if (!key.reset())
                        break;
                }
            } catch (Exception e) {
                if (watching)
                    System.err.println("[Watcher] Error: " + e.getMessage());
            }
        });
    }

    private void reloadConfig() {
        try {
            System.out.println("\n[Watcher] Change detected! Reloading...");
            this.currentBuilder = TomlBuilder.loadFromFile(configPath.toString());
            for (TomlChangeListener listener : listeners) {
                listener.onChanged(this.currentBuilder);
            }
        } catch (IOException e) {
            System.err.println("[Watcher] Failed to reload: " + e.getMessage());
        }
    }

    public void stopWatching() {
        this.watching = false;
        this.watcherExecutor.shutdownNow();
    }
}
