package com.amazon.ios.steps.browse;

import com.amazon.ios.context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class ProfileMenuSteps {

    private final ScenarioContext context;

    public ProfileMenuSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I tap the Profile icon on iOS")
    public void tapProfileMenu() {
        context.bottomTabBar().tapProfileIcon();
    }

    @Then("the order pills row should be visible on iOS")
    public void orderPillsRowVisible() {
        context.browseMenuPills().waitForMenuTileRowVisible();
    }

    @When("I tap the Orders pill on iOS")
    public void tapOrders() {
        context.browseMenuPills().tapOrders();
    }
}
