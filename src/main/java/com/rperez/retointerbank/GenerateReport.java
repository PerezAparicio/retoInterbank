package com.rperez.retointerbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GenerateReport {

    private final ReadCsv readCsv;

    @Autowired
    public GenerateReport(ReadCsv readCsv) {
        this.readCsv = readCsv;
    }

    public void generateReport() throws Exception {
        // 1- Lectura de archivos csv y almacenarlos en una lista
        //      del mismo tipo que se pasa como argumento a readCsv en este caso "Transaction"
        List<Transaction> transactions = readCsv.readCsv(Transaction.class);

        // 2.- De la lista obtenida, hago uso del ApiStream de Java para filtrar por los metodos:
        // filter: El atributo type igualandolo al valor del ENUM.CREDIT que es la constante de "Crédito"
        // map: Transforma el resultado al tipo de dato que tiene una variable que se pasa como argumento
        // reduce: Acumula los resultados de la lista segun como se especifique, en este caso, use la funcion
        //          BigDecimal::add para sumar los datos iniciando el contador por el 0
        BigDecimal finalBalance = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.CREDIT.getType())).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.- De la lista obtenida en el punto 1 utilice el ApiStream de Java usando el metodo
        // max: Se utiliza para obtener el valor maximo de una lista, pasando como argumento el atributo
        //      amount dentro de un Comparartor.comparing.
        // Comparartor.comparing: El método ordena los valores que se le pasa como argumento de forma ascendente.
        Optional<Transaction> maxAmountTransaction = transactions.stream().max(Comparator.comparing(Transaction::getAmount));

        // 4.- De la lista obtenida en el paso 1 utilice el ApiStream de Java usando los métodos:
        //  filter: Igualando el atributo type con el valor del ENUM.CREDIT que es la constante de "Crédito"
        //  count: Hace una cuenta del número de elementos que tiene la lista.
        long maxTransactionCredito = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.CREDIT.getType())).count();
        // 5.- De la lista obtenida en el paso 1 utilice el ApiStream de Java usando los métodos:
        //  filter: Igualando el atributo type con el valor del ENUM.DEBIT que es la constante de "Débito"
        //  count: Hace una cuenta del número de elementos que tiene la lista.
        long maxTransactionDebito = transactions.stream().filter(x -> Objects.equals(x.getType(), TypeTransaction.DEBIT.getType())).count();

        // 6.- Finalmente imprimo los valores en System.out.println
        System.out.println("Reporte de transacciones");
        System.out.println("--------------------------------------------------");
        System.out.println("Balance Final: " + finalBalance);

        // 7.- Utilice una operación ternaria para trabajar con el Optional que obtuve en el paso 3
        //      si el optional tiene valor imprime el resultado si no imprimirá 0
        System.out.println("Transacción de Mayor Monto: " + (maxAmountTransaction.isPresent() ? "ID " + maxAmountTransaction.get().getId() + " - " + maxAmountTransaction.get().getAmount() : 0));
        System.out.println("Conteo de Transacciones: Crédito: " + maxTransactionCredito + " Débito: " + maxTransactionDebito);
    }
}
