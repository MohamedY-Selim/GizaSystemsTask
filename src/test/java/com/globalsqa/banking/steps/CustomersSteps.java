package com.globalsqa.banking.steps;

import com.globalsqa.banking.pages.manager.CustomersPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomersSteps {

    // Variables
    private CustomersPage customersPage = NavigationSteps.customersPageRef;

    // Methods
    @When("the manager searches for customer by first name {string}")
    public void searchByFirstName(String firstName) {
        customersPage.typeSearch(firstName.trim());
    }

    @Then("the customer list should contain a row with first name {string}")
    public void assertRowContainsFirstName(String firstName) {
        List<String> firstNames = customersPage.getColumnValues(1);
        Assert.assertTrue(
                firstNames.stream().anyMatch(fn -> fn.equalsIgnoreCase(firstName.trim())),
                "First name not found: " + firstName
        );
    }

    @When("the manager sorts customers by postcode ascending")
    public void sortByPostcodeAsc() {
        customersPage.sortByPostCodeAscending();
    }

    @When("the manager sorts customers by postcode descending")
    public void sortByPostcodeDesc() {
        customersPage.sortByPostCodeDescending();
    }

    @Then("customers should be sorted by postcode {string}")
    public void assertSortedByPostcode(String order) {
        List<String> actual = customersPage.getPostcodes();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(String::compareToIgnoreCase);
        if ("descending".equalsIgnoreCase(order.trim())) {
            Collections.reverse(expected);
        }
        Assert.assertEquals(actual, expected, "Postcode sorting mismatch: " + order);
    }

    @When("the manager deletes the first visible customer row")
    public void deleteFirstVisibleCustomer() {
        customersPage.deleteFirstVisibleRow();
    }

    @When("the manager deletes customer with first name {string}")
    public void deleteCustomerByName(String firstName) {
        customersPage.deleteCustomerByFirstName(firstName.trim());
    }

    @Then("no row should contain first name {string}")
    public void assertCustomerDeleted(String firstName) {
        List<String> firstNames = customersPage.getColumnValues(1);
        Assert.assertTrue(
                firstNames.stream().noneMatch(fn -> fn.equalsIgnoreCase(firstName.trim())),
                "Customer still exists: " + firstName
        );
    }
}