package com.seamley.amazon.utils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.SessionNotCreatedException;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * iOS session and diagnostics aligned with {@code OpenIosSettings} (XCUITest + {@code local.properties}).
 */
public final class IosUtils extends AppiumUtils {

    public static final String PROPERTIES_FILE = AppiumUtils.LOCAL_PROPERTIES_RESOURCE;
    public static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723/";
    public static final String DEFAULT_IOS_PLATFORM_VERSION = "26.4";
    public static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";
    public static final String DEFAULT_UPDATED_WDA_BUNDLE_ID = "com.seamley.amazon.WebDriverAgentRunner";
    public static final String DEFAULT_XCODE_SIGNING_ID = "Apple Development";

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
        String udid = requireProp(props, "UDID", "ios.udid");
        String xcodeOrgId = requireProp(props, "IOS_XCODE_ORG_ID", "ios.xcodeOrgId");
        String platformVersion = firstNonBlank(
                props.getProperty("IOS_PLATFORM_VERSION"),
                cfg("ios.platformVersion", DEFAULT_IOS_PLATFORM_VERSION, "IOS_PLATFORM_VERSION"));
        String wdaBundleId = firstNonBlank(
                props.getProperty("IOS_UPDATED_WDA_BUNDLE_ID"),
                cfg("ios.updatedWdaBundleId", "", "IOS_UPDATED_WDA_BUNDLE_ID"),
                DEFAULT_UPDATED_WDA_BUNDLE_ID);
        String signingId = firstNonBlank(
                props.getProperty("IOS_XCODE_SIGNING_ID"),
                cfg("ios.xcodeSigningId", DEFAULT_XCODE_SIGNING_ID, "IOS_XCODE_SIGNING_ID"));

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
                .setUpdatedWdaBundleId(wdaBundleId);

        options.setXcodeCertificate(new XcodeCertificate(xcodeOrgId, signingId));
        options.setCapability("xcodeOrgId", xcodeOrgId);
        options.setCapability("xcodeSigningId", signingId);
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
