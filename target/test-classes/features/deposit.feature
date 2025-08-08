Feature: Customer deposits money

  Background:
    Given the user navigates to the XYZ Bank login page
    When the user clicks on "Customer Login" button

  @Deposit
  Scenario Outline: Deposit money successfully
    And the user selects "<CustomerName>" from the customer dropdown
    And the user clicks on "Login" button
    And the customer opens "Deposit" tab
    And the customer notes current balance
    When the customer enters deposit amount "<Amount>"
    And the customer submits the deposit
    Then a deposit success message should contain "Deposit Successful"
    And the account balance should increase by "<Amount>"
    And the customer opens "Transactions" tab
    And the customer sets date range to today
    And a transaction of amount "<Amount>" and type "Credit" should exist

    Examples:
      | CustomerName | Amount |
      | Harry Potter | 150    |
      | Ron Weasly   | 75     |