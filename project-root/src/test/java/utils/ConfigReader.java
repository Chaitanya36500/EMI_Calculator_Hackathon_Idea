package utils;



import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;



public class ConfigReader {

    private static Properties prop;



    public static Properties getProperties() throws IOException {

        if (prop == null) {

            prop = new Properties();

            FileInputStream fis = new FileInputStream("config.properties");

            prop.load(fis);

        }

        return prop;

    }



    public static String getProperty(String key) throws IOException {

        return getProperties().getProperty(key);

    }

}