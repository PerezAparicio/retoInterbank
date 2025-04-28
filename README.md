## **INTRODUCCIÓN:** 
Este proyecto es una aplicación de línea de código desarrollada como parte del reto.
Su propósito es procesar un archivo CSV con transacciones bancarias y generar un reporte que incluye:
- **Balance final:** Suma de los montos de transacción solo del tipo "Crédito".
- **Transacción de monto mayor:** ID y monto de la transacción con el mayor valor.
- **Conteo de transacciones:** Número de transacciones por el tipo "Crédito" y "Débito".

## **INTRODUCCIONES DE EJECUCIÓN**
### Requisitos:
- Java Development kit (JDK) Temurin 17
- Uno o más archivos CSV con las columnas `id`, `tipo`, `monto`

### Instalación:
1. Clonar el repositorio base:
    ```
    git clone https://github.com/PerezAparicio/retoInterbank.git
    ```
2. Importar el proyecto a un Java IDE.

### Ejecución:

1. Colocar el archivo CSV en la carpeta src/main/resources/csv del proyecto.
2. Compilar y ejecutar el programa.
3. La aplicación mostrará un reporte en la consola. Ejemplo de salida:
    ```
    Reporte de Transacciones
    ---------------------------------------------
    Balance Final: 300.00
    Transacción de Mayor Monto: ID 1 - 200.00
    Conteo de Transacciones: Crédito: 5 Débito: 10
    ```

## ENFOQUE Y SOLUCIÓN

### Lógica Implementada:
- **Lectura del CSV:** Se utiliza la librería Univocity para leer el archivo CSV, parseando cada fila en un objeto con los campos `id`, `tipo` y `monto`.
- **Procesamiento:**
  - **Balance final:** Se suma los montos de transacción solo del tipo "Crédito".
  - **Transacción de monto mayor:** Se compara el monto de cada transacción para identificar la del mayor valor, almacenando el `id` y `monto`.
  - **Conteo de transacciones:** Se utilizan contadores para registrar el número de transacciones de tipo "Crédito" y "Débito".
- **Reporte:** Se genera una salida formateada en la consola con los resultados, siguiendo el formateo especificado. 

### Decisiones de Diseño:
- **Lenguaje**: Se eligió Java por su robustez, tipado fuerte y amplio soporte en aplicaciones empresariales.
- **Librería Univocity**: Se seleccionó Univocity Parsers por su eficiencia y flexibilidad en el manejo de archivos CSV, permitiendo un parseo confiable y sencillo, además de facilitar la lectura de archivos pesados.
- **Modularidad**: El código se organizó en clases y métodos específicos para facilitar el mantenimiento y la escalabilidad.
- **Manejo de Dependencias**: Se usó una única dependencia externa (Univocity) para minimizar la complejidad de configuración.
- **Formato de Salida**: Se respetó estrictamente el formato de salida especificado para cumplir con los requisitos del reto.


### Estructura del Proyecto
```
project/
├── src/
    └── RetoInterbankApplication.java
    └── GenerateReporte.java 
    └── ReadCsv.java
    └── Transaction.java
    └── TypeTransaction.java
├── resources/
    ├── csv/
        └── data.csv
├── pom.xml
├── README.md
```