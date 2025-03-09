package larentina.function;

import larentina.trigonometric.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TrigFunctions {
    private final Map<TrigonometricFunction, CalculateWithPrecision> trigFunctions = new HashMap<>();

    public TrigFunctions() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);
        trigFunctions.put(TrigonometricFunction.COS, cos);
        trigFunctions.put(TrigonometricFunction.SIN, sin);
        trigFunctions.put(TrigonometricFunction.TAN, tan);
        trigFunctions.put(TrigonometricFunction.SEC, sec);
        trigFunctions.put(TrigonometricFunction.CSC, csc);
    }

    public Cos cos() {
        return (Cos) trigFunctions.get(TrigonometricFunction.COS);
    }
    public Sin sin() {
        return (Sin) trigFunctions.get(TrigonometricFunction.SIN);
    }
    public Tan tan() {
        return (Tan) trigFunctions.get(TrigonometricFunction.TAN);
    }
    public Sec sec() {
        return (Sec) trigFunctions.get(TrigonometricFunction.SEC);
    }

    public Csc csc() {
        return (Csc) trigFunctions.get(TrigonometricFunction.CSC);
    }

    enum TrigonometricFunction {
        SIN, COS, TAN, CSC, SEC
    }

}
