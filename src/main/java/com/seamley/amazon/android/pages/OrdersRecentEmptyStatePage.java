package com.seamley.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OrdersRecentEmptyStatePage extends AbstractAmazonPage {

    public static final String NO_RECENT_ORDERS_MESSAGE =
            "Looks like you haven't placed an order in the last 3 months.";

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    public OrdersRecentEmptyStatePage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrdersRecentEmptyStatePage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    // haven''t — XPath string literal needs doubled apostrophe
    private static By noRecentOrdersTextView() {
        return By.xpath(
                "//android.widget.TextView[@text='Looks like you haven''t placed an order in the last 3 months.']");
    }

    public WebElement waitForNoRecentOrdersTextView() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecentOrdersTextView()));
    }

    public void assertNoRecentOrdersMessageVisible() {
        WebElement el = waitForNoRecentOrdersTextView();
        Assert.assertTrue(el.isDisplayed(), "Expected empty-recent-orders TextView to be visible");
        Assert.assertEquals(el.getText(), NO_RECENT_ORDERS_MESSAGE, "Orders empty-state body text");
    }
}
