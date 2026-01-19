package lisp.operations;

import java.util.List;

public class Addition implements Operatable {

    @Override
    public Object apply(List<Object> args) {

        boolean hasDecimal = false;
        for(Object x : args){
            if (x instanceof Double || x instanceof Float) {
                hasDecimal = true;
                break;
            }
        }

        if(hasDecimal){
            double sum = 0.0;
            for(Object x : args)
                sum += ((Number) x).doubleValue();
            return sum;
        }

        int sum = 0;
        for(Object x : args)
            sum += ((Number) x).intValue();
        return sum;
    }
}
