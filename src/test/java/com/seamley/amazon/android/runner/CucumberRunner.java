package com.seamley.amazon.android.runner;

import com.seamley.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/amazon_browse_orders_android.feature"},
        tags = "@android",
        glue = {
                "com.seamley.amazon.android.steps.hooks",
                "com.seamley.amazon.android.steps.session",
                "com.seamley.amazon.android.steps.bottombar",
                "com.seamley.amazon.android.steps.browse",
                "com.seamley.amazon.android.steps.orders"
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
