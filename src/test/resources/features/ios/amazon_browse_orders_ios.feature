@ios
Feature: Browse menu and Orders (iOS)

  Scenario: iOS — orders empty state when there are no recent orders
    Given the Amazon shopping app is in the foreground on iOS
    Then the bottom tab bar should show at least one icon on iOS
    When I tap the Profile icon on iOS
    Then the order pills row should be visible on iOS
    When I tap the Orders pill on iOS
    Then I should see the purchase history on iOS
