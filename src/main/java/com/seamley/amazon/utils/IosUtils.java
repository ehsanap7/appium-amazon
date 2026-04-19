package com.seamley.amazon.utils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import org.openqa.selenium.SessionNotCreatedException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public final class IosUtils extends AppiumUtils {

    public static final String AMAZON_IOS_BUNDLE_ID = "com.amazon.Amazon";
    public static final String IOS_UPDATED_WDA_BUNDLE_ID = "com.apple.Preferences";
    public static final String IOS_XCODE_SIGNING_ID = "Apple Development";


    private IosUtils() {
        super();
    }

    public static XCUITestOptions buildAmazonSessionOptions() {
        String udid = need("IOS_UDID");
        String xcodeOrgId = need("IOS_XCODE_ORG_ID");
        String platformVersion = need("IOS_PLATFORM_VERSION");

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
                .setUpdatedWdaBundleId(IOS_UPDATED_WDA_BUNDLE_ID);

        options.setXcodeCertificate(new XcodeCertificate(xcodeOrgId, IOS_XCODE_SIGNING_ID));
        options.setCapability("xcodeOrgId", xcodeOrgId);
        options.setCapability("xcodeSigningId", IOS_XCODE_SIGNING_ID);
        return options;
    }

    public static IOSDriver newAmazonSessionOrThrow(XCUITestOptions options) throws MalformedURLException {
        try {
            String appiumUrl = AppiumUtils.resolveAppiumServerUrl();
            return new IOSDriver(new URL(appiumUrl), options);
        } catch (SessionNotCreatedException e) {
            printSessionFailureHelp(e);
            throw e;
        }
    }

    public static void printSessionFailureHelp(SessionNotCreatedException e) {
        System.err.println("Error starting session: " + e.getMessage());
    }
}
