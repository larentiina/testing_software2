package larentina;

import larentina.function.FunctionSystem;

import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;
import larentina.trigonometric.Cos;
import larentina.trigonometric.Sin;
import larentina.utils.CSVWriter;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        new FunctionSystem(new TrigFunctions(),new LogFunctions()).calculate(BigDecimal.valueOf(10),15);
////        System.out.println(new Ln().calculate(BigDecimal.valueOf(1),15));
//
//        CSVWriter csvWriter = new CSVWriter();
//        Sin sinFunction = new Sin(new Cos());
//
//        try {
//            csvWriter.write(
//                    new FunctionSystem(new TrigFunctions(),new LogFunctions()),
//                    "SinResults.csv", // Имя файла в текущей директории
//                    BigDecimal.valueOf(-1.5), // from
//                    BigDecimal.valueOf(-1), // to
//                    BigDecimal.valueOf(0.1), // step
//                    5 // precision
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
