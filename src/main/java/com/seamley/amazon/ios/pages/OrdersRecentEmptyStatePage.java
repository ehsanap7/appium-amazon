package com.seamley.amazon.ios.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OrdersRecentEmptyStatePage extends AbstractIosAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    @iOSXCUITFindBy(
            iOSNsPredicate = "name == \"Purchase history\" AND label == \"Purchase history\" AND value == \"1\"")
    private WebElement purchaseHistory;

    public OrdersRecentEmptyStatePage(IOSDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrdersRecentEmptyStatePage(IOSDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public WebElement waitForPurchaseHistory() {
        return wait.until(ExpectedConditions.visibilityOf(purchaseHistory));
    }

    public void assertNoRecentOrdersMessageVisible() {
        WebElement el = waitForPurchaseHistory();
        Assert.assertTrue(el.isDisplayed(), "Expected Purchase history section on Orders");
    }
}
