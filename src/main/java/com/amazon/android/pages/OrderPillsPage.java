package com.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderPillsPage extends AbstractAmazonPage {

    private final WebDriverWait wait;

    @AndroidFindBy(accessibility = "Orders List item 1 of 4")
    private WebElement orders;

    public OrderPillsPage(AndroidDriver driver) {
        this(driver, Duration.ofSeconds(30));
    }

    public OrderPillsPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitForOrderTabVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(orders));
    }

    public void tapOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(orders)).click();
    }

}
