package com.amazon.common.steps.session;

import com.amazon.common.context.ScenarioContext;
import com.amazon.utils.AppiumUtils;
import com.amazon.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public final class AppSessionSteps {

    private final ScenarioContext context;

    public AppSessionSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("the Amazon shopping app is in the foreground")
    public void appInForeground() {
        context.bringAmazonToForeground();

        String platform = AppiumUtils.need("TARGET_PLATFORM").toLowerCase();
        if (platform.equals("android")) {
            AndroidDriver driver = (AndroidDriver) context.driver();
            Assert.assertEquals(driver.getCurrentPackage(), AndroidUtils.AMAZON_APP_PACKAGE, "current package");
            System.out.println("Activity: " + driver.currentActivity());
        }
    }
}
