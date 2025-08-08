package com.globalsqa.banking.pages.customer;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DepositPage extends BasePage<DepositPage> {

    // Constructor
    public DepositPage(WebDriver driver) { super(driver); }

    // Locators
    private final By depositForm  = By.cssSelector("form[ng-submit='deposit()']");
    private final By amountInput  = By.cssSelector("input[ng-model='amount']");
    private final By submitBtn    = By.cssSelector("button[type='submit']");
    private final By resultMsg    = By.cssSelector("span.error.ng-binding");
    private final By balanceValue = By.cssSelector(".center strong:nth-of-type(2)");

    // Methods

    @Step("Load Deposit page")
    public DepositPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.ACCOUNT_PAGE_END_POINT);
        waitForVisible(depositForm);
        return this;
    }

    @Step("Enter deposit amount: {amount}")
    public DepositPage enterAmount(String amount) {
        waitForVisible(depositForm);
        type(amountInput, amount);
        return this;
    }

    @Step("Submit deposit")
    public DepositPage submit() {
        waitForVisible(depositForm);
        click(submitBtn);
        return this;
    }

    @Step("Get deposit result message")
    public String getResultMessage() {
        return waitForVisible(resultMsg).getText().trim();
    }

    @Step("Get current account balance")
    public int getBalance() {
        String txt = waitForVisible(balanceValue).getText().trim();
        return Integer.parseInt(txt);
    }

}