package lisp.symbolTable;

import java.util.HashMap;

public class SymbolTable {

    private final HashMap<String , Object> table;

    private SymbolTable() {
        table = new HashMap<>();
    }

    public static class Helper {
        public static final SymbolTable ptr = new SymbolTable();
    }

    public static SymbolTable getInstance() {
        return Helper.ptr;
    }

    public void register(String variableName, Object value) {
        table.put(variableName, value);
    }

    public Object get(String variableName) {
        return table.get(variableName);
    }


    public Object getOrThrow(String variableName) {
        if (!table.containsKey(variableName)) {
            throw new RuntimeException("Undefined variable '" + variableName + "'");
        }
        return table.get(variableName);
    }
}
