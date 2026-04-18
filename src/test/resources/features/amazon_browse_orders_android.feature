@android
Feature: Browse menu and Orders (Android)

  Scenario: Android — orders empty state when there are no recent orders
    Given the Amazon shopping app is in the foreground on Android
    When I wait for the home screen to settle on Android
    Then the bottom tab bar should show at least one icon on Android
    When I tap the Browse menu tab on Android
    Then the browse menu pills row should be visible on Android
    When I tap the Orders pill on Android
    Then I should see the no recent orders empty state on Android
