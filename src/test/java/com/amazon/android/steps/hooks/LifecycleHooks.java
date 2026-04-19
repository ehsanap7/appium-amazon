package com.amazon.android.steps.hooks;

import com.amazon.android.context.ScenarioContext;
import com.amazon.support.CucumberTeardownAllure;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public final class LifecycleHooks {

    private final ScenarioContext context;

    public LifecycleHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before(value = "@android", order = 0)
    public void startSession() throws IOException {
        context.startSession();
    }

    @After(value = "@android", order = 50_000)
    public void teardownAllureAttachments(Scenario scenario) {
        CucumberTeardownAllure.runTeardownAttachments(scenario, context.driver(), "android");
    }

    @After(value = "@android", order = 10_000)
    public void endSession() {
        context.driver().terminateApp(ScenarioContext.APP_PACKAGE);
        context.endSession();
    }

}
