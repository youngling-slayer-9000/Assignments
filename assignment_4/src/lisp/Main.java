package lisp;
import lisp.ast.AST;
import lisp.ast.Traversal;
import lisp.nodes.Node;
import lisp.symbolTable.SymbolTable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AST ast = new AST();
        SymbolTable env = SymbolTable.getInstance();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("lisp> ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;
            if (input.isEmpty()) continue;

            Node<?> root = ast.construction(input);
            Object result = Traversal.eval(root, env);

            System.out.println(result);
        }

        sc.close();
    }
}
