package com.amazon.ios.steps.searchInput;

// Steps around the iOS home search field (visible check + tap).

import com.amazon.ios.context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public final class SearchInputBoxSteps {

    private final ScenarioContext context;

    public SearchInputBoxSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the search input field should be visible on iOS")
    public void searchInputExist() {
        Assert.assertTrue(context.bottomTabBar().searchInputBoxExist(), "expected search input field to be visible");
    }

    @When("I tap the search input field on iOS")
    public void clickOnSearchInputBox() {
        context.bottomTabBar().tapSearchBox();
    }

    @Then("^Write \"(.+)\" in the search field on iOS$")
    public void writeQuotedTextInSearchField(String product) {
        context.bottomTabBar().typeInSearchField(product);
    }

    @And("^Click on the first search result that value is \"(.+)\" on iOS$")
    public void clickOnFirstSearchResult(String product) {
        context.bottomTabBar().tapFirstSearchResult(product);
    }
}
