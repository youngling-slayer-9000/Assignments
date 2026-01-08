import java.io.IOException;
import java.util.*;

public class Solver {

    private List<Pair> userPokemons;
    private List<Pair> opponentPokemons;

    private final InputHandle handler = new ConsoleInput();
    private final Parser parser = new Parser();

    private static final Map<String, List<String>> ADVANTAGE = Map.of(
        "Fire", List.of("Grass", "Ghost"),
        "Water", List.of("Fire"),
        "Grass", List.of("Electric", "Fighting"),
        "Electric", List.of("Water"),
        "Psychic", List.of("Ghost"),
        "Ghost", List.of("Fighting", "Fire", "Electric"),
        "Fighting", List.of("Electric")
    );

    public void fetchInput() {
        try {
            userPokemons = parser.parseInput(handler.read_input());
            opponentPokemons = parser.parseInput(handler.read_input());
        } catch (IOException e) {
            throw new RuntimeException("Input failed", e);
        }
    }
    
    
    private void permute(int idx, List<Pair> arr, List<List<Pair>> res) {
        if (idx == arr.size()) {
            res.add(new ArrayList<>(arr));
            return;
        }
        for (int i = idx; i < arr.size(); i++) {
            Collections.swap(arr, idx, i);
            permute(idx + 1, arr, res);
            Collections.swap(arr, idx, i);
        }
    }

    

    public void run() {
        List<List<Pair>> permutations = new ArrayList<>();
        permute(0, userPokemons, permutations);

        for (List<Pair> perm : permutations) {
            int wins = 0;

            for (int i = 0; i < 5; i++) {
                if (compare(perm.get(i), opponentPokemons.get(i)) >= 1) {
                    wins++;
                }
            }

            if (wins >= 3) {
                printResult(perm);
                return;
            }
        }

        System.out.println("There are no chance of winning");
    }

    private int compare(Pair mine, Pair opp) {

        if (mine.getType().equals(opp.getType())) {
            return Integer.compare(mine.getLevel(), opp.getLevel());
        }

        boolean mineAdv = ADVANTAGE
                .getOrDefault(mine.getType(), List.of())
                .contains(opp.getType());

        boolean oppAdv = ADVANTAGE
                .getOrDefault(opp.getType(), List.of())
                .contains(mine.getType());

        if (mineAdv && !oppAdv) {
            return (opp.getLevel() >= 2 * mine.getLevel()) ? -1 : 1;
        }

        if (oppAdv && !mineAdv) {
            return (mine.getLevel() >= 2 * opp.getLevel()) ? 1 : -1;
        }

        return Integer.compare(mine.getLevel(), opp.getLevel());
    }

    
    private void printResult(List<Pair> result) {
        StringBuilder sb = new StringBuilder();
        for (Pair p : result) {
            sb.append(p.getType())
              .append("#")
              .append(p.getLevel())
              .append(";");
        }
        sb.setLength(sb.length() - 1); // remove last ;
        System.out.println(sb);
    }

    
    
}
