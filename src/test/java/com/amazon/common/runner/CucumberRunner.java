package com.amazon.common.runner;

import com.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/amazon_browse_orders.feature"},
        glue = {
                "com.amazon.common.steps",
        },
        objectFactory = PicoFactory.class,
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true
)
public class CucumberRunner extends BaseCucumberRunner {
}
