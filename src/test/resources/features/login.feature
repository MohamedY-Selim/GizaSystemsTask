Feature: Login to XYZ Bank application

  Background:
    Given the user navigates to the XYZ Bank login page

  @BankManagerLogin
  Scenario: Login as Bank Manager
    When the user clicks on "Bank Manager Login" button
    Then the Bank Manager dashboard should be displayed

  @CustomerLogin
  Scenario Outline: Login as Customer
    When the user clicks on "Customer Login" button
    And the user selects "<CustomerName>" from the customer dropdown
    And the user clicks on "Login" button
    Then the Customer dashboard should be displayed
    And the welcome message should contain "<CustomerName>"

    Examples:
      | CustomerName |
      | Harry Potter |
      | Ron Weasly   |