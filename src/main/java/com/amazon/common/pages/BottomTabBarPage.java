package com.amazon.common.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(25);
    private final WebDriverWait wait;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Browse menu Tab 4 of 5\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"meTab\"")
    private WebElement meTab;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/bottom_tab_button_icon")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'home' OR name == 'meTab' OR name == 'cartTab' OR name == 'menuTab'")
    private List<WebElement> tabIcons;

    public BottomTabBarPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    public List<WebElement> tabIcons() {
        return tabIcons;
    }

    public void tapProfileIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(meTab)).click();
    }
}
