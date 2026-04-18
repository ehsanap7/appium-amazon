package com.seamley.amazon.ios.pages;

import io.appium.java_client.ios.IOSDriver;

public abstract class AbstractIosAmazonPage {

    protected final IOSDriver driver;

    protected AbstractIosAmazonPage(IOSDriver driver) {
        this.driver = driver;
    }
}
