package com.amazon.android.context;

import com.amazon.android.factory.AmazonPageFactory;
import com.amazon.android.pages.BottomTabBarPage;
import com.amazon.android.pages.OrderPillsPage;
import com.amazon.android.pages.OrdersRecentEmptyStatePage;
import com.amazon.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public final class ScenarioContext {

    public static final String APP_PACKAGE = AndroidUtils.AMAZON_APP_PACKAGE;

    private AndroidDriver driver;
    private BottomTabBarPage bottomTabBar;
    private OrderPillsPage orderPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public void startSession() throws IOException {
        if (driver != null) {
            return;
        }

        UiAutomator2Options options = AndroidUtils.buildAmazonSessionOptions();
        try {
            driver = AndroidUtils.newAmazonSessionOrThrow(options);
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
