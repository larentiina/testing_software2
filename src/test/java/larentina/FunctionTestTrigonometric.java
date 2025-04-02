package larentina.function;

import larentina.logarithmic.*;
import larentina.trigonometric.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static java.lang.Math.PI;
import static org.mockito.Mockito.*;


public class FunctionTestTrigonometric {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    static Stream<Arguments> provideAngles(){
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(-PI/32),-0.141),
                Arguments.of(BigDecimal.valueOf(-PI/16),-0.187),
                Arguments.of(BigDecimal.valueOf(-PI/8),-0.117),
                Arguments.of(BigDecimal.valueOf(-PI/4),0.000),
                Arguments.of(BigDecimal.valueOf(-PI/3),0.096),
                Arguments.of(BigDecimal.valueOf(-3*PI/4),-1.828),
                Arguments.of(BigDecimal.valueOf(-13*PI/12),0.009),
                Arguments.of(BigDecimal.valueOf(-9*PI/8),0.014),
                Arguments.of(BigDecimal.valueOf(-7*PI/6),0.010),
                Arguments.of(BigDecimal.valueOf(-5*PI/4),0.000),
                Arguments.of(BigDecimal.valueOf(-4*PI/3),-0.074),
                Arguments.of(BigDecimal.valueOf(-5*PI/3),4.967)
        );
    }

    static Stream<Arguments> provideAnglesOutOfDomain(){
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(-PI)), //cos(x) = -1
                Arguments.of(BigDecimal.valueOf(-2*PI)),  // cos(x) = 1
                Arguments.of(BigDecimal.valueOf(0.0)),  // cos(x) = 1
                Arguments.of(BigDecimal.valueOf(-PI/2)) //cos(x) = 0
        );
    }






    @ParameterizedTest
    @MethodSource("provideAngles")
    void testFunction2lvl(BigDecimal angle, double expected) {
        Cos cosMock = Mockito.mock(Cos.class);
        Sin sinMock = Mockito.mock(Sin.class);
        Sec secMock = Mockito.mock(Sec.class);
        Tan tan = new Tan(sinMock, cosMock);
        Csc csc = new Csc(sinMock);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


       when(cosMock.calculate(angle, 15)).thenAnswer(invocation -> getCosValue(angle));
        when(sinMock.calculate(angle, 15)).thenAnswer(invocation -> getSinValue(angle));


        FunctionSystem functionSystem = new FunctionSystem(cosMock, sinMock, tan, secMock, csc, log10, log2, log5, ln);
        BigDecimal result = functionSystem.calculate(angle, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideAnglesOutOfDomain")
    void testFunction2lvlOufOfDomain(BigDecimal angle) {
        Cos cosMock = Mockito.mock(Cos.class);
        Sin sinMock = Mockito.mock(Sin.class);
        Sec secMock = Mockito.mock(Sec.class);
        Tan tan = new Tan(sinMock, cosMock);
        Csc csc = new Csc(sinMock);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        when(cosMock.calculate(angle, 15)).thenAnswer(invocation -> getCosValue(angle));
        when(sinMock.calculate(angle, 15)).thenAnswer(invocation -> getSinValue(angle));
        when(secMock.calculate(angle, 15)).thenAnswer(invocation -> getSecValue(angle));


        FunctionSystem functionSystem = new FunctionSystem(cosMock, sinMock, tan, secMock, csc, log10, log2, log5, ln);

        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }


    @ParameterizedTest
    @MethodSource("provideAngles")
    void testFunction1lvl(BigDecimal angle, double expected) {
        Cos cosMock = Mockito.mock(Cos.class);

        Sin sin = new Sin(cosMock);
        Tan tan = new Tan(sin, cosMock);
        Sec sec = new Sec(cosMock);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        when(cosMock.calculate(angle, 15)).thenAnswer(invocation -> getCosValue(angle));

        FunctionSystem functionSystem = new FunctionSystem(cosMock, sin, tan, sec, csc, log10, log2, log5, ln);
        BigDecimal result = functionSystem.calculate(angle, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideAnglesOutOfDomain")
    void testFunction1lvlOufOfDomain(BigDecimal angle) {
        Cos cosMock = Mockito.mock(Cos.class);

        Sin sin = new Sin(cosMock);
        Tan tan = new Tan(sin, cosMock);
        Sec sec = new Sec(cosMock);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);

//        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));

        when(cosMock.calculate(angle, 15)).thenAnswer(invocation -> getCosValue(angle));


        FunctionSystem functionSystem = new FunctionSystem(cosMock, sin, tan, sec, csc, log10, log2, log5, ln);
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }


    @ParameterizedTest
    @MethodSource("provideAngles")
    void testFunction4lvl(BigDecimal angle, double expected) {
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        BigDecimal result = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, ln).calculate(angle, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideAnglesOutOfDomain")
    void testFunction4lvlOufOfDomain(BigDecimal angle) {
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        FunctionSystem functionSystem = new FunctionSystem(cos, sin, tan, sec, csc, log10, log2, log5, ln);
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
    }

    @ParameterizedTest
    @MethodSource("provideAngles")
    void testFunction3lvl(BigDecimal angle, double expected) {

        Cos cosMock = Mockito.mock(Cos.class);
        Sin sinMock = Mockito.mock(Sin.class);
        Sec secMock = Mockito.mock(Sec.class);
        Tan tanMock = Mockito.mock(Tan.class);
        Csc cscMock = Mockito.mock(Csc.class);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        when(cosMock.calculate(angle, 15)).thenAnswer(invocation -> getCosValue(angle));
       // when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));
        when(sinMock.calculate(angle, 15)).thenAnswer(invocation -> getSinValue(angle));
    //    when(sinMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.sin(angle.doubleValue())));
     when(secMock.calculate(angle, 15)).thenAnswer(invocation -> getSecValue(angle));
       // when(secMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.cos(angle.doubleValue())));
     //  when(tanMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.tan(angle.doubleValue())));
         when(tanMock.calculate(angle, 15)).thenAnswer(invocation -> getTanValue(angle));
        when(cscMock.calculate(angle, 15)).thenAnswer(invocation -> getCscValue(angle));
      //  when(cscMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.sin(angle.doubleValue())));

        FunctionSystem functionSystem = new FunctionSystem(cosMock, sinMock, tanMock, secMock, cscMock, log10, log2, log5, ln);
        BigDecimal result = functionSystem.calculate(angle, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    private BigDecimal getCosValue(BigDecimal angle) {
        Map<BigDecimal, BigDecimal> cosValues = new HashMap<>();
        cosValues.put(BigDecimal.valueOf(-Math.PI / 32), new BigDecimal("0.99518"));
        cosValues.put(BigDecimal.valueOf(-Math.PI / 16), new BigDecimal("0.98078528"));
        cosValues.put(BigDecimal.valueOf(-Math.PI / 8), new BigDecimal("0.92388"));
        cosValues.put(BigDecimal.valueOf(-Math.PI / 4), new BigDecimal("0.70711"));
        cosValues.put(BigDecimal.valueOf(-Math.PI / 3), new BigDecimal("0.50000"));
        cosValues.put(BigDecimal.valueOf(-3 * Math.PI / 4), new BigDecimal("-0.70711"));
        cosValues.put(BigDecimal.valueOf(-13 * Math.PI / 12), new BigDecimal("-0.96593"));
        cosValues.put(BigDecimal.valueOf(-9 * Math.PI / 8), new BigDecimal("-0.92388"));
        cosValues.put(BigDecimal.valueOf(-7 * Math.PI / 6), new BigDecimal("-0.86603"));
        cosValues.put(BigDecimal.valueOf(-5 * Math.PI / 4), new BigDecimal("-0.70711"));
        cosValues.put(BigDecimal.valueOf(-4 * Math.PI / 3), new BigDecimal("-0.50000"));
        cosValues.put(BigDecimal.valueOf(-5 * Math.PI / 3), new BigDecimal("0.50000"));

        return cosValues.getOrDefault(angle, BigDecimal.ZERO);
    }

    private BigDecimal getSinValue(BigDecimal angle) {
        Map<BigDecimal, BigDecimal> sinValues = new HashMap<>();
        sinValues.put(BigDecimal.valueOf(-Math.PI / 32), new BigDecimal("-0.09802"));
        sinValues.put(BigDecimal.valueOf(-Math.PI / 16), new BigDecimal("-0.19509"));
        sinValues.put(BigDecimal.valueOf(-Math.PI / 8), new BigDecimal("-0.38268"));
        sinValues.put(BigDecimal.valueOf(-Math.PI / 4), new BigDecimal("-0.70711"));
        sinValues.put(BigDecimal.valueOf(-Math.PI / 3), new BigDecimal("-0.86603"));
        sinValues.put(BigDecimal.valueOf(-3 * Math.PI / 4), new BigDecimal("-0.70711"));
        sinValues.put(BigDecimal.valueOf(-13 * Math.PI / 12), new BigDecimal("0.25882"));
        sinValues.put(BigDecimal.valueOf(-9 * Math.PI / 8), new BigDecimal("0.38268"));
        sinValues.put(BigDecimal.valueOf(-7 * Math.PI / 6), new BigDecimal("0.50000"));
        sinValues.put(BigDecimal.valueOf(-5 * Math.PI / 4), new BigDecimal("0.70711"));
        sinValues.put(BigDecimal.valueOf(-4 * Math.PI / 3), new BigDecimal("0.86603"));
        sinValues.put(BigDecimal.valueOf(-5 * Math.PI / 3), new BigDecimal("0.86603"));

        return sinValues.getOrDefault(angle, BigDecimal.ZERO);
    }

    private BigDecimal getSecValue(BigDecimal angle) {
        Map<BigDecimal, BigDecimal> secValues = new HashMap<>();
        secValues.put(BigDecimal.valueOf(-Math.PI / 32), new BigDecimal("1.00484"));
        secValues.put(BigDecimal.valueOf(-Math.PI / 16), new BigDecimal("1.01959"));
        secValues.put(BigDecimal.valueOf(-Math.PI / 8), new BigDecimal("1.08239"));
        secValues.put(BigDecimal.valueOf(-Math.PI / 4), new BigDecimal("1.41421"));
        secValues.put(BigDecimal.valueOf(-Math.PI / 3), new BigDecimal("2.00000"));
        secValues.put(BigDecimal.valueOf(-3 * Math.PI / 4), new BigDecimal("-1.41421"));
        secValues.put(BigDecimal.valueOf(-13 * Math.PI / 12), new BigDecimal("-1.03528"));
        secValues.put(BigDecimal.valueOf(-9 * Math.PI / 8), new BigDecimal("-1.08239"));
        secValues.put(BigDecimal.valueOf(-7 * Math.PI / 6), new BigDecimal("-1.15470"));
        secValues.put(BigDecimal.valueOf(-5 * Math.PI / 4), new BigDecimal("-1.41421"));
        secValues.put(BigDecimal.valueOf(-4 * Math.PI / 3), new BigDecimal("-2.00000"));
        secValues.put(BigDecimal.valueOf(-5 * Math.PI / 3), new BigDecimal("2.00000"));

        return secValues.getOrDefault(angle, BigDecimal.ZERO);
    }

    private BigDecimal getCscValue(BigDecimal angle) {
        Map<BigDecimal, BigDecimal> cscValues = new HashMap<>();
        cscValues.put(BigDecimal.valueOf(-Math.PI / 32), new BigDecimal("-10.2111"));
        cscValues.put(BigDecimal.valueOf(-Math.PI / 16), new BigDecimal("-5.12583"));
        cscValues.put(BigDecimal.valueOf(-Math.PI / 8), new BigDecimal("-2.6131"));
        cscValues.put(BigDecimal.valueOf(-Math.PI / 4), new BigDecimal("-1.4142"));
        cscValues.put(BigDecimal.valueOf(-Math.PI / 3), new BigDecimal("-1.1547"));
        cscValues.put(BigDecimal.valueOf(-3 * Math.PI / 4), new BigDecimal("-1.4142"));
        cscValues.put(BigDecimal.valueOf(-13 * Math.PI / 12), new BigDecimal("3.8637"));
        cscValues.put(BigDecimal.valueOf(-9 * Math.PI / 8), new BigDecimal("2.6131"));
        cscValues.put(BigDecimal.valueOf(-7 * Math.PI / 6), new BigDecimal("2.0000"));
        cscValues.put(BigDecimal.valueOf(-5 * Math.PI / 4), new BigDecimal("1.4142"));
        cscValues.put(BigDecimal.valueOf(-4 * Math.PI / 3), new BigDecimal("1.1547"));
        cscValues.put(BigDecimal.valueOf(-5 * Math.PI / 3), new BigDecimal("1.1547"));

        return cscValues.getOrDefault(angle, BigDecimal.ZERO);
    }

    private BigDecimal getTanValue(BigDecimal angle) {
        Map<BigDecimal, BigDecimal> tanValues = new HashMap<>();
        tanValues.put(BigDecimal.valueOf(-Math.PI / 32), new BigDecimal("-0.09849"));
        tanValues.put(BigDecimal.valueOf(-Math.PI / 16), new BigDecimal("-0.19891"));
        tanValues.put(BigDecimal.valueOf(-Math.PI / 8), new BigDecimal("-0.41421"));
        tanValues.put(BigDecimal.valueOf(-Math.PI / 4), new BigDecimal("-1.00000"));
        tanValues.put(BigDecimal.valueOf(-Math.PI / 3), new BigDecimal("-1.73205"));
        tanValues.put(BigDecimal.valueOf(-3 * Math.PI / 4), new BigDecimal("1.00000"));
        tanValues.put(BigDecimal.valueOf(-13 * Math.PI / 12), new BigDecimal("-0.26795"));
        tanValues.put(BigDecimal.valueOf(-9 * Math.PI / 8), new BigDecimal("-0.41421"));
        tanValues.put(BigDecimal.valueOf(-7 * Math.PI / 6), new BigDecimal("-0.57735"));
        tanValues.put(BigDecimal.valueOf(-5 * Math.PI / 4), new BigDecimal("-1.00000"));
        tanValues.put(BigDecimal.valueOf(-4 * Math.PI / 3), new BigDecimal("-1.73205"));
        tanValues.put(BigDecimal.valueOf(-5 * Math.PI / 3), new BigDecimal("1.73205"));

        return tanValues.getOrDefault(angle, BigDecimal.ZERO);
    }



}
