@EbayApplyFilterFeature
Feature: Access a product by applying multiple filters

  @Positive @ApplyMultipleFilter
  Scenario: Access a product via category after applying multiple filters
    Given user with ebay web page
    When user clicks on shop by category filter in home page
    And user selects category as 'Electronics > Cell Phones & Accessories'
    Then verify selected category filter is applied
    When user clicks on 'Cell Phones & Smartphones' under shop by category section
    And user clicks on all filter at the end of the filter dropdowns
    And user applies condition filter as 'Open box'
    And user applies price filter as '100' to '500'
    And user applies item location filter as 'US only'
    And user clicks on apply in the all filter popup
    Then verify product list displayed are based on price filter applied
    And verify price filter is applied by verify the header value

