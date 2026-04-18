package com.seamley.amazon.android.steps.session;

import com.seamley.amazon.android.context.ScenarioContext;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

public final class AppSessionSteps {

    private final ScenarioContext context;

    public AppSessionSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("the Amazon shopping app is in the foreground on Android")
    public void appInForeground() {
        context.bringAmazonToForeground();
        AndroidDriver driver = context.driver();
        Assert.assertEquals(driver.getCurrentPackage(), ScenarioContext.APP_PACKAGE, "current package");
        System.out.println("Activity: " + driver.currentActivity());
    }

    @When("I wait for the home screen to settle on Android")
    public void waitForHome() throws InterruptedException {
        Thread.sleep(3000);
    }
}
