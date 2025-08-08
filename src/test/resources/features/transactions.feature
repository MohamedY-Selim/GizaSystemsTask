#Feature: Customer views and filters transactions
#
#  @TransactionsSearch
#  Scenario Outline: See the newly added transaction in the transactions tab
#    Given the user navigates to the XYZ Bank login page
#    When the user clicks on "Customer Login" button
#    And the user selects "<CustomerName>" from the customer dropdown
#    And the user clicks on "Login" button
#
#    And the customer opens "Deposit" tab
#    When the customer enters deposit amount "<Amount>"
#    And the customer submits the deposit
#    Then a deposit success message should contain "Deposit Successful"
#
#    And the customer opens "Transactions" tab
#    When the customer filters transactions from "" to ""
#    Then the transactions table should contain an entry with amount "<Amount>" and type "Credit"
#    And the transactions count should be greater than 0
#
#    Examples:
#      | CustomerName | Amount |
#      | Harry Potter | 60     |
#      | Ron Weasly   | 40     |