package com.globalsqa.banking.steps;

import com.globalsqa.banking.factory.DriverFactory;
import com.globalsqa.banking.pages.customer.DepositPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DepositSteps {

    // Driver
    private final WebDriver driver = DriverFactory.getDriver();

    // Variables
    private DepositPage depositPage() { return NavigationSteps.depositPageRef; }
    private int balanceBefore;
    private int balanceAfter;

    // Methods
    @When("the customer notes current balance")
    public void noteCurrentBalance() {
        balanceBefore = depositPage().getBalance();
    }

    @When("the customer enters deposit amount {string}")
    public void enterDepositAmount(String amount) {
        depositPage().enterAmount(amount.trim());
    }

    @And("the customer submits the deposit")
    public void submitDeposit() {
        depositPage().submit();
    }

    @Then("a deposit success message should contain {string}")
    public void assertDepositSuccessMessage(String expectedMessage) {
        String actual = depositPage().getResultMessage();
        Assert.assertTrue(
                actual.contains(expectedMessage.trim()),
                "Unexpected deposit message. Expected to contain: " + expectedMessage + " but was: " + actual
        );
    }

    @And("the account balance should increase by {string}")
    public void assertBalanceIncreased(String amount) {
        balanceAfter = depositPage().getBalance();
        int expectedIncrease = Integer.parseInt(amount.trim());
        Assert.assertEquals(
                balanceAfter - balanceBefore, expectedIncrease,
                "Balance did not increase by the expected amount"
        );
    }
}