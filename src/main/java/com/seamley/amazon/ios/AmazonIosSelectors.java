package com.seamley.amazon.ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Locators shared by {@link OpenIosSettings} and iOS page objects (single flow: Me → Orders → Purchase history).
 */
public final class AmazonIosSelectors {

    private AmazonIosSelectors() {
    }

    public static final By ME_TAB = AppiumBy.iOSNsPredicateString("name == \"meTab\"");
    public static final By ORDERS = AppiumBy.iOSNsPredicateString("name == \"bac_yo\"");
    public static final By PURCHASE_HISTORY = AppiumBy.iOSNsPredicateString(
            "name == \"Purchase history\" AND label == \"Purchase history\" AND value == \"1\"");
}
