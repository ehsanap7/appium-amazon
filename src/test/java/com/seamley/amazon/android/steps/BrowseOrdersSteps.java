package com.seamley.amazon.android.steps;

import com.seamley.amazon.android.factory.AmazonPageFactory;
import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.pages.BrowseMenuPillsPage;
import com.seamley.amazon.android.pages.OrdersRecentEmptyStatePage;
import com.seamley.amazon.android.util.ElementInspector;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class BrowseOrdersSteps {

    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_UDID = "2A041JEGR05120";
    private static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";
    private static final String APP_ACTIVITY = "com.amazon.mShop.home.HomeActivity";

    private AndroidDriver driver;
    private BottomTabBarPage bottomTabBar;
    private BrowseMenuPillsPage browseMenuPills;
    private OrdersRecentEmptyStatePage ordersRecentEmptyState;

    public BrowseOrdersSteps() {
    }

    @Before
    public void startSession() throws MalformedURLException {
        if (driver != null) {
            return;
        }
        String serverUrl = System.getProperty("appium.serverUrl", DEFAULT_APPIUM_SERVER_URL);
        String udid = System.getProperty("appium.udid", DEFAULT_UDID);

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid(udid)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setNoReset(true)
                .setFullReset(false);

        driver = new AndroidDriver(new URL(serverUrl), options);
        bringAmazonToForeground();
        AmazonPageFactory factory = AmazonPageFactory.create(driver);
        bottomTabBar = factory.bottomTabBar();
        browseMenuPills = factory.browseMenuPills();
        ordersRecentEmptyState = factory.ordersRecentEmptyState();
    }

    @After
    public void endSession() {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } finally {
            driver = null;
            bottomTabBar = null;
            browseMenuPills = null;
            ordersRecentEmptyState = null;
        }
    }

    @Given("the Amazon shopping app is in the foreground")
    public void appInForeground() {
        bringAmazonToForeground();
        Assert.assertEquals(driver.getCurrentPackage(), APP_PACKAGE, "current package");
        System.out.println("Activity: " + driver.currentActivity());
    }

    /** Session can start on launcher (noReset / IDE timing); bring Amazon up before assertions. */
    private void bringAmazonToForeground() {
        try {
            driver.activateApp(APP_PACKAGE);
        } catch (Exception ignored) {
        }
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> APP_PACKAGE.equals(driver.getCurrentPackage()));
    }

    @When("I wait for the home screen to settle")
    public void waitForHome() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Then("the bottom tab bar should show at least one icon")
    public void bottomTabBarHasIcons() {
        ElementInspector.logAllElementsWithResourceId(driver, BottomTabBarPage.BOTTOM_TAB_ICON_RESOURCE_ID);
        int count = bottomTabBar.tabIcons().size();
        Assert.assertTrue(count >= 1, "expected at least one bottom tab icon, got: " + count);
    }

    @When("I tap the Browse menu tab")
    public void tapBrowseMenu() throws InterruptedException {
        bottomTabBar.tapBrowseMenuTab();
        Thread.sleep(1500);
    }

    @Then("the browse menu pills row should be visible")
    public void pillsRowVisible() {
        browseMenuPills.waitForMenuTileRowVisible();
        ElementInspector.logImageMenuItemPillsUnderScrolledHamburgerView(driver);
    }

    @When("I tap the Orders pill")
    public void tapOrders() {
        browseMenuPills.tapOrders();
    }

    @Then("I should see the no recent orders empty state")
    public void assertEmptyOrders() throws InterruptedException {
        ordersRecentEmptyState.assertNoRecentOrdersMessageVisible();
        Thread.sleep(2000);
    }
}
