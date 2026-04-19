package com.amazon.ios.runner;

import com.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/ios/amazon_browse_orders_ios.feature"},
        tags = "@ios",
        glue = {
                "com.amazon.ios.steps"
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
