package lisp.operations;

import java.util.List;

public class OrOp implements Operatable {
    @Override
    public Object apply(List<Object> args) {
        boolean res = false;
        for (Object x : args) res = res || (Boolean) x;
        return res;
    }
}
