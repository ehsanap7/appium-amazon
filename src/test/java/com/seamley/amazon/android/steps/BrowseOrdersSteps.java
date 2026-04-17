package com.seamley.amazon.android.steps;

import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.support.TestContext;
import com.seamley.amazon.android.util.ElementInspector;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.net.MalformedURLException;

public final class BrowseOrdersSteps {

    private final TestContext context;

    public BrowseOrdersSteps(TestContext context) {
        this.context = context;
    }

    @Before
    public void startSession() throws MalformedURLException {
        context.startDriver();
    }

    @After
    public void endSession() {
        context.quitDriver();
    }

    @Given("the Amazon shopping app is in the foreground")
    public void appInForeground() {
        AndroidDriver driver = context.driver();
        Assert.assertEquals(driver.getCurrentPackage(), context.appPackage(), "current package");
        System.out.println("Activity: " + driver.currentActivity());
    }

    @When("I wait for the home screen to settle")
    public void waitForHome() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Then("the bottom tab bar should show at least one icon")
    public void bottomTabBarHasIcons() {
        AndroidDriver driver = context.driver();
        ElementInspector.logAllElementsWithResourceId(driver, BottomTabBarPage.BOTTOM_TAB_ICON_RESOURCE_ID);
        int count = context.bottomTabBar().tabIcons().size();
        Assert.assertTrue(count >= 1, "expected at least one bottom tab icon, got: " + count);
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

    @Then("I should see the no recent orders empty state")
    public void assertEmptyOrders() throws InterruptedException {
        context.ordersRecentEmptyState().assertNoRecentOrdersMessageVisible();
        Thread.sleep(2000);
    }
}
