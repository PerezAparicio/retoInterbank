package com.rperez.retointerbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Clase encargada de generar reportes de transacciones en consola a partir de un archivo CSV.
 *
 * @author Ricardo Perez
 */
@Component
public class GenerateReport {

    private final ReadCsv readCsv;

    @Autowired
    public GenerateReport(ReadCsv readCsv) {
        this.readCsv = readCsv;
    }

    /**
     * Genera un reporte de transacciones basado en un archivo CSV.
     * <p>
     * El reporte incluye:
     * <ul>
     *     <li>Balance final de todas las transacciones de tipo crédito.</li>
     *     <li>La transacción con el monto más alto.</li>
     *     <li>Conteo de transacciones por tipo: Crédito y Débito.</li>
     * </ul>
     * </p>
     *
     * @throws Exception Si ocurre un error durante la lectura del archivo CSV.
     */
    public void generateReport() throws Exception {
        
        List<Transaction> transactions = readCsv.readCsv(Transaction.class);

        BigDecimal finalBalance = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.CREDIT.getType())).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        Optional<Transaction> maxAmountTransaction = transactions.stream().max(Comparator.comparing(Transaction::getAmount));

        long maxTransactionCredito = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.CREDIT.getType())).count();

        long maxTransactionDebito = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.DEBIT.getType())).count();

        System.out.println("Reporte de transacciones");
        System.out.println("--------------------------------------------------");
        System.out.println("Balance Final: " + finalBalance);
        System.out.println("Transacción de Mayor Monto: " + (maxAmountTransaction.isPresent() ? "ID " + maxAmountTransaction.get().getId() + " - " + maxAmountTransaction.get().getAmount() : 0));
        System.out.println("Conteo de Transacciones: Crédito: " + maxTransactionCredito + " Débito: " + maxTransactionDebito);
    }
}
