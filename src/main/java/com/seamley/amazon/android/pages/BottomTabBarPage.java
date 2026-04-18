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

    public WebElement tabIconAt(int zeroBasedIndex) {
        List<WebElement> icons = tabIcons();
        if (zeroBasedIndex < 0 || zeroBasedIndex >= icons.size()) {
            throw new IndexOutOfBoundsException(
                    "Tab index " + zeroBasedIndex + " but only " + icons.size() + " tab icon(s) found.");
        }
        return icons.get(zeroBasedIndex);
    }

    public void tapBrowseMenuTab() {
        tapByExactContentDescription();
    }

    private void tapByExactContentDescription() {
        try {
            By by = AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"" + BOTTOM_TAB_ICON_RESOURCE_ID + "\").description(\"" + BottomTabBarPage.BROWSE_MENU_TAB_CONTENT_DESCRIPTION + "\")");
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        } catch (Exception e1) {
            try {
                By by = By.xpath("//*[@resource-id='" + BOTTOM_TAB_ICON_RESOURCE_ID + "' and @content-desc='" + BottomTabBarPage.BROWSE_MENU_TAB_CONTENT_DESCRIPTION + "']");
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
            } catch (Exception e2) {
                tapByContentDescContains();
            }
        }
    }

    private void tapByContentDescContains() {
        try {
            By by = AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"" + BOTTOM_TAB_ICON_RESOURCE_ID + "\").descriptionContains(\"" + "Browse menu" + "\")");
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        } catch (Exception e) {
            By by = By.xpath("//*[@resource-id='" + BOTTOM_TAB_ICON_RESOURCE_ID + "' and contains(@content-desc,'" + "Browse menu" + "')]");
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        }
    }
}
