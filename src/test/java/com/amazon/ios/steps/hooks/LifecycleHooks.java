package com.amazon.ios.steps.hooks;

import com.amazon.ios.context.ScenarioContext;
import com.amazon.support.CucumberTeardownAllure;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public final class LifecycleHooks {

    private final ScenarioContext context;

    public LifecycleHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before(value = "@ios", order = 0)
    public void startSession() throws IOException {
        context.startSession();
    }

    @After(value = "@ios", order = 50_000)
    public void teardownAllureAttachments(Scenario scenario) {
        CucumberTeardownAllure.runTeardownAttachments(scenario, context.driver(), "ios");
    }

    @After(value = "@ios", order = 10_000)
    public void endSession() {
        context.endSession();
    }
}
