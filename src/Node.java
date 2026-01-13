public interface Node<T> {
    T getData();
    void accept(NodeVisitor visitor);
}
