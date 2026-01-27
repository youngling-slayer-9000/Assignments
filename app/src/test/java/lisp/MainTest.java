package lisp;

import lisp.ast.AST;
import lisp.ast.Evaluation;
import lisp.ast.PostOrderTraversal;
import lisp.nodes.Node;
import lisp.symbolTable.SymbolTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private AST ast;
    private SymbolTable env;

    @BeforeEach
    void setup() {
        ast = new AST();
        env = SymbolTable.getInstance();
         env.clear();
    }

    private Object run(String expr) {
        Node<?> root = ast.construction(expr);
        Evaluation visitor = new Evaluation(env);
        PostOrderTraversal.traverse(root, visitor);
        return visitor.result();
    }

    // ----------------- PARAMETERIZED: Arithmetic -----------------

    static Stream<Arguments> arithmeticCases() {
        return Stream.of(
                Arguments.of("(+ 2 3)", 5),
                Arguments.of("(+ 2 3 4 5)", 14),
                Arguments.of("(* 2 3)", 6),
                Arguments.of("(- 10 2 3)", 5),
                Arguments.of("(/ 20 2)", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("arithmeticCases")
    void testArithmetic(String expr, Object expected) {
        assertEquals(expected, run(expr));
    }

    // ----------------- PARAMETERIZED: Float arithmetic -----------------

    static Stream<Arguments> floatCases() {
        return Stream.of(
                Arguments.of("(+ 2 2.3)", 4.3),
                Arguments.of("(* 2 2.5)", 5.0),
                Arguments.of("(/ 5.0 2)", 2.5),
                Arguments.of("(- 10.5 2)", 8.5)
        );
    }

    @ParameterizedTest
    @MethodSource("floatCases")
    void testFloatArithmetic(String expr, double expected) {
        Object ans = run(expr);
        assertTrue(ans instanceof Double);
        assertEquals(expected, (Double) ans, 1e-9);
    }

    // ----------------- PARAMETERIZED: Comparators -----------------

    static Stream<Arguments> comparatorCases() {
        return Stream.of(
                Arguments.of("(< 2 3)", true),
                Arguments.of("(> 2 3)", false),
                Arguments.of("(>= 5 5)", true),
                Arguments.of("(<= 4 9)", true),
                Arguments.of("(== 7 7)", true),
                Arguments.of("(!= 7 8)", true)
        );
    }

    @ParameterizedTest
    @MethodSource("comparatorCases")
    void testComparators(String expr, boolean expected) {
        assertEquals(expected, run(expr));
    }

    // ----------------- PARAMETERIZED: Logical -----------------

    static Stream<Arguments> logicalCases() {
        return Stream.of(
                Arguments.of("(AND true true)", true),
                Arguments.of("(AND true false)", false),
                Arguments.of("(OR false false)", false),
                Arguments.of("(OR false true)", true),
                Arguments.of("(NOT true)", false),
                Arguments.of("(NOT false)", true)
        );
    }

    @ParameterizedTest
    @MethodSource("logicalCases")
    void testLogical(String expr, boolean expected) {
        assertEquals(expected, run(expr));
    }

    // ----------------- Non-parameterized: Define / Variables -----------------

    @Test
    void testDefineAndUseVariable() {
        assertEquals(10, run("(define x 10)"));
        assertEquals(15, run("(+ x 5)"));
    }

    @Test
    void testUndefinedVariable() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> run("(+ c 3)")
        );
        assertTrue(ex.getMessage().contains("Undefined variable"));
    }
}
