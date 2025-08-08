package com.globalsqa.banking.utils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties properties = new Properties();

    public static Properties loadProperties(String filePath) {
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found");
        } catch (IOException e) {
            throw new RuntimeException("Error while loading the properties");
        }
    }

    public static void updateProperties(String filePath) {
        File file = new File(filePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            savePropertiesWithoutTimestamp(properties, filePath);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found");
        } catch (IOException e) {
            throw new RuntimeException("Error while loading the properties");
        }
    }

    public static void savePropertiesWithoutTimestamp(Properties properties, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        writer.close();
    }
}
