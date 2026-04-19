package com.amazon.ios.context;

import com.amazon.ios.factory.IosAmazonPageFactory;
import com.amazon.ios.pages.BottomTabBarPage;
import com.amazon.ios.pages.OrderPillsPage;
import com.amazon.ios.pages.OrdersRecentEmptyStatePage;
import com.amazon.utils.AppiumUtils;
import com.amazon.utils.IosUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public final class ScenarioContext {

    private IOSDriver driver;
    private BottomTabBarPage bottomTabBar;
    private OrderPillsPage orderPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public static final String AMAZON_BUNDLE_ID = IosUtils.AMAZON_IOS_BUNDLE_ID;

    public void startSession() throws IOException {
        if (driver != null) {
            return;
        }
        XCUITestOptions options = IosUtils.buildAmazonSessionOptions();
        try {
            driver = IosUtils.newAmazonSessionOrThrow(options);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium URL" + e);
        }
        bringAmazonToForeground();
        IosAmazonPageFactory factory = IosAmazonPageFactory.create(driver);
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
            driver.activateApp(AMAZON_BUNDLE_ID);
        } catch (Exception ignored) {
        }
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.iOSNsPredicateString("name == \"meTab\"")));
    }

    public IOSDriver driver() {
        return driver;
    }

    public BottomTabBarPage bottomTabBar() {
        return bottomTabBar;
    }

    public OrderPillsPage browseMenuPills() {
        return orderPills;
    }

    public OrdersRecentEmptyStatePage ordersRecentEmptyState() {
        return ordersRecentEmptyState;
    }
}
