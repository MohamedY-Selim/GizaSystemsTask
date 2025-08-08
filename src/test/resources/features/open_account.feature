Feature: Open a new account

  Background:
    Given the user navigates to the XYZ Bank login page
    When the user clicks on "Bank Manager Login" button
    Then the Bank Manager dashboard should be displayed

  @OpenAccount
  Scenario Outline: Manager opens an account for an existing customer
    When the manager opens "Open Account" page
    And the manager chooses customer "<customerName>"
    And the manager chooses currency "<currency>"
    And the manager processes the account creation
    Then an account creation popup should contain "Account created successfully with account Number"

    Examples:
      | customerName | currency |
      | Harry Potter | Dollar   |
      | Ron Weasly   | Pound    |