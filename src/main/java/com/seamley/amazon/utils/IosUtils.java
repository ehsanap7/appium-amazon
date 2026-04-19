package com.seamley.amazon.utils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.SessionNotCreatedException;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public final class IosUtils extends AppiumUtils {

    public static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";

    private IosUtils() {
        super();
    }

    public static XCUITestOptions buildAmazonSessionOptions() {
        String udid = need("IOS_UDID");
        String xcodeOrgId = need("IOS_XCODE_ORG_ID");
        String platformVersion = need("IOS_PLATFORM_VERSION");
        String wdaBundleId = need("IOS_UPDATED_WDA_BUNDLE_ID");
        String signingId = need("IOS_XCODE_SIGNING_ID");

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
