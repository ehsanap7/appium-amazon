package com.amazon.common.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPillsPage extends AbstractAmazonPage {

    private final WebDriverWait wait;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Orders\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"bac_yo\"")
    private WebElement orders;

    public OrderPillsPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void waitForOrderTabVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(orders));
    }

    public void tapOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(orders)).click();
    }
}
