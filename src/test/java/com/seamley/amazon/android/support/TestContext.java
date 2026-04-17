package com.seamley.amazon.android.support;

import com.seamley.amazon.android.factory.AmazonPageFactory;
import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.pages.BrowseMenuPillsPage;
import com.seamley.amazon.android.pages.OrdersRecentEmptyStatePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public final class TestContext {

    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_UDID = "2A041JEGR05120";
    private static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";
    private static final String APP_ACTIVITY = "com.amazon.mShop.home.HomeActivity";

    private AndroidDriver driver;
    private BottomTabBarPage bottomTabBar;
    private BrowseMenuPillsPage browseMenuPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public void startDriver() throws MalformedURLException {
        if (driver != null) {
            return;
        }
        String serverUrl = System.getProperty("appium.serverUrl", DEFAULT_APPIUM_SERVER_URL);
        String udid = System.getProperty("appium.udid", DEFAULT_UDID);

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid(udid)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setNoReset(true)
                .setFullReset(false);

        driver = new AndroidDriver(new URL(serverUrl), options);
        AmazonPageFactory factory = AmazonPageFactory.create(driver);
        bottomTabBar = factory.bottomTabBar();
        browseMenuPills = factory.browseMenuPills();
        ordersRecentEmptyState = factory.ordersRecentEmptyState();
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
                bottomTabBar = null;
                browseMenuPills = null;
                ordersRecentEmptyState = null;
            }
        }
    }

    public AndroidDriver driver() {
        return driver;
    }

    public BottomTabBarPage bottomTabBar() {
        return bottomTabBar;
    }

    public BrowseMenuPillsPage browseMenuPills() {
        return browseMenuPills;
    }

    public OrdersRecentEmptyStatePage ordersRecentEmptyState() {
        return ordersRecentEmptyState;
    }

    public String appPackage() {
        return APP_PACKAGE;
    }
}
