package com.seamley.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BrowseMenuPillsPage extends AbstractAmazonPage {

    public static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";

    public static final String SCROLLED_HAMBURGER_VIEW_RESOURCE_ID =
            APP_PACKAGE + ":id/scrolled-hamburger-view";

    public static final String MENU_ITEM_TILE_SINGLE_LINE_CONTAINER =
            APP_PACKAGE + ":id/Menu-Item-Tile-Single-Line-Container";

    public static final String PILL_LISTS_RESOURCE_ID = APP_PACKAGE + ":id/image_menu_item_pill_wl";
    public static final String PILL_ORDERS_RESOURCE_ID = APP_PACKAGE + ":id/image_menu_item_pill_yo";
    public static final String PILL_BUY_AGAIN_RESOURCE_ID = APP_PACKAGE + ":id/image_menu_item_pill_bya";
    public static final String PILL_ACCOUNT_RESOURCE_ID = APP_PACKAGE + ":id/image_menu_item_pill_ya";

    public static final String DESC_LISTS = "Lists";
    public static final String DESC_ORDERS = "Orders";
    public static final String DESC_BUY_AGAIN = "Buy Again";
    public static final String DESC_ACCOUNT = "Account";

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    public BrowseMenuPillsPage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public BrowseMenuPillsPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitForMenuTileRowVisible() {
        wait.until(anyBrowseMenuLocatorPresent());
    }

    private static ExpectedCondition<Boolean> anyBrowseMenuLocatorPresent() {
        List<By> locators = Arrays.asList(
                byResourceId(SCROLLED_HAMBURGER_VIEW_RESOURCE_ID),
                By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"),
                underScrolledHamburger("Menu-Item-Tile-Single-Line-Container"),
                underScrolledHamburger("image_menu_item_pill_yo"),
                byResourceId(MENU_ITEM_TILE_SINGLE_LINE_CONTAINER),
                By.xpath("//*[contains(@resource-id,'Menu-Item-Tile-Single-Line-Container')]"),
                By.xpath("//*[contains(@resource-id,'Menu_Item_Tile')]"),
                byResourceId(PILL_ORDERS_RESOURCE_ID),
                By.xpath("//*[contains(@resource-id,'image_menu_item_pill_yo')]"),
                By.xpath("//*[contains(@resource-id,'image_menu_item_pill')]"),
                By.xpath("//*[@content-desc='" + DESC_ORDERS + "']"),
                By.xpath("//*[contains(@content-desc,'Orders') and contains(@resource-id,'pill')]")
        );
        return driver -> {
            for (By by : locators) {
                try {
                    if (!driver.findElements(by).isEmpty()) {
                        return true;
                    }
                } catch (Exception ignored) {
                }
            }
            return false;
        };
    }

    public WebElement scrolledHamburgerView() {
        waitForMenuTileRowVisible();
        List<By> tries = Arrays.asList(
                byResourceId(SCROLLED_HAMBURGER_VIEW_RESOURCE_ID),
                By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"));
        for (By by : tries) {
            try {
                List<WebElement> found = driver.findElements(by);
                if (!found.isEmpty()) {
                    return found.get(0);
                }
            } catch (Exception ignored) {
            }
        }
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]")));
    }

    public WebElement menuTileContainer() {
        waitForMenuTileRowVisible();
        List<By> tries = Arrays.asList(
                underScrolledHamburger("Menu-Item-Tile-Single-Line-Container"),
                byResourceId(MENU_ITEM_TILE_SINGLE_LINE_CONTAINER),
                By.xpath("//*[contains(@resource-id,'Menu-Item-Tile-Single-Line-Container')]"),
                By.xpath("//*[contains(@resource-id,'Menu-Item-Tile')]"));
        for (By by : tries) {
            try {
                List<WebElement> found = driver.findElements(by);
                if (!found.isEmpty()) {
                    return found.get(0);
                }
            } catch (Exception ignored) {
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("Browse menu tile container not found; pills may still work via tapOrders().");
    }

    public List<WebElement> pillElements() {
        return Arrays.asList(
                findPillElement(PILL_LISTS_RESOURCE_ID),
                findPillElement(PILL_ORDERS_RESOURCE_ID),
                findPillElement(PILL_BUY_AGAIN_RESOURCE_ID),
                findPillElement(PILL_ACCOUNT_RESOURCE_ID));
    }

    public WebElement pillLists() {
        return findPillElement(PILL_LISTS_RESOURCE_ID);
    }

    public WebElement pillOrders() {
        return findPillElement(PILL_ORDERS_RESOURCE_ID);
    }

    public WebElement pillBuyAgain() {
        return findPillElement(PILL_BUY_AGAIN_RESOURCE_ID);
    }

    public WebElement pillAccount() {
        return findPillElement(PILL_ACCOUNT_RESOURCE_ID);
    }

    public void tapLists() {
        tapPill(PILL_LISTS_RESOURCE_ID, DESC_LISTS);
    }

    public void tapOrders() {
        tapPill(PILL_ORDERS_RESOURCE_ID, DESC_ORDERS);
    }

    public void tapBuyAgain() {
        tapPill(PILL_BUY_AGAIN_RESOURCE_ID, DESC_BUY_AGAIN);
    }

    public void tapAccount() {
        tapPill(PILL_ACCOUNT_RESOURCE_ID, DESC_ACCOUNT);
    }

    private WebElement findPillElement(String fullResourceId) {
        waitForMenuTileRowVisible();
        String shortSuffix = shortResourceSuffix(fullResourceId);
        List<By> tries = Arrays.asList(
                pillUnderHamburger(shortSuffix),
                pillUnderHamburgerExactResourceId(shortSuffix),
                By.xpath("//*[@resource-id='" + shortSuffix + "']"),
                byResourceId(fullResourceId),
                By.xpath("//*[contains(@resource-id,'" + shortSuffix + "')]"));
        for (By by : tries) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(by));
            } catch (Exception ignored) {
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("Pill not found for id suffix: " + shortSuffix);
    }

    private void tapPill(String fullResourceId, String contentDescFallback) {
        waitForMenuTileRowVisible();
        String shortSuffix = shortResourceSuffix(fullResourceId);
        List<By> tries = Arrays.asList(
                underScrolledHamburgerContentDesc(contentDescFallback),
                pillUnderHamburger(shortSuffix),
                pillUnderHamburgerExactResourceId(shortSuffix),
                By.xpath("//*[@resource-id='" + shortSuffix + "']"),
                byResourceId(fullResourceId),
                By.xpath("//*[contains(@resource-id,'" + shortSuffix + "')]"),
                By.xpath("//*[@content-desc='" + contentDescFallback + "']"));
        Exception last = null;
        for (By by : tries) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                return;
            } catch (Exception e) {
                last = e;
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("Could not tap pill; last error: " + (last != null ? last.getMessage() : ""));
    }

    private static By pillUnderHamburgerExactResourceId(String shortResourceId) {
        return By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"
                + "//*[@resource-id='" + shortResourceId + "']");
    }

    private static By pillUnderHamburger(String resourceIdSuffix) {
        return By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"
                + "//*[contains(@resource-id,'" + resourceIdSuffix + "')]");
    }

    private static By underScrolledHamburger(String resourceIdSuffix) {
        return By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"
                + "//*[contains(@resource-id,'" + resourceIdSuffix + "')]");
    }

    private static By underScrolledHamburgerContentDesc(String contentDesc) {
        return By.xpath("//*[contains(@resource-id,'scrolled-hamburger-view')]"
                + "//*[@content-desc='" + contentDesc + "']");
    }

    private static String shortResourceSuffix(String fullResourceId) {
        return fullResourceId.contains("/id/")
                ? fullResourceId.substring(fullResourceId.indexOf("/id/") + 4)
                : fullResourceId;
    }

    private static By byResourceId(String fullResourceId) {
        return By.xpath("//*[@resource-id='" + fullResourceId + "']");
    }
}
