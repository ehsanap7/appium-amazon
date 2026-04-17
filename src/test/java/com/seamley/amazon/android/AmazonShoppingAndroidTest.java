package com.seamley.amazon.android;

import com.seamley.amazon.android.factory.AmazonPageFactory;
import com.seamley.amazon.android.pages.BottomTabBarPage;
import com.seamley.amazon.android.pages.BrowseMenuPillsPage;
import com.seamley.amazon.android.util.ElementInspector;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AmazonShoppingAndroidTest {

    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723/";
    private static final String DEFAULT_UDID = "2A041JEGR05120";
    private static final String APP_PACKAGE = "com.amazon.mShop.android.shopping";
    private static final String APP_ACTIVITY = "com.amazon.mShop.home.HomeActivity";

    private AndroidDriver driver;
    private AmazonPageFactory pages;

    @BeforeClass(alwaysRun = true)
    public void startSession() throws MalformedURLException {
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
        pages = AmazonPageFactory.create(driver);
    }

    @AfterClass(alwaysRun = true)
    public void endSession() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void step01_amazonAppIsInForeground() {
        Assert.assertEquals(driver.getCurrentPackage(), APP_PACKAGE, "current package");
        System.out.println("Activity: " + driver.currentActivity());
    }

    @Test(dependsOnMethods = "step01_amazonAppIsInForeground")
    public void step02_bottomTabBarIconsArePresent() throws InterruptedException {
        Thread.sleep(3000);
        ElementInspector.logAllElementsWithResourceId(driver, BottomTabBarPage.BOTTOM_TAB_ICON_RESOURCE_ID);
        int count = pages.bottomTabBar().tabIcons().size();
        Assert.assertTrue(count >= 1, "expected at least one bottom tab icon, got: " + count);
    }

    @Test(dependsOnMethods = "step02_bottomTabBarIconsArePresent")
    public void step03_tapBrowseMenuTab() throws InterruptedException {
        pages.bottomTabBar().tapBrowseMenuTab();
        Thread.sleep(1500);
    }

    @Test(dependsOnMethods = "step03_tapBrowseMenuTab")
    public void step04_browseMenuPillsRowVisible() {
        BrowseMenuPillsPage browsePills = pages.browseMenuPills();
        browsePills.waitForMenuTileRowVisible();
        ElementInspector.logImageMenuItemPillsUnderScrolledHamburgerView(driver);
    }

    @Test(dependsOnMethods = "step04_browseMenuPillsRowVisible")
    public void step05_tapOrdersPill() {
        pages.browseMenuPills().tapOrders();
    }

    @Test(dependsOnMethods = "step05_tapOrdersPill")
    public void step06_ordersRecentEmptyStateMessage() throws InterruptedException {
        pages.ordersRecentEmptyState().assertNoRecentOrdersMessageVisible();
        Thread.sleep(2000);
    }
}
