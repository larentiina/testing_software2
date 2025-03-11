package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cos implements CalculateWithPrecision {

    @Override
    public BigDecimal calculate(BigDecimal x, int precision) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal term;
        BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-precision);
        MathContext mc = new MathContext(precision + 2, RoundingMode.HALF_UP);

        int i = 0;
        do {
            term = BigDecimal.valueOf(-1).pow(i)
                    .multiply(x.pow(2 * i, mc))
                    .divide(factorial(2 * i), mc);
            result = result.add(term, mc);
            i++;
        } while (term.abs().compareTo(tolerance) > 0);

        return result.round(new MathContext(precision, RoundingMode.HALF_UP));
    }

    private BigDecimal factorial(int num) {
        if (num == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= num; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }
}