package com.globalsqa.banking.pages;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.model.ManagerSection;
import com.globalsqa.banking.model.CustomerSection;
import com.globalsqa.banking.pages.manager.AddCustomerPage;
import com.globalsqa.banking.pages.manager.CustomersPage;
import com.globalsqa.banking.pages.manager.OpenAccountPage;
import com.globalsqa.banking.pages.customer.DepositPage;
import com.globalsqa.banking.pages.customer.TransactionsPage;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) { super(driver); }

    // Manager buttons
    private final By addCustomerBtn = By.cssSelector("button[ng-click='addCust()']");
    private final By openAccountBtn = By.cssSelector("button[ng-click='openAccount()']");
    private final By customersBtn   = By.cssSelector("button[ng-click='showCust()']");

    // Customer buttons
    private final By transactionsBtn = By.cssSelector("button[ng-click='transactions()']");
    private final By depositBtn      = By.cssSelector("button[ng-click='deposit()']");
    private final By withdrawalBtn   = By.cssSelector("button[ng-click='withdrawl()']");

    private final By welcomeText = By.cssSelector(".fontBig.ng-binding");

    @Step("Load Home page")
    public HomePage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        return this;
    }

    @Step("Check if Bank Manager dashboard is visible")
    public boolean isManagerDashboardVisible() {
        return isDisplayed(addCustomerBtn) && isDisplayed(openAccountBtn) && isDisplayed(customersBtn);
    }

    @Step("Check if Customer dashboard is visible")
    public boolean isCustomerDashboardVisible() {
        return isDisplayed(transactionsBtn) && isDisplayed(depositBtn) && isDisplayed(withdrawalBtn);
    }

    @Step("Get welcome text")
    public String getWelcomeText() {
        return waitForVisible(welcomeText).getText().trim();
    }

    // Generic open for Manager
    @Step("Open manager section: {section}")
    public Object open(ManagerSection section) {
        switch (section) {
            case ADD_CUSTOMER -> { click(addCustomerBtn); return new AddCustomerPage(driver); }
            case OPEN_ACCOUNT -> { click(openAccountBtn); return new OpenAccountPage(driver); }
            case CUSTOMERS    -> { click(customersBtn);   return new CustomersPage(driver); }
            default -> throw new IllegalStateException("Unexpected section: " + section);
        }
    }

    // Generic open for Customer
    @Step("Open customer section: {section}")
    public Object open(CustomerSection section) {
        switch (section) {
            case TRANSACTIONS -> { click(transactionsBtn); return new TransactionsPage(driver); }
            case DEPOSIT      -> { click(depositBtn);      return new DepositPage(driver); }
            default -> throw new IllegalStateException("Unexpected customer section: " + section);
        }
    }
}