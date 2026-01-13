
import java.io.IOException;
import java.util.Scanner;


public class ConsoleInput implements InputHandle {
	@Override
    public String read_input()throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
