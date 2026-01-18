package lisp.operations;

import java.util.HashMap;
import java.util.Map;

public class Operations {

    private static final Map<String, Operatable> ops = new HashMap<>();

    static {
        ops.put("+", new Addition());
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

    public static Operatable get(String symbol) {
        return ops.get(symbol);
    }

    public static void register(String symbol, Operatable op) {
        ops.put(symbol, op);
    }
}
