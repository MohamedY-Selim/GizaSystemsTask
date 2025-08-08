package com.globalsqa.banking.pages;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage> {

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By bankManagerLoginBtn = By.cssSelector("button[ng-click='manager()']");
    private final By customerLoginBtn = By.cssSelector("button[ng-click='customer()']");
    private final By customerSelect = By.id("userSelect");
    private final By customerLoginSubmit = By.cssSelector("button.btn.btn-default[type='submit']");

    // Methods
    @Step("Load login page")
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.LOGIN_PAGE_END_POINT);
        waitForVisible(bankManagerLoginBtn);
        waitForVisible(customerLoginBtn);
        return this;
    }

    @Step("Click Bank Manager Login")
    public HomePage clickBankManagerLogin() {
        click(bankManagerLoginBtn);
        return new HomePage(driver);
    }

    @Step("Click Customer Login")
    public LoginPage clickCustomerLogin() {
        click(customerLoginBtn);
        return this;
    }

    @Step("Select customer: {name}")
    public LoginPage selectCustomer(String name) {
        selectFromDropdown(customerSelect, name);
        return this;
    }

    @Step("Click customer login submit")
    public HomePage clickCustomerLoginSubmit() {
        click(customerLoginSubmit);
        return new HomePage(driver);
    }
}