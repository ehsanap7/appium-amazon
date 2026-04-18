package com.seamley.amazon.android.steps.hooks;

import com.seamley.amazon.android.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public final class LifecycleHooks {

    private final ScenarioContext context;

    public LifecycleHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void startSession() throws MalformedURLException {
        context.startSession();
    }

    @After
    public void endSession() {
        context.endSession();
    }
}
