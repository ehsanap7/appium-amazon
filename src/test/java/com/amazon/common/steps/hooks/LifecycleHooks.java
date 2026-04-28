package com.amazon.common.steps.hooks;

import com.amazon.common.context.ScenarioContext;
import com.amazon.support.CucumberTeardownAllure;
import com.amazon.utils.AppiumUtils;
import com.amazon.utils.AndroidUtils;
import com.amazon.utils.IosUtils;
import io.appium.java_client.InteractsWithApps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public final class LifecycleHooks {

    private final ScenarioContext context;

    public LifecycleHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 0)
    public void startSession() throws IOException {
        context.startSession();
    }

    @After(order = 50_000)
    public void teardownAllureAttachments(Scenario scenario) {
        String platform = AppiumUtils.need("TARGET_PLATFORM");
        CucumberTeardownAllure.runTeardownAttachments(scenario, context.driver(), platform);
    }

    @After(order = 10_000)
    public void endSession() {
        String platform = AppiumUtils.need("TARGET_PLATFORM").toLowerCase();
        String identifier = platform.equals("android") ? AndroidUtils.AMAZON_APP_PACKAGE : IosUtils.AMAZON_IOS_BUNDLE_ID;

        ((InteractsWithApps) context.driver()).terminateApp(identifier);

        context.endSession();
    }
}
