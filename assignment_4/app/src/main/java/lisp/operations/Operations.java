package lisp.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operations {

    private static final Map<String, Operatable> ops = new HashMap<>();

    static {
        ops.put("+", Operations::add);
        ops.put("-",new Subtraction());
        ops.put("*",  new Multiplication());
        ops.put("/",new Division());

        ops.put(">" ,  new GreaterThan());
        ops.put("<" ,  new LessThan());
        ops.put(">=" ,  new GreaterEqual());
        ops.put("<=" , new LessEqual());
        ops.put("==" , new EqualTo());
        ops.put("!=", new NotEqualTo());

        ops.put("AND", new AndOp());
        ops.put("OR", new OrOp());
        ops.put("NOT", new NotOp());

    }

    public static  Object add(List<Object> args) {

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

    public static Operatable get(String symbol) {
        return ops.get(symbol);
    }


}
