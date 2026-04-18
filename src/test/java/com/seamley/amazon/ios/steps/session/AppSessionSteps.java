package com.seamley.amazon.ios.steps.session;

import com.seamley.amazon.ios.context.ScenarioContext;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public final class AppSessionSteps {

    private final ScenarioContext context;

    public AppSessionSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("the Amazon shopping app is in the foreground on iOS")
    public void appInForeground() {
        context.bringAmazonToForeground();
        IOSDriver driver = context.driver();
        Assert.assertNotNull(driver, "driver");
        Assert.assertTrue(driver.isAppInstalled(ScenarioContext.AMAZON_BUNDLE_ID), "Amazon app should be installed");
    }
}
