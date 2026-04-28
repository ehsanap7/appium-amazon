package com.amazon.common.factory;

import com.amazon.common.pages.BottomTabBarPage;
import com.amazon.common.pages.OrderPillsPage;
import com.amazon.common.pages.OrdersRecentEmptyStatePage;
import io.appium.java_client.AppiumDriver;

import java.util.Objects;

public final class AmazonPageFactory {

    private final AppiumDriver driver;

    public AmazonPageFactory(AppiumDriver driver) {
        this.driver = Objects.requireNonNull(driver, "driver");
    }

    public static AmazonPageFactory create(AppiumDriver driver) {
        return new AmazonPageFactory(driver);
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
