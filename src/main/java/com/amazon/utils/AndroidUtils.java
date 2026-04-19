package com.amazon.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

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

    public static AndroidDriver newAmazonSessionOrThrow(UiAutomator2Options options) throws MalformedURLException {
        String appiumUrl = AppiumUtils.resolveAppiumServerUrl();
        return new AndroidDriver(new URL(appiumUrl), options);
    }
}
