@android
Feature: Hamburger menu and Orders (Android)

  Scenario: Android — orders empty state when there are no recent orders
    Given the Amazon shopping app is in the foreground on Android
    Then the bottom tab bar should show at least one icon on Android
    When I tap the Hamburger menu tab on Android
    Then the order pills row should be visible on Android
    When I tap the Orders pill on Android
    Then I should see the no recent orders empty state on Android
