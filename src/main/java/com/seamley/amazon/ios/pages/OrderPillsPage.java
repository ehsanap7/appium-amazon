package com.seamley.amazon.ios.pages;

import com.seamley.amazon.ios.AmazonIosSelectors;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPillsPage extends AbstractIosAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(25);

    private final WebDriverWait wait;

    public OrderPillsPage(IOSDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrderPillsPage(IOSDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitForMenuTileRowVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(AmazonIosSelectors.ORDERS));
    }

    public void tapOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(AmazonIosSelectors.ORDERS)).click();
    }
}
