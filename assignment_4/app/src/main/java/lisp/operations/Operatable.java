package lisp.operations;

import java.util.List;

/* lamdas - functional inteerface*/

@FunctionalInterface
public interface Operatable {
    Object apply(List<Object> args);
}
