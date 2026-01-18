package lisp;
import lisp.ast.AST;
import lisp.ast.PostOrderTraversal;
import lisp.ast.Evaluation;
import lisp.nodes.Node;
import lisp.symbolTable.SymbolTable;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AST ast = new AST();
        SymbolTable env = SymbolTable.getInstance();

        Scanner sc = new Scanner(System.in);

        System.out.println("Type 'exit' to quit");
        System.out.println();

        while (true) {

            System.out.print("lisp> ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;
            if (input.isEmpty()) continue;

            try {
                Node<?> root = ast.construction(input);

                Evaluation visitor = new Evaluation(env);
                PostOrderTraversal.traverse(root, visitor);

                System.out.println(visitor.result());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Bye!");
    }
}
