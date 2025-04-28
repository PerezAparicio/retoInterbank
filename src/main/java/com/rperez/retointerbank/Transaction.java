package com.rperez.retointerbank;

import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;

/**
 * Representa una transacción financiera.
 * <p>
 * Cada transacción contiene:
 * <ul>
 *     <li><strong>id</strong>: Identificador de la transacción.</li>
 *     <li><strong>type</strong>: Tipo de transacción (por ejemplo, "Crédito" o "Débito").</li>
 *     <li><strong>amount</strong>: Monto asociado a la transacción.</li>
 * </ul>
 *
 * <p>
 * Esta clase está mapeada automáticamente a partir de un archivo CSV usando anotaciones de la librería <strong>univocity-parsers</strong>.
 * </p>
 *
 * @author Ricardo Perez
 */
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
