package com.seamley.amazon.ios.steps.orders;

import com.seamley.amazon.ios.context.ScenarioContext;
import io.cucumber.java.en.Then;

public final class OrdersSteps {

    private final ScenarioContext context;

    public OrdersSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("I should see the no recent orders empty state on iOS")
    public void assertEmptyOrders() {
        context.ordersRecentEmptyState().assertNoRecentOrdersMessageVisible();
    }
}
