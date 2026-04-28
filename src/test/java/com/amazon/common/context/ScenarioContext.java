package com.amazon.common.context;

import com.amazon.common.factory.AmazonPageFactory;
import com.amazon.common.pages.BottomTabBarPage;
import com.amazon.common.pages.OrderPillsPage;
import com.amazon.common.pages.OrdersRecentEmptyStatePage;
import com.amazon.utils.AndroidUtils;
import com.amazon.utils.IosUtils;
import com.amazon.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public final class ScenarioContext {

    private AppiumDriver driver;
    private BottomTabBarPage bottomTabBar;
    private OrderPillsPage orderPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public void startSession() throws IOException {
        if (driver != null) {
            return;
        }

        try {
            driver = AppiumUtils.createSession();
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium URL", e);
        }

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
        String appPackageOrBundle = getAppIdentifier();
        try {
            ((InteractsWithApps) driver).activateApp(appPackageOrBundle);
        } catch (Exception ignored) {
        }

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> isAppInForeground(appPackageOrBundle));
    }

    private String getAppIdentifier() {
        String platform = AppiumUtils.need("TARGET_PLATFORM").toLowerCase();
        return platform.equals("android") ? AndroidUtils.AMAZON_APP_PACKAGE : IosUtils.AMAZON_IOS_BUNDLE_ID;
    }

    private boolean isAppInForeground(String identifier) {
        String platform = AppiumUtils.need("TARGET_PLATFORM").toLowerCase();
        if (platform.equals("android")) {
            return identifier.equals(((io.appium.java_client.android.AndroidDriver) driver).getCurrentPackage());
        }
        return true;
    }

    public AppiumDriver driver() {
        return driver;
    }

    public BottomTabBarPage bottomTabBar() {
        return bottomTabBar;
    }

    public OrderPillsPage getOrderPills() {
        return orderPills;
    }

    public OrdersRecentEmptyStatePage ordersRecentEmptyState() {
        return ordersRecentEmptyState;
    }
}
