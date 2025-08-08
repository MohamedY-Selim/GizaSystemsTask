package com.globalsqa.banking.steps;

import com.globalsqa.banking.factory.DriverFactory;
import com.globalsqa.banking.model.ManagerSection;
import com.globalsqa.banking.model.CustomerSection;
import com.globalsqa.banking.pages.HomePage;

// Manager pages
import com.globalsqa.banking.pages.manager.AddCustomerPage;
import com.globalsqa.banking.pages.manager.CustomersPage;
import com.globalsqa.banking.pages.manager.OpenAccountPage;

// Customer pages
import com.globalsqa.banking.pages.customer.DepositPage;
import com.globalsqa.banking.pages.customer.TransactionsPage;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class NavigationSteps {

    // Driver
    private final WebDriver driver = DriverFactory.getDriver();

    // Pages
    private final HomePage homePage = new HomePage(driver);

    // Manager refs
    public static AddCustomerPage addCustomerPageRef;
    public static OpenAccountPage openAccountPageRef;
    public static CustomersPage customersPageRef;

    // Customer refs
    public static DepositPage depositPageRef;
    public static TransactionsPage transactionsPageRef;

    // Manager navigation
    @When("the manager opens {string} page")
    public void the_manager_opens_page(String pageName) {
        ManagerSection section = ManagerSection.from(pageName);
        Object page = homePage.open(section);
        switch (section) {
            case ADD_CUSTOMER -> addCustomerPageRef = (AddCustomerPage) page;
            case OPEN_ACCOUNT -> openAccountPageRef = (OpenAccountPage) page;
            case CUSTOMERS    -> customersPageRef    = (CustomersPage) page;
        }
    }

    // Customer navigation
    @When("the customer opens {string} tab")
    public void the_customer_opens_tab(String tabName) {
        CustomerSection section = CustomerSection.from(tabName);
        Object page = homePage.open(section);
        switch (section) {
            case DEPOSIT      -> depositPageRef      = (DepositPage) page;
            case TRANSACTIONS -> transactionsPageRef = (TransactionsPage) page;
        }
    }
}