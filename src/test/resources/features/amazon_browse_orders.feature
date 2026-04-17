Feature: Browse menu and Orders

  Scenario: Orders empty state when there are no recent orders
    Given the Amazon shopping app is in the foreground
    When I wait for the home screen to settle
    Then the bottom tab bar should show at least one icon
    When I tap the Browse menu tab
    Then the browse menu pills row should be visible
    When I tap the Orders pill
    Then I should see the no recent orders empty state
