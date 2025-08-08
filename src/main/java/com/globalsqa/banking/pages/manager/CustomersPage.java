package com.globalsqa.banking.pages.manager;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomersPage extends BasePage<CustomersPage> {

    // Constructor
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By searchInput = By.cssSelector("input[ng-model='searchCustomer']");
    private final By tableRows = By.cssSelector("table tbody tr");
    private final By firstNameColH = By.xpath("//thead//a[normalize-space()='First Name']");
    private final By lastNameColH = By.xpath("//thead//a[normalize-space()='Last Name']");
    private final By postCodeColH = By.xpath("//thead//a[normalize-space()='Post Code']");

    // Methods
    @Step("Load Customers page")
    public CustomersPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.CUSTOMERS_LIST_PAGE_END_POINT);
        waitForVisible(searchInput);
        return this;
    }

    @Step("Type in customer search: {text}")
    public CustomersPage typeSearch(String text) {
        type(searchInput, text);
        return this;
    }

    @Step("Get visible table rows")
    public List<WebElement> visibleRows() {
        return driver.findElements(tableRows);
    }

    @Step("Get column values (1-based index): {colIndex1Based}")
    public List<String> getColumnValues(int colIndex1Based) {
        List<String> vals = new ArrayList<>();
        for (WebElement r : visibleRows()) {
            List<WebElement> tds = r.findElements(By.cssSelector("td"));
            if (tds.size() >= colIndex1Based) {
                vals.add(tds.get(colIndex1Based - 1).getText().trim());
            }
        }
        return vals;
    }

    @Step("Sort by First Name")
    public CustomersPage sortByFirstName() {
        click(firstNameColH);
        return this;
    }

    @Step("Sort by Last Name")
    public CustomersPage sortByLastName() {
        click(lastNameColH);
        return this;
    }

    @Step("Sort by Post Code ascending")
    public CustomersPage sortByPostCodeAscending() {
        click(postCodeColH);
        if (!isSortedAsc(getPostcodes())) {
            click(postCodeColH);
        }
        return this;
    }

    @Step("Sort by Post Code descending")
    public CustomersPage sortByPostCodeDescending() {
        click(postCodeColH);
        if (!isSortedDesc(getPostcodes())) {
            click(postCodeColH);
        }
        return this;
    }

    @Step("Get all postcodes")
    public List<String> getPostcodes() {
        return getColumnValues(3);
    }

    private boolean isSortedAsc(List<String> list) {
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(String::compareToIgnoreCase);
        return list.equals(sorted);
    }

    private boolean isSortedDesc(List<String> list) {
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(String::compareToIgnoreCase);
        Collections.reverse(sorted);
        return list.equals(sorted);
    }

    @Step("Delete first visible customer row")
    public CustomersPage deleteFirstVisibleRow() {
        List<WebElement> rows = visibleRows();
        if (!rows.isEmpty()) {
            rows.getFirst().findElement(By.cssSelector("button[ng-click='deleteCust(cust)']")).click();
        }
        return this;
    }

    @Step("Delete customer by first name: {firstName}")
    public CustomersPage deleteCustomerByFirstName(String firstName) {
        for (WebElement row : visibleRows()) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            if (!cells.isEmpty() && cells.getFirst().getText().trim().equalsIgnoreCase(firstName)) {
                row.findElement(By.cssSelector("button[ng-click='deleteCust(cust)']")).click();
                break;
            }
        }
        return this;
    }
}