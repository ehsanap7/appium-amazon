package com.seamley.amazon.utils;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Shared helpers for Android and iOS automation. Subclasses group platform-specific utilities.
 */
public abstract class AppiumUtils {

    protected AppiumUtils() {
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

    /** System property {@code propKey}, then env keys, then {@code fallback}. */
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

    public static boolean safeDisplayed(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
