package ua.edu.sumdu.j2se.dudynskyi.ui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigIO {

    private static final Logger logger = LoggerFactory.getLogger(ConfigIO.class);

    private ConfigIO() {
    }

    public static void write(TaskManagerConfig config, OutputStream out) {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(config);
            oos.flush();
        } catch (IOException e) {
            logger.error("Exception during serialization of TaskManagerConfig object", e);
        }
    }

    public static void read(TaskManagerConfig config, InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            TaskManagerConfig config1 = (TaskManagerConfig) ois.readObject();
            config.setLanguage(config1.getLanguage());
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception during deserialization of TaskManagerConfig object", e);
        }
    }

    public static void writeBinary(TaskManagerConfig config, File file) {
        try (BufferedOutputStream bos = new BufferedOutputStream(
                Files.newOutputStream(Paths.get(file.getPath())))
        ) {
            write(config, bos);
        } catch (IOException e) {
            logger.error("Exception during serialization of TaskManagerConfig object", e);
        }
    }

    public static void readBinary(TaskManagerConfig config, File file) {
        try (BufferedInputStream bis = new BufferedInputStream(
                Files.newInputStream(Paths.get(file.getPath())))
        ) {
            read(config, bis);
        } catch (IOException e) {
            logger.error("Exception during deserialization of TaskManagerConfig object", e);
        }
    }


    public static void write(TaskManagerConfig config, Writer out) {
        try (BufferedWriter bw = new BufferedWriter(out)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(config);
            bw.write(json);
            bw.flush();
        } catch (IOException e) {
            logger.error("Exception during serialization of TaskManagerConfig object", e);
        }
    }

    public static void read(TaskManagerConfig config, Reader in) {
        try (BufferedReader br = new BufferedReader(in)) {
            String str = "";
            StringBuilder builder = new StringBuilder(str);
            while (br.ready()) {
                builder.append(br.readLine());
            }
            String json = builder.toString();
            Gson gson = new GsonBuilder().create();
            TaskManagerConfig config1 = gson.fromJson(json, TaskManagerConfig.class);
            config.setLanguage(config1.getLanguage());

        } catch (IOException e) {
            logger.error("Exception during deserialization of TaskManagerConfig object", e);
        }
    }

    public static void writeText(TaskManagerConfig config, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()))) {
            write(config, writer);
        } catch (IOException e) {
            logger.error("Exception during serialization of TaskManagerConfig object", e);
        }
    }

    public static void readText(TaskManagerConfig config, File file) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            read(config, reader);
        } catch (IOException e) {
            logger.error("Exception during deserialization of TaskManagerConfig object", e);
        }
    }
}
