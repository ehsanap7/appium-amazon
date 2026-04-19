package com.seamley.amazon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AppiumUtils {

    public static final String LOCAL_PROPERTIES_RESOURCE = "local.properties";
    public static final Properties LOCAL_PROPERTIES;
    private static volatile String appiumServerUrlOverride;

    static {
        try {
            LOCAL_PROPERTIES = loadLocalProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String need(String key) {
        String v = LOCAL_PROPERTIES.getProperty(key);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing '" + key + "' in local.properties.");
        }
        return v.trim();
    }

    public static Properties loadLocalProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream in = AppiumUtils.class.getClassLoader().getResourceAsStream(LOCAL_PROPERTIES_RESOURCE)) {
            if (in == null) {
                throw new IllegalStateException("Classpath resource not found: " + LOCAL_PROPERTIES_RESOURCE);
            }
            props.load(in);
        }
        return props;
    }

    public static void setAppiumServerUrlOverride(String url) {
        appiumServerUrlOverride = url.trim();
    }

    public static void clearAppiumServerUrlOverride() {
        appiumServerUrlOverride = null;
    }

    public static String resolveAppiumServerUrl() {
        if (appiumServerUrlOverride != null && !appiumServerUrlOverride.isBlank()) {
            return appiumServerUrlOverride;
        }
        return need("APPIUM_SERVER_URL");
    }
}
