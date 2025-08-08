Feature: Manage customers list

  Background:
    Given the user navigates to the XYZ Bank login page
    When the user clicks on "Bank Manager Login" button
    Then the Bank Manager dashboard should be displayed
    When the manager opens "Customers" page

  @SearchCustomer
  Scenario: Search existing customer by first name
    When the manager searches for customer by first name "Hermoine"
    Then the customer list should contain a row with first name "Hermoine"

  @SortCustomersAsc
  Scenario: Sort customers by PostCode ascending
    When the manager sorts customers by postcode ascending
    Then customers should be sorted by postcode "ascending"

  @SortCustomersDesc
  Scenario: Sort customers by PostCode descending
    When the manager sorts customers by postcode descending
    Then customers should be sorted by postcode "descending"

  @DeleteSpecificCustomer
  Scenario: Delete a specific customer by first name
    When the manager searches for customer by first name "Albus"
    And the manager deletes customer with first name "Albus"
    Then no row should contain first name "Albus"