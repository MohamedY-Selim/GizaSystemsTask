package com.globalsqa.banking.model;

public enum CustomerSection {
    TRANSACTIONS("Transactions"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdraw");

    private final String displayName;

    CustomerSection(String displayName) {
        this.displayName = displayName;
    }

    public static CustomerSection from(String name) {
        String key = name.trim().toLowerCase();
        return switch (key) {
            case "transactions" -> TRANSACTIONS;
            case "deposit" -> DEPOSIT;
            case "withdrawl" -> WITHDRAWAL;
            default -> throw new IllegalArgumentException("Unknown customer section: " + name);
        };
    }

    @Override
    public String toString() {
        return displayName;
    }
}