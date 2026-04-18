package com.seamley.amazon.android.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OrderPillsPage extends AbstractAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    public OrderPillsPage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrderPillsPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void tapOrders() {
        By by = AppiumBy.accessibilityId("Orders List item 1 of 4");
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
}
