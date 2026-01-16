package lisp.nodes;

public interface NodeVisitor {

    void visit(CharacterNode ptr);
    void visit(IntegerNode ptr);
    void visit(SymbolNode ptr);
}
