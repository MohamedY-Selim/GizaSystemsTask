package com.globalsqa.banking.pages.manager;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage<OpenAccountPage> {

    // Constructor
    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By customerSelect = By.id("userSelect");
    private final By currencySelect = By.id("currency");
    private final By processBtn = By.cssSelector("button[type='submit']");

    // Methods
    @Step("Load Open Account page")
    public OpenAccountPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.OPEN_ACCOUNT_PAGE_END_POINT);
        waitForVisible(customerSelect);
        return this;
    }

    @Step("Select customer: {name}")
    public OpenAccountPage selectCustomer(String name) {
        selectFromDropdown(customerSelect, name);
        return this;
    }

    @Step("Select currency: {currency}")
    public OpenAccountPage selectCurrency(String currency) {
        selectFromDropdown(currencySelect, currency);
        return this;
    }

    @Step("Click Process button")
    public OpenAccountPage clickProcess() {
        click(processBtn);
        return this;
    }

    @Step("Get and accept alert text")
    public String getAndAcceptAlertText() {
        return alertGetTextAndAccept();
    }
}