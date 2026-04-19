package com.seamley.amazon.android.context;

import com.seamley.amazon.android.factory.AmazonPageFactory;
import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.pages.OrderPillsPage;
import com.seamley.amazon.android.pages.OrdersRecentEmptyStatePage;
import com.seamley.amazon.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public final class ScenarioContext {

    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_UDID = "2A041JEGR05120";
    public static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";
    private static final String APP_ACTIVITY = "com.amazon.mShop.home.HomeActivity";

    private AndroidDriver driver;
    private BottomTabBarPage bottomTabBar;
    private OrderPillsPage orderPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public void startSession() throws IOException {
        if (driver != null) {
            return;
        }
        Properties local = AppiumUtils.loadLocalProperties();
        String serverUrl = AppiumUtils.cfg("appium.serverUrl", DEFAULT_APPIUM_SERVER_URL, "APPIUM_SERVER_URL");
        // -Dappium.udid / env ANDROID_UDID or UDID override; else local.properties ANDROID_UDID=…; else default.
        String udid = AppiumUtils.firstNonBlank(
                AppiumUtils.cfg("appium.udid", "", "ANDROID_UDID", "UDID"),
                local.getProperty("ANDROID_UDID"),
                DEFAULT_UDID);

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid(udid)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setNoReset(true)
                .setFullReset(false);

        driver = new AndroidDriver(new URL(serverUrl), options);
        bringAmazonToForeground();
        AmazonPageFactory factory = AmazonPageFactory.create(driver);
        bottomTabBar = factory.bottomTabBar();
        orderPills = factory.orderPills();
        ordersRecentEmptyState = factory.ordersRecentEmptyState();
    }

    public void endSession() {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } finally {
            driver = null;
            bottomTabBar = null;
            orderPills = null;
            ordersRecentEmptyState = null;
        }
    }

    public void bringAmazonToForeground() {
        if (driver == null) {
            return;
        }
        try {
            driver.activateApp(APP_PACKAGE);
        } catch (Exception ignored) {
        }
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> APP_PACKAGE.equals(((AndroidDriver) d).getCurrentPackage()));
    }

    public AndroidDriver driver() {
        return driver;
    }

    public BottomTabBarPage bottomTabBar() {
        return bottomTabBar;
    }

    public OrderPillsPage orderMenuPills() {
        return orderPills;
    }

    public OrdersRecentEmptyStatePage ordersRecentEmptyState() {
        return ordersRecentEmptyState;
    }
}
