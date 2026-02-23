package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    private static final String logFilePath = "test-output/logs.txt";

    public static void writeLog(String message) {
        try {
            // Ensure timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logEntry = "[" + timestamp + "] " + message;

            // Append to log file
            try (FileWriter fw = new FileWriter(logFilePath, true)) {
                fw.write(logEntry + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Optional: start a new log file for each run
    public static void startNewLogFile() {
        try (FileWriter fw = new FileWriter(logFilePath, false)) {
            fw.write("=== New Test Run Started at " +
                     new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                     " ===\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
