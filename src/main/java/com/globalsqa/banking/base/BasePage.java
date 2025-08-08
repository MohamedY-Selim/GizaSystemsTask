package com.globalsqa.banking.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage<T extends BasePage<T>> {

    // Driver
    protected final WebDriver driver;

    // Variables
    private final Duration defaultWait = Duration.ofSeconds(15);

    // Constructor
    public BasePage(WebDriver driver) { this.driver = driver; }

    // Methods
    public T load() { return (T) this; }

    public String getCurrentPageUrl() { return driver.getCurrentUrl(); }

    public WebDriverWait explicitWait() { return new WebDriverWait(driver, defaultWait); }

    public Actions actions() { return new Actions(driver); }

    public WebElement waitForVisible(By locator) { return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator)); }

    public WebElement waitForClickable(By locator) { return explicitWait().until(ExpectedConditions.elementToBeClickable(locator)); }

    public void click(By locator) { waitForClickable(locator).click(); }

    public void type(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public boolean isDisplayed(By locator) {
        try { return waitForVisible(locator).isDisplayed(); }
        catch (TimeoutException e) { return false; }
    }

    public void selectFromDropdown(By selectLocator, String visibleText) {
        new Select(waitForVisible(selectLocator)).selectByVisibleText(visibleText);
    }

    // Alerts
    public Alert waitForAlert() { return explicitWait().until(ExpectedConditions.alertIsPresent()); }

    public String alertGetTextAndAccept() {
        Alert a = waitForAlert();
        String text = a.getText();
        a.accept();
        return text;
    }

    public void alertAccept() { waitForAlert().accept(); }

    public void alertDismiss() { waitForAlert().dismiss(); }

    public boolean isAlertPresent() {
        try { explicitWait().until(ExpectedConditions.alertIsPresent()); return true; }
        catch (TimeoutException e) { return false; }
    }
}