package com.seamley.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OrderPillsPage extends AbstractAmazonPage {

    public static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";

    public static final String SCROLLED_HAMBURGER_VIEW_RESOURCE_ID =
            APP_PACKAGE + ":id/scrolled-hamburger-view";

    public static final String MENU_ITEM_TILE_SINGLE_LINE_CONTAINER =
            APP_PACKAGE + ":id/Menu-Item-Tile-Single-Line-Container";

    public static final String PILL_ORDERS_RESOURCE_ID = APP_PACKAGE + ":id/image_menu_item_pill_yo";

    public static final String DESC_ORDERS = "Orders";

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    private final WebDriverWait wait;

    public OrderPillsPage(AndroidDriver driver) {
        this(driver, DEFAULT_WAIT);
    }

    public OrderPillsPage(AndroidDriver driver, Duration timeout) {
        super(driver);
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitForMenuTileRowVisible() {
        wait.until(anyOrderLocatorPresent());
    }

    private static ExpectedCondition<Boolean> anyOrderLocatorPresent() {
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

    public void tapOrders() {
        tapPill();
    }

    private void tapPill() {
        waitForMenuTileRowVisible();
        String shortSuffix = shortResourceSuffix(OrderPillsPage.PILL_ORDERS_RESOURCE_ID);
        List<By> tries = Arrays.asList(
                underScrolledHamburgerContentDesc(OrderPillsPage.DESC_ORDERS),
                pillUnderHamburger(shortSuffix),
                pillUnderHamburgerExactResourceId(shortSuffix),
                By.xpath("//*[@resource-id='" + shortSuffix + "']"),
                byResourceId(OrderPillsPage.PILL_ORDERS_RESOURCE_ID),
                By.xpath("//*[contains(@resource-id,'" + shortSuffix + "')]"),
                By.xpath("//*[@content-desc='" + OrderPillsPage.DESC_ORDERS + "']"));
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
