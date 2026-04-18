package com.seamley.amazon.ios.factory;

import com.seamley.amazon.ios.pages.BottomTabBarPage;
import com.seamley.amazon.ios.pages.OrderPillsPage;
import com.seamley.amazon.ios.pages.OrdersRecentEmptyStatePage;
import io.appium.java_client.ios.IOSDriver;

import java.util.Objects;

public final class IosAmazonPageFactory {

    private final IOSDriver driver;

    public IosAmazonPageFactory(IOSDriver driver) {
        this.driver = Objects.requireNonNull(driver, "driver");
    }

    public static IosAmazonPageFactory create(IOSDriver driver) {
        return new IosAmazonPageFactory(driver);
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
