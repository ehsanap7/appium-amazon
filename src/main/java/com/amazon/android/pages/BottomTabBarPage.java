package com.amazon.android.pages;

// Bottom nav + home search + PDP add-to-cart + cart tab (same role as iOS BottomTabBarPage).

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractAmazonPage {

    private final WebDriverWait wait;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[2]")
    private WebElement meTab;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[3]")
    private WebElement cartTabIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.amazon.mShop.android.shopping:id/chrome_search_hint_view\"]")
    private WebElement searchField;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/bottom_tab_button_icon")
    private List<WebElement> tabIcons;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Sponsored Ad - Bottle Warmer, Grownsy 8-in-1 Fast Baby Bottle Warmer with Timer, Safe for Breastmilk Nutrients, Accurate Temperature Control, with Defrost, Sterili-zing, Keep Warm, Heats Baby Food & Bottles\"]")
    private WebElement firstIcon;

    @AndroidFindBy(id = "add-to-cart-button")
    private WebElement addToCart;

    /**
     * Shown after the search chrome opens; used for typing (hint row is usually not editable).
     */
    private static final By SEARCH_EDIT_TEXT =
            AppiumBy.xpath("//android.widget.EditText[contains(@resource-id,\"search\") or contains(@resource-id,\"rs_search\")]");

    public BottomTabBarPage(AndroidDriver driver) {
        this(driver, Duration.ofSeconds(15));
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

    public void tapCartTab() {
        wait.until(ExpectedConditions.elementToBeClickable(cartTabIcon)).click();
    }

    public boolean searchInputBoxExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchField));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void tapSearchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(searchField)).click();
    }

    public void typeInSearchField(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_EDIT_TEXT));
        el.clear();
        el.sendKeys(text);
    }

    public void tapFirstSearchResult(String text) {
        By hit = AppiumBy.xpath(
                "//android.widget.Button[@content-desc=\"bottle warmer\"]\n");
        wait.until(ExpectedConditions.elementToBeClickable(hit)).click();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstIcon)).click();
    }
}
