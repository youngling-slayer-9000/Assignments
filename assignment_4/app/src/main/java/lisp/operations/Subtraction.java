package lisp.operations;

import java.util.List;

public class Subtraction implements Operatable {

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
            double res = ((Number) args.get(0)).doubleValue();
            for (int i = 1; i < args.size(); i++) {
                res -= ((Number) args.get(i)).doubleValue();
            }
            return res;
        }

        int res = ((Number) args.get(0)).intValue();
        for (int i = 1; i < args.size(); i++) {
            res -= ((Number) args.get(i)).intValue();
        }
        return res;
    }
}
