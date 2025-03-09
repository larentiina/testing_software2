package larentina;

import larentina.function.FunctionSystem;
import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new FunctionSystem().solveSystem(BigDecimal.valueOf(10),15);
//        System.out.println(new Ln().calculate(BigDecimal.valueOf(1),15));


    }
}
