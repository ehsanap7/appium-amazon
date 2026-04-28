package com.amazon.common.steps.browse;

import com.amazon.common.context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class HamburgerMenuSteps {

    private final ScenarioContext context;

    public HamburgerMenuSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I tap the Profile Menu icon")
    public void tapHamburgerMenu() {
        context.bottomTabBar().tapProfileIcon();
    }

    @Then("the order pills row should be visible")
    public void orderPillsRowVisible() {
        context.getOrderPills().waitForOrderTabVisible();
    }

    @When("I tap the Orders pill")
    public void tapOrders() {
        context.getOrderPills().tapOrders();
    }
}
