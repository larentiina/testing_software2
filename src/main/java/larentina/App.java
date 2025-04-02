package larentina;

import larentina.function.FunctionSystem;

import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;
import larentina.logarithmic.Log2;
import larentina.logarithmic.Log5;
import larentina.trigonometric.*;
import larentina.utils.CSVWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class App
{
    public static void main( String[] args ) throws IOException {

        CSVWriter csvWriter = new CSVWriter();
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        FunctionSystem system = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, ln);
//
//
//            csvWriter.write(
//                   sin,
//                    "Sin.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(2*Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//            csvWriter.write(
//                    cos,
//                    "Cos.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(2*Math.PI),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
//            csvWriter.write(
//                    tan,
//                    "Tan.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(0),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
//            csvWriter.write(
//                    sec,
//                    "Sec.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//            csvWriter.write(
//                    csc,
//                    "Csc.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//
//            csvWriter.write(
//                    ln,
//                    "Ln.csv",
//                    BigDecimal.valueOf(0),
//                    BigDecimal.valueOf(5),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
            csvWriter.write(
                    log2,
                    "log2.csv",
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(5),
                    BigDecimal.valueOf(0.1),
                    5
            );
            csvWriter.write(
                    log5,
                    "log5.csv",
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(5),
                    BigDecimal.valueOf(0.1),
                    5
            );
            csvWriter.write(
                    log10,
                    "log10.csv",
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(5),
                    BigDecimal.valueOf(0.1),
                    5
            );

//            csvWriter.write(
//                    system,
//                    "Function.csv",
//                    BigDecimal.valueOf(-3*Math.PI),
//                    BigDecimal.valueOf(2),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );


    }
    }
