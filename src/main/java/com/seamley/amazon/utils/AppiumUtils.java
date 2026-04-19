package com.seamley.amazon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AppiumUtils {

    public static final String LOCAL_PROPERTIES_RESOURCE = "local.properties";

    protected AppiumUtils() {
    }

    public static Properties loadLocalProperties() throws IOException {
        return loadClasspathProperties(AppiumUtils.class, LOCAL_PROPERTIES_RESOURCE);
    }

    public static Properties loadClasspathProperties(Class<?> anchor, String file) throws IOException {
        Properties props = new Properties();
        try (InputStream in = anchor.getClassLoader().getResourceAsStream(file)) {
            if (in == null) {
                throw new IllegalStateException("Classpath resource not found: " + file);
            }
            props.load(in);
        }
        return props;
    }

    public static String cfg(String propKey, String fallback, String... envKeys) {
        String fromProp = System.getProperty(propKey);
        if (fromProp != null && !fromProp.isBlank()) {
            return fromProp.trim();
        }
        for (String k : envKeys) {
            String v = System.getenv(k);
            if (v != null && !v.isBlank()) {
                return v.trim();
            }
        }
        if (fallback != null && !fallback.isBlank()) {
            return fallback.trim();
        }
        return "";
    }

    public static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.isBlank()) {
                return v.trim();
            }
        }
        return "";
    }
}
