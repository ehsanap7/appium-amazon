package com.seamley.amazon.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public final class OpenIosSettings {

    private static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_IOS_PLATFORM_VERSION = "26.4";
    private static final String PROPERTIES_FILE = "local.properties";

    /**
     * Amazon Shopping on iPhone (typical US App Store install); change if your store build uses another id.
     */
    private static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";
    /**
     * Your signed WebDriverAgentRunner bundle id from Xcode (must differ from {@link #AMAZON_IOS_BUNDLE_ID}).
     */
    private static final String UPDATED_WDA_BUNDLE_ID = "com.seamley.amazon.WebDriverAgentRunner";
    private static final By ME_TAB = AppiumBy.iOSNsPredicateString("name == \"meTab\"");
    private static final By ORDERS = AppiumBy.iOSNsPredicateString("name == \"bac_yo\"");
    /**
     * Section header for Purchase history (from page source). Used to assert Orders screen loaded this block.
     */
    private static final By PURCHASE_HISTORY = AppiumBy.iOSNsPredicateString(
            "name == \"Purchase history\" AND label == \"Purchase history\" AND value == \"1\"");

    public static void main(String[] args) throws Exception {
        String appiumUrl = cfg("appium.serverUrl", DEFAULT_APPIUM_URL, "APPIUM_SERVER_URL");
        String platformVersion = cfg("ios.platformVersion", DEFAULT_IOS_PLATFORM_VERSION, "IOS_PLATFORM_VERSION");
        Properties props = new Properties();
        try (InputStream in = OpenIosSettings.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (in == null) {
                throw new IllegalStateException("Classpath resource not found: " + PROPERTIES_FILE);
            }
            props.load(in);
        }
        String udid = props.getProperty("UDID", null);
        String xcodeOrgId = props.getProperty("IOS_XCODE_ORG_ID", null);
        String xcodeSigningId = "Apple Development";

        if (udid == null) {
            throw new IllegalStateException("Missing UDID. Set env UDID or IOS_UDID, or -Dios.udid (IntelliJ → Run → Environment variables).");
        }
        if (xcodeOrgId == null) {
            throw new IllegalStateException(
                    "Missing Team ID. Set env IOS_XCODE_ORG_ID or TEAM_ID, or -Dios.xcodeOrgId.");
        }

        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setAutomationName("XCUITest")
                .setUdid(udid)
                .setPlatformVersion(platformVersion)
                .setBundleId(AMAZON_IOS_BUNDLE_ID)
                .setNoReset(true)
                .setFullReset(false)
                .setShowXcodeLog(true)
                .setAllowProvisioningDeviceRegistration(true)
                .setWdaLaunchTimeout(Duration.ofMinutes(5))
                .setUpdatedWdaBundleId(UPDATED_WDA_BUNDLE_ID);

        options.setXcodeCertificate(new XcodeCertificate(xcodeOrgId, xcodeSigningId));
        options.setCapability("xcodeOrgId", xcodeOrgId);
        options.setCapability("xcodeSigningId", xcodeSigningId);

        IOSDriver driver = null;
        try {
            try {
                driver = new IOSDriver(new URL(appiumUrl), options);
            } catch (SessionNotCreatedException e) {
                printSessionFailureHelp(e);
                throw e;
            }
            System.out.println("Session started.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            wait.until(ExpectedConditions.elementToBeClickable(ME_TAB)).click();
            System.out.println("Tapped meTab.");
            wait.until(ExpectedConditions.elementToBeClickable(ORDERS)).click();
            System.out.println("Tapped Orders");
            // Assert Purchase history is on the page: wait until that node exists (fails with timeout if not).
            wait.until(ExpectedConditions.presenceOfElementLocated(PURCHASE_HISTORY));
            System.out.println("Assert OK: Purchase history is on the page.");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static String cfg(String propKey, String fallback, String... envKeys) {
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

    private static void printSessionFailureHelp(SessionNotCreatedException e) {
        System.err.println("Error starting session: " + e.getMessage());
    }
}
