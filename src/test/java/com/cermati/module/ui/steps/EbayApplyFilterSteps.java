package com.cermati.module.ui.steps;

import com.cermati.module.ui.pages.EbayHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class EbayApplyFilterSteps {

    private EbayHomePage ebayHomePage = new EbayHomePage();
    String category = "";
    String minPrice = "";
    String maxPrice = "";

    @Given("user with ebay web page")
    public void userOpenEbayWebPage() {
        ebayHomePage.openEbayHomePage();
    }

    @When("user clicks on shop by category filter in home page")
    public void userClicksOnShopByCategoryFilterInHomePage() {
        ebayHomePage.clickOnShopByCategory();
    }

    @And("user selects category as {string}")
    public void userSelectsCategoryAsElectronicsCellPhonesAccessories(String category) {
        this.category = category;
        ebayHomePage.selectCategory();
    }

    @Then("verify selected category filter is applied")
    public void verifySelectedCategoryFilterIsApplied() {
        assertThat("Selected category filter is not applied", ebayHomePage.verifyCategoryFilter(), containsString(category));
    }

    @When("user clicks on {string} under shop by category section")
    public void userClicksOnCellPhonesSmartphonesUnderShopByCategorySection(String category) {
        ebayHomePage.selectSubCategory();
    }

    @When("user clicks on all filter at the end of the filter dropdowns")
    public void userClicksOnAllFilterAtTheEndOfTheFilterDropdowns() {
        ebayHomePage.clickOnAllFilter();
    }

    @And("user applies condition filter as {string}")
    public void userAppliesConditionFilterAsOpenBox(String condition) {
        ebayHomePage.clickOnConditionFilter();
    }

    @And("user applies price filter as {string} to {string}")
    public void userAppliesPriceFilterAsTo(String minPrice, String maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        ebayHomePage.clickOnPriceFilter(minPrice, maxPrice);
    }

    @And("user applies item location filter as {string}")
    public void userAppliesItemLocationFilterAsUSOnly(String location) {
        ebayHomePage.clickOnItemLocationFilter();
    }

    @And("user clicks on apply in the all filter popup")
    public void userClicksOnApplyInTheAllFilterPopup() {
        ebayHomePage.clickOnApplyButton();
    }

    @Then("verify product list displayed are based on price filter applied")
    public void verifyProductListDisplayedAreBasedOnPriceFilterApplied() {
        assertThat("Products are not displayed in given price range filter", ebayHomePage.verifyProductDisplayedAreInGivenRange(), everyItem(equalTo(true)));
    }

    @And("verify price filter is applied by verify the header value")
    public void verifyPriceFilterIsAppliedByVerifyTheHeaderValue() {
        String verifyPrice = ebayHomePage.verifyPriceFilterApplied();
        assertThat("Min Price filter applied is not displayed in the header", verifyPrice, containsString(minPrice));
        assertThat("Max Price filter applied is not displayed in the header", verifyPrice, containsString(maxPrice));
    }

    @And("verify condition filter is applied as {string}")
    public void verifyConditionFilterIsAppliedAsOpenBox(String condition) {
        assertThat("Condition filter is not applied", ebayHomePage.verifyConditionFilterApplied(), everyItem(equalTo(condition)));

    }

    @And("verify item location filter is applied as {string}")
    public void verifyItemLocationFilterIsAppliedAsUnitedStates(String location) {
        assertThat("Item location filter is not applied", ebayHomePage.verifyLocationFilterApplied(), everyItem(containsString(location)));
    }

    @When("user searches for {string} in home page search bar")
    public void userSearchesForMacBookInHomePageSearchBar(String keyword) {
        ebayHomePage.searchByKeyword(keyword);
    }

    @And("user selects category as {string} in home page")
    public void userSelectsCategoryAsElectronicsComputersTabletsNetworkingInHomePage(String category) {
        ebayHomePage.applyComputerCategoryFilter();
    }

    @Then("verify {string} selected category filter is applied")
    public void verifyComputersTabletsNetworkingSelectedCategoryFilterIsApplied(String category) {
        assertThat(category + " category filter is not applied", ebayHomePage.verifyComputerCategoryFilterApplied(), containsString(category));
    }

    @And("verify search results has {string} products displayed")
    public void verifySearchResultsHasMacBookProductsDisplayed(String searchResult) {
        assertThat("Products are not displayed based on search filter applied", ebayHomePage.verifySearchFilterApplied(), everyItem(containsString(searchResult.toLowerCase())));
    }
}
