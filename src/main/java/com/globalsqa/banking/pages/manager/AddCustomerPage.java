package com.globalsqa.banking.pages.manager;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage extends BasePage<AddCustomerPage> {

    // Constructor
    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By firstNameInput = By.cssSelector("input[ng-model='fName']");
    private final By lastNameInput = By.cssSelector("input[ng-model='lName']");
    private final By postCodeInput = By.cssSelector("input[ng-model='postCd']");
    private final By submitBtn = By.cssSelector("button[type='submit']");

    // Methods
    @Step("Load Add Customer page")
    public AddCustomerPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.ADD_CUSTOMER_PAGE_END_POINT);
        waitForVisible(firstNameInput);
        return this;
    }

    @Step("Fill first name: {first}")
    public AddCustomerPage fillFirstName(String first) {
        type(firstNameInput, first);
        return this;
    }

    @Step("Fill last name: {last}")
    public AddCustomerPage fillLastName(String last) {
        type(lastNameInput, last);
        return this;
    }

    @Step("Fill postcode: {pc}")
    public AddCustomerPage fillPostCode(String pc) {
        type(postCodeInput, pc);
        return this;
    }

    @Step("Submit Add Customer form")
    public AddCustomerPage submitForm() {
        click(submitBtn);
        return this;
    }

    @Step("Get and accept alert text")
    public String getAndAcceptAlertText() {
        return alertGetTextAndAccept();
    }
}