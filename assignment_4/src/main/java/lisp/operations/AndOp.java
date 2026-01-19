package lisp.operations;

import java.util.List;

public class AndOp implements Operatable {
    @Override
    public Object apply(List<Object> args) {
        boolean res = true;
        for (Object x : args) res = res && (Boolean) x;
        return res;
    }
}
