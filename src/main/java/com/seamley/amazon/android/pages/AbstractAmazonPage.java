package com.seamley.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;

public abstract class AbstractAmazonPage {

    protected final AndroidDriver driver;

    protected AbstractAmazonPage(AndroidDriver driver) {
        this.driver = driver;
    }

    protected AndroidDriver driver() {
        return driver;
    }
}
