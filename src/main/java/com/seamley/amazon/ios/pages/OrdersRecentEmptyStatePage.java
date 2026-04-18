package com.seamley.amazon.ios.pages;

import com.seamley.amazon.ios.AmazonIosSelectors;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/**
 * Android scenario ends on empty-state copy; iOS matches {@link com.seamley.amazon.ios.OpenIosSettings}
 * (Purchase history section on Orders).
 */
public class OrdersRecentEmptyStatePage extends AbstractIosAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    public OrdersRecentEmptyStatePage(IOSDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrdersRecentEmptyStatePage(IOSDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public WebElement waitForPurchaseHistory() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(AmazonIosSelectors.PURCHASE_HISTORY));
    }

    public void assertNoRecentOrdersMessageVisible() {
        WebElement el = waitForPurchaseHistory();
        Assert.assertTrue(el.isDisplayed(), "Expected Purchase history section on Orders (OpenIosSettings parity)");
    }
}
