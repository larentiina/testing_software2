package larentina;

import larentina.trigonometric.Cos;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CosTest {

    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);
    private static final BigDecimal PI = new BigDecimal("3.14159265358979323846");
    private static final BigDecimal HALF_PI = PI.divide(BigDecimal.valueOf(2), MC);
    private static final BigDecimal TWO_PI = PI.multiply(BigDecimal.valueOf(2), MC);

    private final Cos cos = new Cos();

    @Test
    void testCosineOfZero() {
        BigDecimal x = BigDecimal.ZERO;
        BigDecimal expected = BigDecimal.ONE; // cos(0) = 1
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }

    @Test
    void testCosineOfPiOverTwo() {
        BigDecimal x = HALF_PI;
        BigDecimal expected = BigDecimal.ZERO; // cos(π/2) = 0
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }

    @Test
    void testCosineOfPi() {
        BigDecimal x = PI;
        BigDecimal expected = new BigDecimal("-1"); // cos(π) = -1
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }

    @Test
    void testCosineOfTwoPi() {
        BigDecimal x = TWO_PI;
        BigDecimal expected = BigDecimal.ONE; // cos(2π) = 1
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }

    @Test
    void testCosineOfPiOverFour() {
        BigDecimal x = PI.divide(BigDecimal.valueOf(4), MC); // π/4
        BigDecimal expected = new BigDecimal("0.7071067812"); // cos(π/4) ≈ 0.7071067812
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }

    @Test
    void testCosineOfRandomValue() {
        BigDecimal x = new BigDecimal("1.234"); // Произвольное значение
        BigDecimal expected = new BigDecimal("0.3304651081"); // cos(1.234) ≈ 0.3304651081
        BigDecimal result = cos.calculate(x, 10);
        assertEquals(expected, result);
    }
}