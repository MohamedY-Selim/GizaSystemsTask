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

    public String getUserName() {
        String prop = properties.getProperty("USERNAME");
        if (prop != null) return prop;
        throw new RuntimeException("Couldn't find the UserName in the property file");
    }

    public String getPassword() {
        String prop = properties.getProperty("PASSWORD");
        if (prop != null) return prop;
        throw new RuntimeException("Couldn't find the password in the property file");
    }

    //    public void setNewUserData(String username, String password) {
//        setNewUserEmail(username);
//        setNewUserPassword(password);
//    }
//
//    public void setNewUserEmail(String username) {
//        properties.setProperty("USERNAME", email);
//        PropertiesUtils.updateProperties("src/test/resources/properties/testing.properties");
//    }
//
//    public void setNewUserPassword(String password) {
//        properties.setProperty("PASSWORD", password);
//        PropertiesUtils.updateProperties("src/test/resources/properties/testing.properties");
//    }
    public static String getRandomChar() {
        char randomChar;
        do {
            randomChar = (char) (random.nextInt(26) + 'a');
        } while (randomChar == 'z' || randomChar == 'x' || randomChar == 'q');

        return String.valueOf(randomChar);
    }

    public static String getRandomUserRole() {
        int index = random.nextInt(ROLES.length);
        return ROLES[index];
    }

    public static String getRandomStatus() {
        int index = random.nextInt(STATUSES.length);
        return STATUSES[index];
    }

    public static String generateStrongPassword() {
        var password = new Faker().internet().password(8, 16, true, false, true);
        if (isPasswordValid(password)) {
            return password;
        }
        return generateStrongPassword();
    }

    private static boolean isPasswordValid(String password) {
        return nonNull(password) && password.length() >= 8 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase);
    }
    public static String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("02");

        for (int i = 0; i < 8; i++) {
            phoneNumber.append(faker.number().digit());
        }

        return phoneNumber.toString();
    }

}
