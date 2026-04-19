package com.amazon.android.steps.orders;

import com.amazon.android.context.ScenarioContext;
import io.cucumber.java.en.Then;

public final class OrdersSteps {

    private final ScenarioContext context;

    public OrdersSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("I should see the no recent orders empty state on Android")
    public void assertEmptyOrders() throws InterruptedException {
        context.ordersRecentEmptyState().assertNoRecentOrdersMessageVisible();
        Thread.sleep(2000);
    }
}
