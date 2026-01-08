import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileInput implements InputHandle {
	private final Path path;

    public FileInput(Path path) {
        this.path = path;
    }

    @Override
    public String read_input() throws IOException {
        return Files.readString(path);
    }
}
