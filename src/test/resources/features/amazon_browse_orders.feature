Feature: Browse menu and Orders

  Scenario: Orders empty state when there are no recent orders
    Given the Amazon shopping app is in the foreground
    Then the bottom tab bar should show at least one icon
    When I tap the Profile Menu icon
    Then the order pills row should be visible
    When I tap the Orders pill
    Then I should see the empty state for orders
