Feature: Add new customer

  Background:
    Given the user navigates to the XYZ Bank login page
    When the user clicks on "Bank Manager Login" button
    Then the Bank Manager dashboard should be displayed

  @AddCustomer
  Scenario Outline: Add customer successfully
    When the manager opens "Add Customer" page
    And the manager fills first name "<firstName>" last name "<lastName>" and postcode "<postcode>"
    And the manager submits customer creation
    Then a success popup should contain "Customer added successfully with customer id"

    Examples:
      | firstName | lastName | postcode |
      | John      | Wick     | 11111    |
      | Sarah     | Connor   | 22222    |