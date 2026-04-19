package com.seamley.amazon.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public final class AndroidUtils extends AppiumUtils {

    public static final String AMAZON_APP_PACKAGE = "com.amazon.mShop.android.shopping";
    private static final String APP_ACTIVITY = "com.amazon.mShop.home.HomeActivity";

    private AndroidUtils() {
        super();
    }

    public static UiAutomator2Options buildAmazonSessionOptions() {
        String udid = need("ANDROID_UDID");
        return new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid(udid)
                .setAppPackage(AMAZON_APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setNoReset(true)
                .setFullReset(false);
    }

    public static AndroidDriver newAmazonSessionOrThrow(URL appiumUrl, UiAutomator2Options options) {
        return new AndroidDriver(appiumUrl, options);
    }
}
