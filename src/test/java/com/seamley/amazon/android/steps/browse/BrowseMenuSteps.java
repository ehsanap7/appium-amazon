package com.seamley.amazon.android.steps.browse;

import com.seamley.amazon.android.context.ScenarioContext;
import com.seamley.amazon.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class BrowseMenuSteps {

    private final ScenarioContext context;

    public BrowseMenuSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I tap the Browse menu tab on Android")
    public void tapBrowseMenu() throws InterruptedException {
        context.bottomTabBar().tapBrowseMenuTab();
        Thread.sleep(1500);
    }

    @Then("the browse menu pills row should be visible on Android")
    public void pillsRowVisible() {
        AndroidDriver driver = context.driver();
        context.browseMenuPills().waitForMenuTileRowVisible();
        AndroidUtils.logImageMenuItemPillsUnderScrolledHamburgerView(driver);
    }

    @When("I tap the Orders pill on Android")
    public void tapOrders() {
        context.browseMenuPills().tapOrders();
    }
}
