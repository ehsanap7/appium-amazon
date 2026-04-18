package com.seamley.amazon.ios.pages;

import com.seamley.amazon.ios.AmazonIosSelectors;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractIosAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(25);

    private final WebDriverWait wait;

    public BottomTabBarPage(IOSDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public BottomTabBarPage(IOSDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public List<WebElement> tabBarButtons() {
        return driver.findElements(AmazonIosSelectors.ME_TAB);
    }

    public void tapProfileIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(AmazonIosSelectors.ME_TAB)).click();
        wait.until(ExpectedConditions.elementToBeClickable(AmazonIosSelectors.ME_TAB)).click();
    }
}
