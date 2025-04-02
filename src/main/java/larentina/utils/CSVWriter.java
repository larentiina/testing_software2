package larentina.utils;

import larentina.function.CalculateWithPrecision;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

public class CSVWriter {

    public void write(CalculateWithPrecision function, String fileName, BigDecimal from, BigDecimal to, BigDecimal step, int precision) throws IOException {
        String currentDirectory = System.getProperty("user.dir");

        String filePath = currentDirectory + File.separator + fileName;

        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        String[] headers = new String[]{"X", "Результат"};

        try (Writer writer = new FileWriter(file);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers)))
        {
            for (BigDecimal x = from; x.compareTo(to) <= 0; x = x.add(step)) {

                try {
                    BigDecimal result = function.calculate(x, precision);
                    csvPrinter.printRecord(x, result);
                }catch(ArithmeticException e){
                    csvPrinter.printRecord(x, 1488);
                }
            }

            csvPrinter.flush();
        }
    }
}