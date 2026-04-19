package com.seamley.amazon.utils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.SessionNotCreatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public final class IosUtils extends AppiumUtils {

    public static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723/";
    public static final String DEFAULT_IOS_PLATFORM_VERSION = "26.4";
    public static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";
    public static final String DEFAULT_XCODE_SIGNING_ID = "Apple Development";
    public static final String WDA_BUNDLE_ID = "com.apple.Preferences";

    private IosUtils() {
        super();
    }

    public static Properties loadAmazonLocalProperties() throws IOException {
        return loadLocalProperties();
    }

    public static String resolveAppiumServerUrl(Properties props) {
        return firstNonBlank(
                props.getProperty("APPIUM_SERVER_URL"),
                cfg("appium.serverUrl", DEFAULT_APPIUM_URL, "APPIUM_SERVER_URL"));
    }

    public static String requireProp(Properties props, String key, String systemKey) {
        String fromSys = System.getProperty(systemKey);
        if (fromSys != null && !fromSys.isBlank()) {
            return fromSys.trim();
        }
        String v = props.getProperty(key);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing '" + key + "' in local.properties (or -D" + systemKey + ").");
        }
        return v.trim();
    }

    /**
     * Same capability recipe as {@code OpenIosSettings}: XCUITest, Amazon bundle, WDA signing, no reset.
     */
    public static XCUITestOptions buildAmazonSessionOptions(Properties props) {
        String udid = requireProp(props, "IOS_UDID", "ios.udid");
        String xcodeOrgId = requireProp(props, "IOS_XCODE_ORG_ID", "ios.xcodeOrgId");
        String platformVersion = firstNonBlank(
                props.getProperty("IOS_PLATFORM_VERSION"),
                cfg("ios.platformVersion", DEFAULT_IOS_PLATFORM_VERSION, "IOS_PLATFORM_VERSION"));

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
                .setUpdatedWdaBundleId(WDA_BUNDLE_ID);

        options.setXcodeCertificate(new XcodeCertificate(xcodeOrgId, DEFAULT_XCODE_SIGNING_ID));
        options.setCapability("xcodeOrgId", xcodeOrgId);
        options.setCapability("xcodeSigningId", DEFAULT_XCODE_SIGNING_ID);
        return options;
    }

    public static IOSDriver newAmazonSessionOrThrow(java.net.URL appiumUrl, XCUITestOptions options) {
        try {
            return new IOSDriver(appiumUrl, options);
        } catch (SessionNotCreatedException e) {
            printSessionFailureHelp(e);
            throw e;
        }
    }

    public static void printSessionFailureHelp(SessionNotCreatedException e) {
        System.err.println("Error starting session: " + e.getMessage());
    }
}
