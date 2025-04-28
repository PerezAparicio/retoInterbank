package com.rperez.retointerbank;

import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;

public class Transaction {

    @Parsed(field = "id")
    private long id;

    @Parsed(field = "tipo")
    private String type;

    @Parsed(field = "monto")
    private BigDecimal amount;

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
