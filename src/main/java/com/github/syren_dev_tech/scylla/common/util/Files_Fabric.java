package com.github.syren_dev_tech.scylla.common.util;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Arrays;
import com.github.syren_dev_tech.scylla.common.ScyllaCommon;
import net.fabricmc.loader.api.FabricLoader;

public class Files_Fabric {

    public static final void write(String path, String name, String data, String ext) {
        Path p = getGameDir().resolve(path);
        final File dir = p.toFile();

        if (!dir.exists())
            dir.mkdirs();

        try {
            try (PrintWriter out = new PrintWriter(path + "/" + name + ext)) {
                out.write(data);
            }
        } catch (Exception err) {
            ScyllaCommon.LOGGER.error("Failed to write to file: {}/{}/{}", path, name, ext);
            ScyllaCommon.LOGGER.error(Arrays.toString(err.getStackTrace()));
        }
    }

    public static final void writeToSave(String path, String name, String data, String ext) {

        File[] saves = new File(getGameDir().resolve("saves").toString()).listFiles(File::isDirectory);

        for (File file : saves) {
            Path p = getGameDir().resolve(file.toString() + "/" + path);
            final File dir = p.toFile();

            if (!dir.exists())
                dir.mkdirs();

            try {
                try (PrintWriter out = new PrintWriter(p.toString() + "/" + name + ext)) {
                    out.write(data);
                }
            } catch (Exception err) {
                ScyllaCommon.LOGGER.error("Failed to write to file: {}/{}/{}", p, name, ext);
                ScyllaCommon.LOGGER.error(Arrays.toString(err.getStackTrace()));
            }
        }
    }


    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
