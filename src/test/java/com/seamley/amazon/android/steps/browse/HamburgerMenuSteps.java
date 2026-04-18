package com.seamley.amazon.android.steps.browse;

import com.seamley.amazon.android.context.ScenarioContext;
import com.seamley.amazon.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class HamburgerMenuSteps {

    private final ScenarioContext context;

    public HamburgerMenuSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I tap the Hamburger menu tab on Android")
    public void tapHamburgerMenu() {
        context.bottomTabBar().tapHamburgerMenuTab();
    }

    @Then("the order pills row should be visible on Android")
    public void pillsRowVisible() {
        context.orderMenuPills().waitForMenuTileRowVisible();
    }

    @When("I tap the Orders pill on Android")
    public void tapOrders() {
        context.orderMenuPills().tapOrders();
    }
}
