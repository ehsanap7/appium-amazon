package com.seamley.amazon.android.util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public final class ElementInspector {

    private ElementInspector() {
    }

    public static void logAllElementsWithResourceId(AndroidDriver driver, String resourceId) {
        By by = By.xpath("//*[@resource-id='" + resourceId + "']");
        List<WebElement> elements = driver.findElements(by);
        System.out.println();
        System.out.println("=== Elements with resource-id=\"" + resourceId + "\" ===");
        System.out.println("Count: " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            WebElement el = elements.get(i);
            System.out.println("--- [#" + i + "] ---");
            System.out.println("  displayed: " + el.isDisplayed());
            System.out.println("  enabled:   " + el.isEnabled());
            try {
                System.out.println("  content-desc (UiAutomator): " + el.getAttribute("content-desc"));
            } catch (Exception ignored) {
                System.out.println("  content-desc: (n/a)");
            }
            try {
                System.out.println("  className: " + el.getAttribute("className"));
            } catch (Exception ignored) {
                System.out.println("  className: (n/a)");
            }
            try {
                System.out.println("  text:      " + el.getAttribute("text"));
            } catch (Exception ignored) {
                System.out.println("  text:      (n/a)");
            }
            try {
                System.out.println("  contentDescription: " + el.getAttribute("contentDescription"));
            } catch (Exception ignored) {
                System.out.println("  contentDescription: (n/a)");
            }
            try {
                System.out.println("  bounds:    " + el.getAttribute("bounds"));
            } catch (Exception ignored) {
                System.out.println("  bounds:    (n/a)");
            }
            try {
                System.out.println("  resource-id: " + el.getAttribute("resourceId"));
            } catch (Exception ignored) {
                System.out.println("  resource-id: (n/a)");
            }
            try {
                System.out.println("  location:  " + el.getLocation() + ", size: " + el.getSize());
            } catch (Exception e) {
                System.out.println("  location/size: (n/a) " + e.getMessage());
            }
        }
        System.out.println("=== end ===");
        System.out.println();
    }

    public static void logImageMenuItemPillsUnderScrolledHamburgerView(AndroidDriver driver) {
        By underHamburger = By.xpath(
                "//*[contains(@resource-id,'scrolled-hamburger-view')]"
                        + "//*[contains(@resource-id,'image_menu_item_pill_')]");
        List<WebElement> elements = driver.findElements(underHamburger);
        System.out.println();
        System.out.println("=== Pills under scrolled-hamburger-view (resource-id contains image_menu_item_pill_) ===");
        System.out.println("Count: " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            WebElement el = elements.get(i);
            System.out.println("--- [#" + i + "] ---");
            System.out.println("  displayed: " + safeDisplayed(el));
            try {
                System.out.println("  resource-id: " + el.getAttribute("resourceId"));
            } catch (Exception e) {
                System.out.println("  resource-id: (n/a)");
            }
            try {
                System.out.println("  content-desc: " + el.getAttribute("content-desc"));
            } catch (Exception e) {
                System.out.println("  content-desc: (n/a)");
            }
            try {
                System.out.println("  className: " + el.getAttribute("className"));
            } catch (Exception e) {
                System.out.println("  className: (n/a)");
            }
            try {
                System.out.println("  clickable: " + el.getAttribute("clickable"));
            } catch (Exception e) {
                System.out.println("  clickable: (n/a)");
            }
        }
        System.out.println("=== end ===");
        System.out.println();
    }

    private static boolean safeDisplayed(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
