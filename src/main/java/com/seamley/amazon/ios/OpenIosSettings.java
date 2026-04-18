package com.seamley.amazon.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.URL;
import java.time.Duration;

public final class OpenIosSettings {

    private static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_IOS_PLATFORM_VERSION = "26.4";
    /** Amazon Shopping on iPhone (typical US App Store install); change if your store build uses another id. */
    private static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";

    public static void main(String[] args) throws Exception {
        String appiumUrl = cfg("appium.serverUrl", DEFAULT_APPIUM_URL, "APPIUM_SERVER_URL");
        String udid = cfg("ios.udid", "", "IOS_UDID", "UDID");
        String platformVersion = cfg("ios.platformVersion", DEFAULT_IOS_PLATFORM_VERSION, "IOS_PLATFORM_VERSION");
        String xcodeOrgId = cfg("ios.xcodeOrgId", "", "IOS_XCODE_ORG_ID", "TEAM_ID");
        String xcodeSigningId = cfg("ios.xcodeSigningId", "Apple Development", "IOS_XCODE_SIGNING_ID");

        if (udid.isEmpty()) {
            throw new IllegalStateException("Missing UDID. Set env UDID or IOS_UDID, or -Dios.udid (IntelliJ → Run → Environment variables).");
        }
        if (xcodeOrgId.isEmpty()) {
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
                .setUpdatedWdaBundleId(AMAZON_IOS_BUNDLE_ID);

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
            Thread.sleep(2000);
            String src = driver.getPageSource();
            System.out.println("Page source length: " + (src != null ? src.length() : 0));
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
