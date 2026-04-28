package com.amazon.common.steps.orders;

import com.amazon.common.context.ScenarioContext;
import io.cucumber.java.en.Then;

public final class OrdersSteps {

    private final ScenarioContext context;

    public OrdersSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("I should see the empty state for orders")
    public void assertEmptyOrders() throws InterruptedException {
        context.ordersRecentEmptyState().assertNoRecentOrdersMessageVisible();
        Thread.sleep(2000);
    }
}
