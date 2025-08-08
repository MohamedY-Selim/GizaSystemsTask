package com.globalsqa.banking.steps;

import com.globalsqa.banking.factory.DriverFactory;
import com.globalsqa.banking.pages.manager.OpenAccountPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OpenAccountSteps {

    // Driver
    private final WebDriver driver = DriverFactory.getDriver();

    // Variables
    private OpenAccountPage openAccountPage() { return NavigationSteps.openAccountPageRef; }

    // Methods
    @When("the manager chooses customer {string}")
    public void chooseCustomer(String name) {
        openAccountPage().selectCustomer(name.trim());
    }

    @When("the manager chooses currency {string}")
    public void chooseCurrency(String currency) {
        openAccountPage().selectCurrency(currency.trim());
    }

    @When("the manager processes the account creation")
    public void processAccountCreation() {
        openAccountPage().clickProcess();
    }

    @Then("an account creation popup should contain {string}")
    public void assertAccountPopup(String expected) {
        String text = openAccountPage().getAndAcceptAlertText();
        Assert.assertTrue(text.contains(expected.trim()), "Unexpected alert text: " + text);
    }
}