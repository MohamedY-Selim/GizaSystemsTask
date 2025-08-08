package com.globalsqa.banking.hooks;

import com.globalsqa.banking.factory.DriverFactory;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hooks {

    private static WebDriver driver;

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";

    @BeforeAll
    public static void setup() {
        driver = DriverFactory.initializeDriver();
    }

    @After
    @Step("Log scenario result and capture screenshot")
    public void afterScenario(Scenario scenario) {
        if (driver == null) return;

        String scenarioName = scenario.getName();
        boolean passed = !scenario.isFailed();

        if (passed) {
            System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + scenarioName + " Scenario Succeed" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + scenarioName + " Scenario Failed" + ANSI_RESET);
        }

        File destFile = new File("target/screenshots/" + (passed ? "passed_" : "failed_") + sanitize(scenarioName) + ".png");
        takeScreenshot(destFile);

        try {
            List<String> allWindows = new ArrayList<>(driver.getWindowHandles());
            if (!allWindows.isEmpty()) {
                for (int i = 1; i < allWindows.size(); i++) {
                    driver.switchTo().window(allWindows.get(i));
                    driver.close();
                }
                driver.switchTo().window(allWindows.getFirst());
            }
        } catch (Exception ignored) {
        }

        if (scenario.getSourceTagNames().contains("Initial")) {
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception ignored) {
            }
        }
    }

    @AfterAll
    @Step("Driver quit")
    public static void tearDown() {
        DriverFactory.quitDriver();
        driver = null;
    }

    private static String sanitize(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    private void takeScreenshot(File destFile) {
        try {
            if (driver == null) return;
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.forceMkdirParent(destFile);
            FileUtils.copyFile(src, destFile);

            try (InputStream is = new FileInputStream(destFile)) {
                Allure.addAttachment("screenshot", is);
            }
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error capturing screenshot: " + e.getMessage());
        }
    }
}