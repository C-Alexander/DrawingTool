package sample;

import com.esotericsoftware.minlog.Log;

import java.io.*;
import java.util.Properties;

/**
 * Created by jasve_29 on 13-Mar-17.
 */
public class PropertiesTool {
    static File f = new File(System.getProperty("user.home") + File.separator + "drawingtool.properties");
    static String connString = "jdbc:mysql://studmysql01.fhict.local/dbi349135";
    static String userName = "dbi349135";
    static String passWord = "test123";
    static InputStream is = null;
    static Properties properties;

    public static Properties getProperties() {
        if (properties == null) loadProperties();
    checkProperties();
    return properties;
    }

    private static void loadProperties() {
       properties = new Properties();
        try {
            is = new FileInputStream(f);
            properties.load(is);
        } catch (FileNotFoundException e) {
            Log.info("Serialization", "File not found. Creating new file..");
            createProperties();
        } catch (IOException e) {
            Log.error("Serialization", e);
        }
    }

    private static void checkProperties() {
        if (properties.getProperty("file") == null)
            properties.setProperty("file", System.getProperty("user.home") + File.separator + "drawing.drw");
        if (properties.getProperty("ConnectionString") == null)
            properties.setProperty("ConnectionString", connString);
        if (properties.getProperty("userName") == null)
            properties.setProperty("userName", userName);
        if (properties.getProperty("passWord") == null)
            properties.setProperty("passWord", passWord);
    }

    private static void createProperties() {
        checkProperties();
        saveProperties();
    }

    private static void saveProperties() {
        try {
            OutputStream os = new FileOutputStream(f);
            properties.store(os, "Drawing file");
        } catch (Exception e1) {
            Log.error("Serialization", "Can not create new file: " + e1.toString());
        }
    }
}

