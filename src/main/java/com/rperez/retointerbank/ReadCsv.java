package com.rperez.retointerbank;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Ricardo Perez
 */
@Component
public class ReadCsv {

    /**
     * Ruta donde se almacenan los archivos CSV a procesar.
     */
    @Value("${local.path-csv}")
    private String pathCsv;


    /**
     * Lee archivos CSV desde un directorio específico y convierte su contenido en una lista de objetos del tipo especificado.
     * <p>
     * Este método:
     * <ul>
     *     <li>Busca todos los archivos con extensión <code>.csv</code> en el directorio configurado.</li>
     *     <li>Lee cada archivo usando <code>CsvParser</code> y convierte cada fila en una instancia de la clase proporcionada.</li>
     *     <li>Combina todos los resultados en una sola lista.</li>
     * </ul>
     *
     * @param model La clase del modelo al que se deben mapear los datos del CSV.
     * @param <T> El tipo de objeto que representa cada fila del CSV.
     * @return Una lista de objetos de tipo <code>T</code> mapeados desde los archivos CSV; si no existen archivos, devuelve una lista vacía.
     * @throws Exception Si ocurre un error en la lectura o el procesamiento de los archivos CSV.
     *
     */
    public <T> List<T> readCsv(Class<T> model) throws Exception {

        // 1.- Se instancia el BeanListProcessor de Tipo model
        // BeanListProcessor: Sirve para convertir datos a objetos almacenandolos en una lista.
        // El objeto lo determina en este caso el argumento model.
        BeanListProcessor<T> processor = new BeanListProcessor<>(model);

        // 2.- Se instancia el CsvParserSettings
        // CsvParserSettings: Sirve para configurar el comportamiento del componente CsvParser,
        // controla como se lee el archivo CSV y como se procesan los datos.
        CsvParserSettings settings = new CsvParserSettings();

        // Activa la lectura de la primera fila del archivo y lo toma como encabezado y así mapear
        // las columnas con la del objeto
        settings.setHeaderExtractionEnabled(true);

        // Aqui es donde se enlaza el BeanListProcessor para que pueda almacenarse los datos procesados.
        settings.setProcessor(processor);

        // Habilita la deteccion automatica del separador de lineas
        settings.setLineSeparatorDetectionEnabled(true);

        // 3.- Obtengo la dirección de mi ruta donde van a estar mis archivos csv
        File filesCsv = new File(pathCsv);

        // 4.- Valido si la ruta existe o no
        if (!filesCsv.exists()) {
            return Collections.emptyList();
        }

        // 5.- Listo los archivos dentro de la ruta con el cuyos nombre de archivos terminen con la extensión .csv
        File[] files = filesCsv.listFiles((dir, name) -> name.endsWith(".csv"));

        // 6.- Valido si files es nulo ó si la cantidad de elementos que contiene es 0
        if (Objects.isNull(files) || files.length == 0) {
            return Collections.emptyList();
        }

        // 7.- Instancio una lista vacia de tipo Genérica.
        List<T> allResult = new ArrayList<>();

        //8.- Instancio la clase CsvParser: se utiliza para leer y analizar los datos de un archivo csv
        //      usando una configuracion que se determino en pasos anteriores.
        CsvParser parser = new CsvParser(settings);

        //9.- Iterar los archivos dentro de mi ruta
        for (File csv : files) {
            //10.- Leer el archivo
            try (FileReader fileReader = new FileReader(csv)) {
                //11.- Parsear el contenido del archivo csv a la de la variable de BeanListProcessor
                parser.parse(fileReader);
            }
            //12.- El resultado del parseo de datos se almacena en la variable allResult
            //      Esto para ir almacenando los datos de cada archivo ya que la librería
            //      por cada iteracion libera el espacio en el BeanListProcessor
            allResult.addAll(processor.getBeans());
        }

        // 13.- Devuelvo todos los resultados de mi lectura a los arhcivos .csv
        return allResult;
    }

}
