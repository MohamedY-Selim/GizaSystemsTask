package com.globalsqa.banking.pages.customer;

import com.globalsqa.banking.base.BasePage;
import com.globalsqa.banking.factory.EndPoint;
import com.globalsqa.banking.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.List;

public class TransactionsPage extends BasePage<TransactionsPage> {

    // Constructor
    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By container = By.cssSelector("div.marTop.tbStruct.border.box");
    private final By startInput = By.cssSelector("input#start[type='datetime-local']");
    private final By endInput = By.cssSelector("input#end[type='datetime-local']");
    private final By tableRows = By.cssSelector("table.table tbody tr");

    // Methods
    @Step("Load Transactions page")
    public TransactionsPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TRANSACTIONS_PAGE_END_POINT);
        waitForVisible(container);
        return this;
    }

    @Step("Set date range from yesterday to today")
    public TransactionsPage setDateRangeToToday() {
        LocalDateTime yesterdayStart = LocalDateTime.now()
                .minusDays(1)
                .withHour(0).withMinute(0).withSecond(0);

        LocalDateTime todayEnd = LocalDateTime.now().plusDays(1)
                .withHour(0).withMinute(0).withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        type(startInput, yesterdayStart.format(formatter));
        type(endInput, todayEnd.format(formatter));

        return this;
    }

    @Step("Check if transaction exists: amount={amount}, type={type}")
    public boolean transactionExists(String amount, String type) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement r : rows) {
            List<WebElement> tds = r.findElements(By.cssSelector("td"));
            if (tds.size() < 3) continue;
            String amt = tds.get(1).getText().trim();
            String kind = tds.get(2).getText().trim();
            if (amt.equals(amount.trim()) && kind.equalsIgnoreCase(type.trim())) return true;
        }
        return false;
    }

    @Step("Wait until transaction appears within {timeout} seconds")
    public boolean waitUntilTransactionVisible(String amount, String type, Duration timeout) {
        try {
            explicitWait().withTimeout(timeout).until((ExpectedCondition<Boolean>) d -> transactionExists(amount, type));
            return true;
        } catch (TimeoutException te) {
            return false;
        }
    }

    @Step("Ensure transaction is visible after deposit with one retry")
    public boolean ensureTransactionAfterDeposit(String amount, String type) {
        if (waitUntilTransactionVisible(amount, type, Duration.ofSeconds(1))) return true;
        driver.navigate().refresh();
        waitForVisible(container);
        return waitUntilTransactionVisible(amount, type, Duration.ofSeconds(1));
    }
}