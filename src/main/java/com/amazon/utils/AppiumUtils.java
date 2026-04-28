package com.amazon.utils;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
        String v = System.getProperty(key);

        if (v == null || v.isBlank()) {
            v = LOCAL_PROPERTIES.getProperty(key);
        }

        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing '" + key + "' in command line or local.properties.");
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


    public static AppiumDriver createSession() throws MalformedURLException {
        String platform = need("TARGET_PLATFORM").toLowerCase();

        if (platform.equals("android")) {
            return AndroidUtils.newAmazonSessionOrThrow(AndroidUtils.buildAmazonSessionOptions());
        } else if (platform.equals("ios")) {
            return IosUtils.newAmazonSessionOrThrow(IosUtils.buildAmazonSessionOptions());
        } else {
            throw new IllegalArgumentException("Unknown TARGET_PLATFORM specified: " + platform);
        }
    }
}