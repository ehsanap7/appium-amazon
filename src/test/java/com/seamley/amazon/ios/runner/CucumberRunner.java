package com.seamley.amazon.ios.runner;

import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features/amazon_browse_orders_ios.feature"},
        tags = "@ios",
        glue = {
                "com.seamley.amazon.ios.steps.hooks",
                "com.seamley.amazon.ios.steps.session",
                "com.seamley.amazon.ios.steps.bottombar",
                "com.seamley.amazon.ios.steps.browse",
                "com.seamley.amazon.ios.steps.orders"
        },
        objectFactory = PicoFactory.class,
        plugin = {
                "pretty",
                "html:target/cucumber-reports-ios/cucumber.html",
                "json:target/cucumber-reports-ios/cucumber.json"
        },
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
