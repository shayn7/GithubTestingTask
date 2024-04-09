package dev.naamad.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;

    public ApplicationProperties() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.out.println("Failed to load properties file: application.properties");
            }
        } catch (IOException e) {
            System.out.println("IOException occurred while loading properties file: " + e);
        }
    }

    public String readProperty(String keyName) {
        System.out.println("Reading property: " + keyName);
        return properties.getProperty(keyName, "There is no such key in the properties file");
    }
}
