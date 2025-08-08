package com.globalsqa.banking.steps;

import com.globalsqa.banking.factory.DriverFactory;
import com.globalsqa.banking.pages.HomePage;
import com.globalsqa.banking.pages.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    // Driver
    private final WebDriver driver = DriverFactory.getDriver();

    // Variables
    private final LoginPage loginPage = new LoginPage(driver);
    private HomePage homePage;

    // Methods
    @Given("the user navigates to the XYZ Bank login page")
    public void navigateToLoginPage() {
        loginPage.load();
    }

    @When("the user clicks on {string} button")
    public void clickOnButton(String btn) {
        String key = btn.trim();
        switch (key) {
            case "Bank Manager Login" -> this.homePage = loginPage.clickBankManagerLogin();
            case "Customer Login" -> loginPage.clickCustomerLogin();
            case "Login" -> this.homePage = loginPage.clickCustomerLoginSubmit();
            default -> throw new IllegalArgumentException("Unknown button: " + btn);
        }
    }

    @And("the user selects {string} from the customer dropdown")
    public void selectCustomer(String name) {
        loginPage.selectCustomer(name.trim());
    }

    @Then("the Bank Manager dashboard should be displayed")
    public void assertManagerDashboard() {
        Assert.assertTrue(homePage.isManagerDashboardVisible(), "Manager dashboard not visible");
    }

    @Then("the Customer dashboard should be displayed")
    public void assertCustomerDashboard() {
        Assert.assertTrue(homePage.isCustomerDashboardVisible(), "Customer dashboard not visible");
    }

    @And("the welcome message should contain {string}")
    public void assertWelcomeContains(String expectedName) {
        Assert.assertTrue(homePage.getWelcomeText().contains(expectedName.trim()), "Welcome text mismatch");
    }
}