package com.amazon.android.runner;

import com.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/amazon_browse_orders_android.feature"},
        tags = "@android",
        glue = {
                "com.amazon.android.steps.hooks",
                "com.amazon.android.steps.session",
                "com.amazon.android.steps.bottombar",
                "com.amazon.android.steps.browse",
                "com.amazon.android.steps.orders"
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
