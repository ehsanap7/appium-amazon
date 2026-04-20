@ios
Feature: Order An Item and Delete It (iOS)

  Scenario Outline: iOS — One item should be ordered and then deleted from the order history
    Given the Amazon shopping app is in the foreground on iOS
    Then the search input field should be visible on iOS
    When I tap the search input field on iOS
    Then Write "<product>" in the search field on iOS
    And Click on the first search result that value is "<product>" on iOS

    Examples:
      | product |
      | bottle  |
      | laptop  |
      | book    |
