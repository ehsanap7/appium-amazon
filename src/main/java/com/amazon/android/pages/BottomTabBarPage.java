package com.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractAmazonPage {

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(15);

    private final WebDriverWait wait;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[2]")
    private WebElement meTab;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/bottom_tab_button_icon")
    private List<WebElement> tabIcons;

    public BottomTabBarPage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public BottomTabBarPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public List<WebElement> tabIcons() {
        return tabIcons;
    }

    public void tapHamburgerMenuTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meTab)).click();
    }
}
