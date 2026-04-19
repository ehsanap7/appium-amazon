package com.amazon.ios.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractIosAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(25);

    private final WebDriverWait wait;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"meTab\"")
    private WebElement meTab;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'home' OR name == 'meTab' OR name == 'cartTab' OR name == 'menuTab'")
    private List<WebElement> tabIcons;


    public BottomTabBarPage(IOSDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public BottomTabBarPage(IOSDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public List<WebElement> tabIcons() {
        return tabIcons;
    }

    public void tapProfileIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(meTab)).click();
    }
}
