package com.amazon.common.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OrdersRecentEmptyStatePage extends AbstractAmazonPage {

    public static final String NO_RECENT_ORDERS_MESSAGE =
            "Looks like you haven't placed an order in the last 3 months.";

    private final WebDriverWait wait;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Looks like you haven't placed an order in the last 3 months.\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Purchase history\" AND label == \"Purchase history\" AND value == \"1\"")
    private WebElement emptyStateTargetElement;

    public OrdersRecentEmptyStatePage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement waitForEmptyStateElement() {
        return wait.until(ExpectedConditions.visibilityOf(emptyStateTargetElement));
    }

    public void assertNoRecentOrdersMessageVisible() {
        WebElement el = waitForEmptyStateElement();

        Assert.assertTrue(el.isDisplayed(), "Expected empty state element to be visible");

        String currentPlatform = driver.getCapabilities().getPlatformName().toString();
        if (currentPlatform.equalsIgnoreCase("android")) {
            Assert.assertEquals(el.getText(), NO_RECENT_ORDERS_MESSAGE, "Orders empty-state body text");
        }
    }
}
