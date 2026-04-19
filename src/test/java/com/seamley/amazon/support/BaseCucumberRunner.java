package com.seamley.amazon.support;

import com.seamley.amazon.utils.AppiumUtils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public abstract class BaseCucumberRunner extends AbstractTestNGCucumberTests {

    private static AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void setUpAppium() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        String url = service.getUrl().toString();
        System.out.println("Appium server started at: " + url);
        AppiumUtils.setAppiumServerUrlOverride(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAppium() {
        AppiumUtils.clearAppiumServerUrlOverride();
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
