package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Sec implements CalculateWithPrecision {
    private final Cos cos;
    private static final BigDecimal EPSILON = new BigDecimal("1E-10");

    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal cosX = cos.calculate(a, precision);

        if (cosX.abs().compareTo(EPSILON) < 0) {
            throw new ArithmeticException("Divided by zero");
        }
        // sec(x) = 1/cos(x)
        return BigDecimal.ONE.divide(cosX, precision, RoundingMode.HALF_UP);
    }
}
