package com.seamley.amazon.android.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractAmazonPage {

    public static final String BOTTOM_TAB_ICON_RESOURCE_ID =
            "com.amazon.mShop.android.shopping:id/bottom_tab_button_icon";

    public static final String BROWSE_MENU_TAB_CONTENT_DESCRIPTION = "Browse menu Tab 4 of 5";

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(15);

    private final WebDriverWait wait;

    public BottomTabBarPage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public BottomTabBarPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public List<WebElement> tabIcons() {
        By by = By.xpath("//*[@resource-id='" + BOTTOM_TAB_ICON_RESOURCE_ID + "']");
        return driver.findElements(by);
    }

    public void tapHamburgerMenuTab() {
        By by = AppiumBy.xpath(
                "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
}
