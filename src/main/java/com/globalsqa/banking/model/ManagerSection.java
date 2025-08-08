package com.globalsqa.banking.model;

public enum ManagerSection {
    ADD_CUSTOMER("Add Customer"),
    OPEN_ACCOUNT("Open Account"),
    CUSTOMERS("Customers");

    private final String label;
    ManagerSection(String label) { this.label = label; }
    public String getLabel() { return label; }

    public static ManagerSection from(String text) {
        String key = text == null ? "" : text.trim();
        for (ManagerSection s : values()) {
            if (s.label.equalsIgnoreCase(key)) return s;
        }
        throw new IllegalArgumentException("Unknown section: " + text);
    }
}