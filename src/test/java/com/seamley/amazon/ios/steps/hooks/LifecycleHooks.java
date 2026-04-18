package com.seamley.amazon.ios.steps.hooks;

import com.seamley.amazon.ios.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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

    @After(value = "@ios", order = 10000)
    public void endSession() {
        context.endSession();
    }
}
