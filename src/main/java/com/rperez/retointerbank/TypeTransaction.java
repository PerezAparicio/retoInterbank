package com.rperez.retointerbank;

/**
 * Enum que representa los tipos de transacciones financieras.
 * <p>
 * Cada tipo de transacción tiene una representación en texto que será utilizada para comparar o mostrar información.
 * </p>
 *
 * <ul>
 *     <li><strong>CREDIT</strong>: Representa una transacción de tipo "Crédito".</li>
 *     <li><strong>DEBIT</strong>: Representa una transacción de tipo "Débito".</li>
 * </ul>
 *
 * @author Ricardo Perez
 */
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
