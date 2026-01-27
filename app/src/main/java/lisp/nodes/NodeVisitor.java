package lisp.nodes;

public interface NodeVisitor {

    void visit(FloatNode ptr);
    void visit(CharacterNode ptr);
    void visit(IntegerNode ptr);
    void visit(SymbolNode ptr);
    void visit(BooleanNode ptr);
    void visit(IdentifierNode ptr);
}
