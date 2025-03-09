package larentina.logarithmic;

import larentina.function.CalculateWithPrecision;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Log2 implements CalculateWithPrecision {
    private final Ln ln;
    @Override
    public BigDecimal calculate(BigDecimal a, int precision) {
        BigDecimal lnX = ln.calculate(a, precision);
        BigDecimal ln2 = ln.calculate(BigDecimal.valueOf(2), precision);
        return lnX.divide(ln2, precision, BigDecimal.ROUND_HALF_UP);
    }
}
