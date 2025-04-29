package com.rperez.retointerbank;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase encargada de leer y procesar archivos CSV.
 *
 * @author Ricardo Perez
 */
@Service
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

        BeanListProcessor<T> processor = new BeanListProcessor<>(model);

        CsvParserSettings settings = new CsvParserSettings();

        settings.setHeaderExtractionEnabled(true);
        settings.setProcessor(processor);
        settings.setLineSeparatorDetectionEnabled(true);

        File filesCsv = new File(pathCsv);

        if (!filesCsv.exists()) {
            return Collections.emptyList();
        }

        File[] files = filesCsv.listFiles((dir, name) -> name.endsWith(".csv"));

        if (Objects.isNull(files) || files.length == 0) {
            return Collections.emptyList();
        }

        List<T> allResult = new ArrayList<>();

        CsvParser parser = new CsvParser(settings);

        for (File csv : files) {
            try (FileReader fileReader = new FileReader(csv)) {
                parser.parse(fileReader);
            }
            allResult.addAll(processor.getBeans());
        }

        return allResult;
    }

}
