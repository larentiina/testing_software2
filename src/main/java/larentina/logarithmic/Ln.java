package larentina.logarithmic;

import larentina.function.CalculateWithPrecision;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Ln implements CalculateWithPrecision {
    @Override
    public BigDecimal calculate(BigDecimal x, int precision) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("ln(x) не определён для x <= 0");
        }

        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal one = BigDecimal.ONE;

        // Приводим x к виду 1 + y, где |y| < 1
        int k = 0;
        while (x.compareTo(BigDecimal.valueOf(2)) >= 0) {
            x = x.divide(BigDecimal.valueOf(2), mc);
            k++;
        }

        BigDecimal y = x.subtract(one);
        BigDecimal term = y;
        BigDecimal sum = BigDecimal.ZERO;

        for (int n = 1; n <= precision; n++) {
            BigDecimal fraction = term.divide(BigDecimal.valueOf(n), mc);
            sum = (n % 2 == 0) ? sum.subtract(fraction) : sum.add(fraction);
            term = term.multiply(y, mc);
        }

        // Добавляем k * ln(2)
        BigDecimal ln2 = new BigDecimal("0.6931471805599453"); // Предвычисленное значение ln(2)
        sum = sum.add(ln2.multiply(BigDecimal.valueOf(k)), mc);

        return sum;
    }
}
