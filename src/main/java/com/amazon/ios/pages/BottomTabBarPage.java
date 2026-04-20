package com.amazon.ios.pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BottomTabBarPage extends AbstractIosAmazonPage {

    private final WebDriverWait wait;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"meTab\"")
    private WebElement meTab;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'home' OR name == 'meTab' OR name == 'cartTab' OR name == 'menuTab'")
    private List<WebElement> tabIcons;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"searchTextField\"")
    private WebElement searchInputBox;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextView[`name == \"searchTextView\"`][1]")
    private WebElement searchInput;

    public BottomTabBarPage(IOSDriver driver) {
        this(driver, Duration.ofSeconds(25));
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

    public boolean searchInputBoxExist() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchInputBox));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void tapSearchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(searchInputBox)).click();
    }

    public void typeInSearchField(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        el.clear();
        el.sendKeys(text);
    }
}
