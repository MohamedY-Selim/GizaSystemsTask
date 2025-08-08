package com.globalsqa.banking.steps;

import com.globalsqa.banking.pages.customer.TransactionsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class TransactionsSteps {

    // Page reference (shared from NavigationSteps or Hooks)
    private TransactionsPage txPage() {
        return NavigationSteps.transactionsPageRef;
    }

    @And("the customer sets date range to today")
    public void setDateRangeToToday() {
        txPage().setDateRangeToToday();
    }

    @Then("a transaction of amount {string} and type {string} should exist")
    public void verifyTransactionExists(String amount, String type) {
        boolean found = txPage().ensureTransactionAfterDeposit(amount, type);
        Assert.assertTrue(
                found,
                "Transaction not found for amount: " + amount + " and type: " + type
        );
    }
}