package com.globalsqa.banking.utils;

import com.github.javafaker.Faker;

import java.util.Properties;
import java.util.Random;

import static java.util.Objects.nonNull;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;
    private static final Random random = new Random();
    private static final Faker faker = new Faker();
    private static final String[] ROLES = {"Admin", "ESS"};
    private static final String[] STATUSES = {"Enabled", "Disabled"};


    private ConfigUtils() {
        String env = System.getProperty("env", "TESTING");
        switch (env) {
            case "TESTING" ->
                    properties = PropertiesUtils.loadProperties("src/main/resources/properties/testing.properties");
            case "STAGING" ->
                    properties = PropertiesUtils.loadProperties("src/main/resources/properties/staging.properties");
            case "PRODUCTION" ->
                    properties = PropertiesUtils.loadProperties("src/main/resources/properties/production.properties");
            default -> throw new RuntimeException("Environment isn't supported");
        }
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("URL");
        if (prop != null) return prop;
        throw new RuntimeException("Couldn't find the base url in the property file");
    }
}
