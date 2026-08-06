package com.github.syren_dev_tech.scylla.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class MinecraftEnv {

    public static Path getGameDirectory() {
        // 1. Check for NeoForge / Modern Forge (FMLPaths)
        try {
            Class<?> fmlPathsClass = Class.forName("net.neoforged.fml.loading.FMLPaths");
            Object gameDirSupplier = fmlPathsClass.getField("GAMEDIR").get(null);
            return (Path) gameDirSupplier.getClass().getMethod("get").invoke(gameDirSupplier);
        } catch (Exception ignored) {
        }

        try {
            Class<?> fmlPathsClass = Class.forName("net.minecraftforge.fml.loading.FMLPaths");
            Object gameDirSupplier = fmlPathsClass.getField("GAMEDIR").get(null);
            return (Path) gameDirSupplier.getClass().getMethod("get").invoke(gameDirSupplier);
        } catch (Exception ignored) {
        }

        // 2. Check for Fabric / Quilt
        try {
            Class<?> fabricLoaderClass = Class.forName("net.fabricmc.loader.api.FabricLoader");
            Object fabricLoaderInstance = fabricLoaderClass.getMethod("getInstance").invoke(null);
            return (Path) fabricLoaderClass.getMethod("getGameDir").invoke(fabricLoaderInstance);
        } catch (Exception ignored) {
        }

        // 3. Fallback to Operating System Defaults / Working Directory
        String userDir = System.getProperty("user.dir");

        // In most production mod environments, user.dir is the game directory.
        // However, if run from a generic launcher or vanilla state, we check OS paths.
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String home = System.getProperty("user.home");

        if (os.contains("win")) {
            String appData = System.getenv("APPDATA");
            if (appData != null)
                return Paths.get(appData, ".minecraft");
        } else if (os.contains("mac")) {
            return Paths.get(home, "Library", "Application Support", "minecraft");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return Paths.get(home, ".minecraft");
        }

        return Paths.get(userDir);
    }
}
