package com.amazon.android.runner;

import com.amazon.support.BaseCucumberRunner;
import io.cucumber.picocontainer.PicoFactory;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features/android/amazon_browse_orders_android.feature"},
        tags = "@android",
        glue = {
                "com.amazon.android.steps",
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
