package lisp;

import lisp.ast.AST;
import lisp.ast.Traversal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

      //  lisp.parser.Parser.Helper ptr = new lisp.parser.Parser.Helper("sharon");
       AST ast = new AST();

        System.out.println(Traversal.eval(ast.construction("( + (+ 2 3 6 7 ) ( + 3 4 ) )")));


    }
}