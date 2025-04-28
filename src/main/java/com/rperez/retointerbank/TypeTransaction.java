package com.rperez.retointerbank;

public enum TypeTransaction {

    CREDIT("Crédito"),
    DEBIT("Débito");

    private final String type;

    TypeTransaction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
