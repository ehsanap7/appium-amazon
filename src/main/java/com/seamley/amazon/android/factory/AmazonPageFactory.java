package com.seamley.amazon.android.factory;

import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.pages.OrderPillsPage;
import com.seamley.amazon.android.pages.OrdersRecentEmptyStatePage;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.Objects;

public final class AmazonPageFactory {

    private final AndroidDriver driver;

    public AmazonPageFactory(AndroidDriver driver) {
        this.driver = Objects.requireNonNull(driver, "driver");
    }

    public static AmazonPageFactory create(AndroidDriver driver) {
        return new AmazonPageFactory(driver);
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public BottomTabBarPage bottomTabBar() {
        return new BottomTabBarPage(driver);
    }

    public OrderPillsPage orderPills() {
        return new OrderPillsPage(driver);
    }

    public OrdersRecentEmptyStatePage ordersRecentEmptyState() {
        return new OrdersRecentEmptyStatePage(driver);
    }
}
