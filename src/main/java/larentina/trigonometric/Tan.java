package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Tan implements CalculateWithPrecision {
    private final Sin sin;
    private final Cos cos;

    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal cosX = cos.calculate(a, precision);
        if(cosX.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Divided by zero");
        BigDecimal tanX = sin.calculate(a, precision).divide(cosX, precision, RoundingMode.HALF_UP);
        return tanX;
    }
}
