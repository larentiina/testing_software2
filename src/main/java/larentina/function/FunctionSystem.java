package larentina.function;

import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;
import larentina.logarithmic.Log2;
import larentina.logarithmic.Log5;
import larentina.trigonometric.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionSystem {

    private final TrigFunctions trigFunctions = new TrigFunctions();
    private final LogFunctions logFunctions = new LogFunctions();

    public BigDecimal solveSystem(BigDecimal a, int precision) {
        Cos cos = trigFunctions.cos();
        Sin sin = trigFunctions.sin();
        Tan tan = trigFunctions.tan();
        Csc csc = trigFunctions.csc();
        Sec sec = trigFunctions.sec();

        Log2 log2 = logFunctions.log2();
        Log5 log5 = logFunctions.log5();
        Log10 log10 = logFunctions.log10();
        Ln ln = logFunctions.ln();

        BigDecimal result;
        if (a.compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal firstlog = log10.calculate(a,precision)
                    .pow(2)
                    .add(log10.calculate(a,precision))
                    .add(log2.calculate(a,precision).multiply(ln.calculate(a,precision)));

            BigDecimal secondlog = log5.calculate(a,precision)
                    .divide(log2.calculate(a,precision),precision,RoundingMode.HALF_UP)
                    .divide(ln.calculate(a,precision),precision,RoundingMode.HALF_UP);

            result = firstlog.multiply(secondlog).pow(3);
        }
        else {
            BigDecimal firstPath = cos.calculate(a, precision)
                    .pow(3)
                    .divide(csc.calculate(a, precision), precision, RoundingMode.HALF_UP)
                    .multiply(sec.calculate(a, precision))
                    .add(tan.calculate(a, precision));
            BigDecimal secondPath = cos.calculate(a, precision).add(sin.calculate(a, precision)).pow(3);

           result = firstPath.multiply(secondPath);

        }

        return result;

    }
}
