package com.globalsqa.banking.steps;

import com.globalsqa.banking.factory.DriverFactory;
import com.globalsqa.banking.pages.manager.AddCustomerPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddCustomerSteps {

    // Driver
    private final WebDriver driver = DriverFactory.getDriver();

    // Variables
    private AddCustomerPage addCustomerPage() { return NavigationSteps.addCustomerPageRef; }

    // Methods
    @When("the manager fills first name {string} last name {string} and postcode {string}")
    public void fillCustomer(String first, String last, String postcode) {
        addCustomerPage()
                .fillFirstName(first.trim())
                .fillLastName(last.trim())
                .fillPostCode(postcode.trim());
    }

    @When("the manager submits customer creation")
    public void submitCustomer() {
        addCustomerPage().submitForm();
    }

    @Then("a success popup should contain {string}")
    public void assertSuccessPopup(String expectedPart) {
        String text = addCustomerPage().getAndAcceptAlertText();
        Assert.assertTrue(text.contains(expectedPart.trim()), "Unexpected alert text: " + text);
    }
}