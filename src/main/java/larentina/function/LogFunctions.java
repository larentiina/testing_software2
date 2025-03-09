package larentina.function;

import larentina.logarithmic.Ln;
import larentina.logarithmic.Log10;
import larentina.logarithmic.Log2;
import larentina.logarithmic.Log5;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class LogFunctions {

    private final Map<LogarithmicFunction,CalculateWithPrecision> logFunctions = new HashMap<>();

    public LogFunctions() {
        Ln ln = new Ln();
        Log2 log2 = new Log2(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);
        logFunctions.put(LogarithmicFunction.LOG10, log10);
        logFunctions.put(LogarithmicFunction.LOG5, log5);
        logFunctions.put(LogarithmicFunction.LN, ln);
        logFunctions.put(LogarithmicFunction.LOG2, log2);
    }

    public Ln ln() {
        return (Ln) logFunctions.get(LogarithmicFunction.LN);
    }
    public Log2 log2() {
        return (Log2) logFunctions.get(LogarithmicFunction.LOG2);
    }
    public Log5 log5() {
        return (Log5) logFunctions.get(LogarithmicFunction.LOG5);
    }
    public Log10 log10() {
        return (Log10) logFunctions.get(LogarithmicFunction.LOG10);
    }


    enum LogarithmicFunction {
       LN, LOG2, LOG5, LOG10
    }

}
