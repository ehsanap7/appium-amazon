package com.seamley.amazon.ios.steps.bottombar;

import com.seamley.amazon.ios.context.ScenarioContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class BottomBarSteps {

    private final ScenarioContext context;

    public BottomBarSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the bottom tab bar should show at least one icon on iOS")
    public void bottomTabBarHasIcons() {
        context.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        int count = context.bottomTabBar().tabBarButtons().size();
        Assert.assertTrue(count >= 1, "expected at least one tab bar button, got: " + count);
    }
}
