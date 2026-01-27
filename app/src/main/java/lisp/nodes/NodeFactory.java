package lisp.nodes;

public class NodeFactory {

    public static Node<?> createNode(String type, String value) {

        if (type == null || value == null) {
            throw new IllegalArgumentException("Type or value can't be null");
        }

        type = type.trim().toLowerCase();
        value = value.trim();

        switch (type) {
            case "integer":
                return new IntegerNode(Integer.parseInt(value));

            case "float":
                return new FloatNode(Double.parseDouble(value));

            case "character":
                if (value.length() == 0) throw new IllegalArgumentException("Empty character value");
                return new CharacterNode(value.charAt(0));

            case "boolean":
                return new BooleanNode(Boolean.parseBoolean(value));

            case "symbol":
                return new SymbolNode(value);

            case "identifier":
                return new IdentifierNode(value);
            default:
                throw new IllegalArgumentException("Unknown node type: " + type);
        }
    }
}
