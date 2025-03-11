package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Csc implements CalculateWithPrecision {

    private final Sin sin;
    private static final BigDecimal EPSILON = new BigDecimal("1E-10");

    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal sinX = sin.calculate(a,precision);
        if (sinX.abs().compareTo(EPSILON) < 0) {
            throw new ArithmeticException("Divided by zero");
        }
        return BigDecimal.ONE.divide(sinX,precision,BigDecimal.ROUND_HALF_UP);
    }
}
