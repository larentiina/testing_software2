package larentina.logarithmic;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@RequiredArgsConstructor
public class Log5 implements CalculateWithPrecision {
    private final Ln ln;    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal lnX = ln.calculate(a, precision);
        BigDecimal ln5 = ln.calculate(BigDecimal.valueOf(5), precision);
        return lnX.divide(ln5, precision, BigDecimal.ROUND_HALF_UP);
    }
}
