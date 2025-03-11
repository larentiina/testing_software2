package larentina;

import larentina.function.FunctionSystem;
import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;
import larentina.logarithmic.Log2;
import larentina.logarithmic.Log5;
import larentina.trigonometric.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

public class FunctionTestLogarithmic {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    static Stream<Arguments> provideNumbers(){
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(0.2),-0.840),
                Arguments.of(BigDecimal.valueOf(0.76627),0.000),
                Arguments.of(BigDecimal.valueOf(2),0.306)
        );
    }

    static Stream<Arguments> provideNumbersOutOfDomain(){
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1))
        );
    }
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testFunction3lvl(BigDecimal number, double expected) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln lnMock = Mockito.mock(Ln.class);
        Log10 log10Mock = Mockito.mock(Log10.class);
        Log2 log2Mock = Mockito.mock(Log2.class);
        Log5 log5Mock = Mockito.mock(Log5.class);


        when(lnMock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue()));
                });
        when(log10Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log10(arg.doubleValue()));
                });
        when(log2Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue())/Math.log(2));
                });
        when(log5Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue())/Math.log(5));
                });

        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10Mock, log2Mock, log5Mock, lnMock);
        BigDecimal result = functionSystem.calculate(number, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideNumbersOutOfDomain")
    void testFunction3lvlOufOfDomain(BigDecimal angle) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln lnMock = Mockito.mock(Ln.class);
        Log10 log10Mock = Mockito.mock(Log10.class);
        Log2 log2Mock = Mockito.mock(Log2.class);
        Log5 log5Mock = Mockito.mock(Log5.class);


        when(lnMock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue()));
                });
        when(log10Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log10(arg.doubleValue()));
                });
        when(log2Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue())/Math.log(2));
                });
        when(log5Mock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue())/Math.log(5));
                });


        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10Mock, log2Mock, log5Mock, lnMock);
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testFunction2lvl(BigDecimal number, double expected) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln lnMock = Mockito.mock(Ln.class);
        Log10 log10 = new Log10(lnMock);
        Log2 log2 = new Log2(lnMock);
        Log5 log5 = new Log5(lnMock);


        when(lnMock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue()));
                });

        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, lnMock);
        BigDecimal result = functionSystem.calculate(number, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideNumbersOutOfDomain")
    void testFunction2lvlOufOfDomain(BigDecimal angle) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln lnMock = Mockito.mock(Ln.class);
        Log10 log10 = new Log10(lnMock);
        Log2 log2 = new Log2(lnMock);
        Log5 log5 = new Log5(lnMock);


        when(lnMock.calculate(Mockito.any(), Mockito.eq(15)))
                .thenAnswer(invocation -> {
                    BigDecimal arg = invocation.getArgument(0);
                    return BigDecimal.valueOf(Math.log(arg.doubleValue()));
                });


        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, lnMock);
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testFunction1lvl(BigDecimal number, double expected) {

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);

        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        BigDecimal result = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, ln).calculate(number, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideNumbersOutOfDomain")
    void testFunction1lvlOufOfDomain(BigDecimal angle) {
        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);

        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, ln);
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }
}
