package larentina.trigonometric;

import larentina.function.CalculateWithPrecision;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class Sin  implements CalculateWithPrecision {
    private final Cos cos;

    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal pi = BigDecimal.valueOf(Math.PI);
        BigDecimal twoPi = pi.multiply(BigDecimal.valueOf(2));

        // Приводим угол к диапазону [-π, π]
        BigDecimal angle = a.remainder(twoPi);
        if (angle.compareTo(pi.negate()) < 0) {
            angle = angle.add(twoPi);
        }

        BigDecimal cosX = cos.calculate(a, precision);
        BigDecimal sinX = BigDecimal.ONE.subtract(cosX.pow(2)).sqrt(new MathContext(precision, RoundingMode.HALF_UP));

        // Определяем знак синуса: отрицательный, если угол в [-π, 0]
        return angle.compareTo(BigDecimal.ZERO) < 0 ? sinX.negate() : sinX;
    }
}
