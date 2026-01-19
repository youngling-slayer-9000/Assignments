package lisp.operations;

import java.util.List;

public class GreaterThan implements Operatable {
    @Override
    public Object apply(List<Object> args) {
        return ((Number) args.get(0)).doubleValue() > ((Number) args.get(1)).doubleValue();
    }
}
