package lisp.operations;

import java.util.List;

public class Multiplication implements Operatable {

    @Override
    public Object apply(List<Object> args) {

        boolean hasDecimal = false;
        for (Object x : args) {
            if (x instanceof Double || x instanceof Float) {
                hasDecimal = true;
                break;
            }
        }

        if (hasDecimal) {
            double prod = 1.0;
            for (Object x : args) prod *= ((Number) x).doubleValue();
            return prod;
        }

        int prod = 1;
        for (Object x : args) prod *= ((Number) x).intValue();
        return prod;
    }
}
