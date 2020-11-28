package com.auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiKey {
    public static String keyName;
    public static String keyValue;

    public static String getKeyName() throws IOException {
        Properties prop = new Properties();
        FileInputStream inputStream = new FileInputStream("resources/config.properties");
        prop.load(inputStream);

        String key = prop.getProperty("key");

        return key;
    }

    public static String getKeyValue() throws IOException {
        Properties prop = new Properties();
        FileInputStream inputStream = new FileInputStream("resources/config.properties");
        prop.load(inputStream);

        String value = prop.getProperty("value");

        return value;
    }

    static {
        try {
            keyName = getKeyName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            keyValue = getKeyValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
