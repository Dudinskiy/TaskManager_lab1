package ua.edu.sumdu.j2se.dudynskyi.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.edu.sumdu.j2se.dudynskyi.tasks.exceptions.TaskIOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class TaskIO {

    private TaskIO() {
    }

    public static void write(AbstractTaskList tasks, OutputStream out) throws TaskIOException {
        try (DataOutputStream dos = new DataOutputStream(out)) {
            dos.writeInt(tasks.taskAmount);
            for (Task task : tasks) {
                char[] title = task.getTitle().toCharArray();
                dos.writeInt(title.length);
                dos.writeChars(task.getTitle());
                dos.writeBoolean(task.isActive());
                dos.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    long start = dateToLong(task.getStartTime());
                    dos.writeLong(start);
                    long end = dateToLong(task.getEndTime());
                    dos.writeLong(end);
                } else {
                    long time = dateToLong(task.getTime());
                    dos.writeLong(time);
                }
            }
            dos.flush();
        } catch (IOException e) {
            throw new TaskIOException("Exception when serialization ArrayTaskList object", e);
        }
    }

    private static long dateToLong(LocalDateTime time) {
        ZonedDateTime timeZ = ZonedDateTime.of(time
                , ZoneId.systemDefault());
        return timeZ.toInstant().toEpochMilli();
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws TaskIOException {
        try (DataInputStream dis = new DataInputStream(in)) {
            int taskAmount = dis.readInt();
            for (int i = 0; i < taskAmount; i++) {
                Task task;
                int length = dis.readInt();
                char[] chars = new char[length];
                for (int j = 0; j < length; j++) {
                    chars[j] = dis.readChar();
                }
                String title = String.valueOf(chars);
                boolean active = dis.readBoolean();
                int repeatInterval = dis.readInt();
                if (repeatInterval > 0) {
                    long startMilli = dis.readLong();
                    LocalDateTime start = longToDate(startMilli);
                    long endMilli = dis.readLong();
                    LocalDateTime end = longToDate(endMilli);
                    task = new Task(title, start, end, repeatInterval);
                } else {
                    long timeMilli = dis.readLong();
                    LocalDateTime time = longToDate(timeMilli);
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new TaskIOException("Exception when deserialization ArrayTaskList object", e);
        }
    }

    private static LocalDateTime longToDate(long milli) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(milli)
                        , TimeZone.getDefault().toZoneId());
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws TaskIOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(
                Files.newOutputStream(Paths.get(file.getPath())))
        ) {
            write(tasks, bos);
        } catch (IOException e) {
            throw new TaskIOException("Exception when serialization ArrayTaskList object", e);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws TaskIOException {
        try (BufferedInputStream bis = new BufferedInputStream(
                Files.newInputStream(Paths.get(file.getPath())))) {
            read(tasks, bis);
        } catch (IOException e) {
            throw new TaskIOException("Exception when deserialization ArrayTaskList object", e);
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws TaskIOException {
        try (BufferedWriter bw = new BufferedWriter(out)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Task[] arr = tasks.toArray();

            String json = gson.toJson(arr);

            bw.write(json);
            bw.flush();
        } catch (IOException e) {
            throw new TaskIOException("Exception when serialization ArrayTaskList object", e);
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws TaskIOException {
        try (BufferedReader br = new BufferedReader(in)) {
            StringBuilder builder = new StringBuilder();
            while (br.ready()) {
                builder.append(br.readLine());
            }
            String json = builder.toString();
            Gson gson = new GsonBuilder().create();
            Task[] arr = gson.fromJson(json, Task[].class);

            for (Task task : arr) {
                tasks.add(task);
            }

        } catch (IOException e) {
            throw new TaskIOException("Exception when deserialization ArrayTaskList object", e);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws TaskIOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()))) {
            write(tasks, writer);
        } catch (IOException e) {
            throw new TaskIOException("Exception when serialization ArrayTaskList object", e);
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws TaskIOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            read(tasks, reader);
        } catch (IOException e) {
            throw new TaskIOException("Exception when deserialization ArrayTaskList object", e);
        }
    }
}
