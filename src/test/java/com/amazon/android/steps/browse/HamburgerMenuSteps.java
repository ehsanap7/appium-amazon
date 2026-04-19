package com.amazon.android.steps.browse;

import com.amazon.android.context.ScenarioContext;
import com.amazon.utils.AndroidUtils;
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

    @When("I tap the Orders pill on Android")
    public void tapOrders() {
        context.orderMenuPills().tapOrders();
    }
}
