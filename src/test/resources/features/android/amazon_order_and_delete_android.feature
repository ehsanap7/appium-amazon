@android
Feature: Order An Item and Manage Cart (Android)

  Scenario Outline: Android — Search, add to cart, open cart, verify, remove line
    Given the Amazon shopping app is in the foreground on Android
    Then the search input field should be visible on Android
    When I tap the search input field on Android
    Then Write "<product>" in the search field on Android
    And Click on the first search result that value is "<product>" on Android
    And Click on the fist item which is "<detail>" on Android
    And Click on the Add to Cart button of first search result on Android
#    When I tap the cart tab on Android
#    Then the cart should show an item containing "<product>" on Android
#    And print the cart line summary containing "<product>" on Android
#    When I delete the cart line containing "<product>" on Android

    Examples:
      | product |
      | bottle warmer |
      | laptop  |
      | book    |
