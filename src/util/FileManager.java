package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Member: Abdulaziz (Leader)
 * Task: Implement universal file reading it writing logic for data persistence.
 */
public class FileManager {
    private static final String DATA_DIR = "data";

    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void saveToFile(String filename, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DATA_DIR, filename)))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + filename + " - " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filename) {
        List<String> data = new ArrayList<>();
        File file = new File(DATA_DIR, filename);
        if (!file.exists()) {
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + filename + " - " + e.getMessage());
        }
        return data;
    }
}
