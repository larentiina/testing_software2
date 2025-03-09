package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Csc implements CalculateWithPrecision {

    private final Sin sin;
    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal sinX = sin.calculate(a,precision);
        if(sinX.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Divided by zero");
        return BigDecimal.ONE.divide(sinX,precision,BigDecimal.ROUND_HALF_UP);
    }
}
