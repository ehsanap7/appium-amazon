package com.amazon.ios.steps.bottombar;

import com.amazon.ios.context.ScenarioContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public final class BottomBarSteps {

    private final ScenarioContext context;

    public BottomBarSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the bottom tab bar should show at least one icon on iOS")
    public void bottomTabBarHasIcons() {
        int count = context.bottomTabBar().tabIcons().size();
        System.out.println("Bottom tab bar button count: " + count);
        Assert.assertTrue(count >= 1, "expected at least one tab bar button, got: " + count);
    }
}
