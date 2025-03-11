package larentina.function;

import larentina.logarithmic.*;
import larentina.trigonometric.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static java.lang.Math.PI;


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
                Arguments.of(BigDecimal.valueOf(-PI/2)) //cos(x) = 0
        );
    }

    @Test
    @Disabled
    void test2PI(){
        BigDecimal pi = BigDecimal.valueOf(-PI/2);
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin,cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);
        List<CalculateWithPrecision> list = new ArrayList<>(){{
            add(cos);
            add(sin);
            add(tan);
            add(sec);
            add(csc);

        }};

        list.forEach(
                e->{
                    System.out.println(e.calculate(pi,15));
                }
        );

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


        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));
        when(sinMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.sin(angle.doubleValue())));
        when(secMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.cos(angle.doubleValue())));
        when(tanMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.tan(angle.doubleValue())));
        when(cscMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.sin(angle.doubleValue())));

        FunctionSystem functionSystem = new FunctionSystem(cosMock, sinMock, tanMock, secMock, cscMock, log10, log2, log5, ln);
        BigDecimal result = functionSystem.calculate(angle, 15);
        assertEquals(expected, result.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @ParameterizedTest
    @MethodSource("provideAnglesOutOfDomain")
    void testFunction3lvlOufOfDomain(BigDecimal angle) {
        Cos cosMock = Mockito.mock(Cos.class);
        Sin sinMock = Mockito.mock(Sin.class);
        Sec secMock = Mockito.mock(Sec.class);
        Tan tanMock = Mockito.mock(Tan.class);
        Csc cscMock = Mockito.mock(Csc.class);

        Ln ln = new Ln();
        Log10 log10 = new Log10(ln);
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);


        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));
        when(sinMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.sin(angle.doubleValue())));
        when(secMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.cos(angle.doubleValue())));
        when(tanMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.tan(angle.doubleValue())));
        when(cscMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.sin(angle.doubleValue())));

        when(secMock.calculate(BigDecimal.valueOf(-PI/2), 15)).thenThrow(new ArithmeticException());
        when(tanMock.calculate(BigDecimal.valueOf(-PI), 15)).thenThrow(new ArithmeticException());
        when(tanMock.calculate(BigDecimal.valueOf(-2*PI), 15)).thenThrow(new ArithmeticException());
        when(cscMock.calculate(BigDecimal.valueOf(-PI), 15)).thenThrow(new ArithmeticException());
        when(cscMock.calculate(BigDecimal.valueOf(-2*PI), 15)).thenThrow(new ArithmeticException());

        FunctionSystem functionSystem = new FunctionSystem(cosMock, sinMock, tanMock, secMock, cscMock, log10, log2, log5, ln);

        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(angle, 15));
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


        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));
        when(sinMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.sin(angle.doubleValue())));
        when(secMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.cos(angle.doubleValue())));

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


        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));
        when(sinMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.sin(angle.doubleValue())));
        when(secMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(1/Math.cos(angle.doubleValue())));

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


        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));

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

        when(cosMock.calculate(angle, 15)).thenReturn(BigDecimal.valueOf(Math.cos(angle.doubleValue())));

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


}
