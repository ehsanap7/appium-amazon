package com.amazon.android.steps.searchInput;

import com.amazon.android.context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public final class SearchInputBoxSteps {

    private final ScenarioContext context;

    public SearchInputBoxSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the search input field should be visible on Android")
    public void searchInputExist() {
        Assert.assertTrue(context.bottomTabBar().searchInputBoxExist(), "expected search input field to be visible");
    }

    @When("I tap the search input field on Android")
    public void clickOnSearchInputBox() {
        context.bottomTabBar().tapSearchBox();
    }

    @Then("^Write \"(.+)\" in the search field on Android$")
    public void writeQuotedTextInSearchField(String product) {
        context.bottomTabBar().typeInSearchField(product);
    }

    @And("^Click on the first search result that value is \"(.+)\" on Android$")
    public void clickOnFirstSearchResult(String product) {
        context.bottomTabBar().tapFirstSearchResult(product);
    }

    @And("Click on the Add to Cart button of first search result on Android")
    public void clickOnAddToCart() {
        context.bottomTabBar().addToCart();
    }
}
