package com.globalsqa.banking.factory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Locale;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver initializeDriver() {
        if (getDriver() != null) {
            return getDriver();
        }

        String browser = System.getProperty("browser", "CHROME");
        String headless = System.getProperty("headless", "false");
        long implicitSec = Long.parseLong(System.getProperty("implicitWait", "5"));

        WebDriver driver;
        switch (browser.toUpperCase(Locale.ROOT)) {
            case "CHROME" -> {
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (Boolean.parseBoolean(headless)) {
                    options.addArguments("--headless=new");
                }
                driver = new ChromeDriver(options);
            }
            case "FIREFOX" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (Boolean.parseBoolean(headless)) {
                    options.addArguments("-headless");
                }
                driver = new FirefoxDriver(options);
            }
            case "EDGE" -> {
                EdgeOptions options = new EdgeOptions();
                if (Boolean.parseBoolean(headless)) {
                    options.addArguments("--headless=new");
                }
                driver = new EdgeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitSec));
        driver.manage().window().maximize();
        DRIVER.set(driver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver d = DRIVER.get();
        if (d != null) {
            try {
                d.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }
}