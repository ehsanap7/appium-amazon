package com.amazon.android.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class AbstractAmazonPage {

    private static final Duration PAGE_FACTORY_IMPLICIT_WAIT = Duration.ofSeconds(15);

    protected final AndroidDriver driver;

    protected AbstractAmazonPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, PAGE_FACTORY_IMPLICIT_WAIT), this);
    }
}
