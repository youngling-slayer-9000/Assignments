package lisp.operations;

import java.util.List;

public class NotOp implements Operatable {
    @Override
    public Object apply(List<Object> args) {
        return !(Boolean) args.get(0);
    }
}
