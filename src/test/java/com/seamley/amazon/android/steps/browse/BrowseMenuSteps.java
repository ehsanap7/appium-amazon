package com.seamley.amazon.android.steps.browse;

import com.seamley.amazon.android.context.ScenarioContext;
import com.seamley.amazon.android.util.ElementInspector;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class BrowseMenuSteps {

    private final ScenarioContext context;

    public BrowseMenuSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I tap the Browse menu tab")
    public void tapBrowseMenu() throws InterruptedException {
        context.bottomTabBar().tapBrowseMenuTab();
        Thread.sleep(1500);
    }

    @Then("the browse menu pills row should be visible")
    public void pillsRowVisible() {
        AndroidDriver driver = context.driver();
        context.browseMenuPills().waitForMenuTileRowVisible();
        ElementInspector.logImageMenuItemPillsUnderScrolledHamburgerView(driver);
    }

    @When("I tap the Orders pill")
    public void tapOrders() {
        context.browseMenuPills().tapOrders();
    }
}
