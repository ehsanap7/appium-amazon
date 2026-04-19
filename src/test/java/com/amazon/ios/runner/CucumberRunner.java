package com.amazon.ios.runner;

import com.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/amazon_browse_orders_ios.feature"},
        tags = "@ios",
        glue = {
                "com.amazon.ios.steps.hooks",
                "com.amazon.ios.steps.session",
                "com.amazon.ios.steps.bottombar",
                "com.amazon.ios.steps.browse",
                "com.amazon.ios.steps.orders"
        },
        objectFactory = PicoFactory.class,
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports-ios/cucumber.html",
                "json:target/cucumber-reports-ios/cucumber.json"
        },
        monochrome = true
)
public class CucumberRunner extends BaseCucumberRunner {
}
