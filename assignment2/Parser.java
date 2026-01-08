import java.util.List;
import java.util.ArrayList;

public class Parser {

    private List<Pair> inputs;

    // 1️⃣ Split entire line into individual mopokens
    String[] delimitPokemons(String input) {
        return input.split(";");
    }

    // 2️⃣ Split each mopoken into (type, level)
    void splitByTypeAndLevel(String[] delimitedPokemons) {
        inputs = new ArrayList<>();

        for (String token : delimitedPokemons) {

            token = token.trim();
            if (token.isEmpty()) continue;

            String[] parts = token.split("#");

            if (parts.length != 2) {
                throw new IllegalArgumentException(
                    "Invalid mopoken format: " + token
                );
            }

            String type = parts[0].trim();
            int level;

            try {
                level = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "Invalid level for mopoken: " + token
                );
            }

            inputs.add(new Pair(type, level));
        }
    }

    // 3️⃣ Public API
    public List<Pair> parseInput(String input) {
        String[] delimitedPokemons = delimitPokemons(input);
        splitByTypeAndLevel(delimitedPokemons);
        return inputs;
    }
}
