package lisp.operations;

import java.util.List;
import java.util.Objects;

public class NotEqualTo implements Operatable {
    @Override
    public Object apply(List<Object> args) {
        return !Objects.equals(args.get(0), args.get(1));
    }
}
