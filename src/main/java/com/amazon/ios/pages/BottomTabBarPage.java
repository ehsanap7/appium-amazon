package com.amazon.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BottomTabBarPage extends AbstractIosAmazonPage {

    private static final int ADD_TO_CART_MAX_SWIPES = 25;

    private static final By ADD_TO_CART_BUTTON = AppiumBy.iOSNsPredicateString(
            "type == 'XCUIElementTypeButton' AND (name == \"Add to cart\")");

    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

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
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
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

    public void tapFirstSearchResult(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.iOSNsPredicateString("name == \"" + text + "\"")));
        el.click();
    }

    public void addToCart() {
        scrollUntilAddToCartClickableThenTap();
    }

    private void scrollUntilAddToCartClickableThenTap() {
        for (int i = 0; i < BottomTabBarPage.ADD_TO_CART_MAX_SWIPES; i++) {
            List<WebElement> candidates = driver.findElements(ADD_TO_CART_BUTTON);
            for (WebElement el : candidates) {
                if (tryTapAddToCart(el)) {
                    return;
                }
            }
            swipeUpOnScreen();
            pauseBetweenGestures();
        }
        throw new NoSuchElementException(
                "Add to cart button did not show up after " + BottomTabBarPage.ADD_TO_CART_MAX_SWIPES + " upward swipes (check label or scroll area).");
    }

    private boolean tryTapAddToCart(WebElement el) {
        try {
            if (!el.isDisplayed()) {
                return false;
            }
            shortWait.until(ExpectedConditions.elementToBeClickable(el)).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void swipeUpOnScreen() {
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.70);
        int endY = (int) (size.getHeight() * 0.30);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(550), PointerInput.Origin.viewport(), x, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    private static void pauseBetweenGestures() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
