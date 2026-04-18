package com.seamley.amazon.android.steps.bottombar;

import com.seamley.amazon.android.context.ScenarioContext;
import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.util.ElementInspector;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public final class BottomBarSteps {

    private final ScenarioContext context;

    public BottomBarSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the bottom tab bar should show at least one icon")
    public void bottomTabBarHasIcons() {
        AndroidDriver driver = context.driver();
        ElementInspector.logAllElementsWithResourceId(driver, BottomTabBarPage.BOTTOM_TAB_ICON_RESOURCE_ID);
        int count = context.bottomTabBar().tabIcons().size();
        Assert.assertTrue(count >= 1, "expected at least one bottom tab icon, got: " + count);
    }
}
