package engine.options;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Options {
    static int soundLevel;
    static int playerCount;
    static AiLevel aiLevel;
    static List<Player> players;
    enum AiLevel {EASY, MEDIUM, HARD}
    enum PlayerType {AI, PLAYER}

    private static final String[] AI_Names = new String[]{"Optix", "Present", "Memory", "Admin", "Holmes", "Synapse", "Aura", "Solace", "Animus", "Sprite", "Mage"};



    private static final Options options = new Options();

    public static void init() {
        soundLevel = 100;
        playerCount = 2;
        aiLevel = AiLevel.EASY;
        players = new ArrayList<>(4);
    }


    public int addPlayer(String name) {
        if (players.size() == 4) {
            throw new IllegalArgumentException("Only 4 players are allowed");
        }
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Player needs to have a name");
        }
        players.add(new Player(PlayerType.PLAYER, null, name, players.size()));
        return players.size() - 1;
    }

    public int addAI(String name, AiLevel aiLevel) {
        if (players.size() == 4) {
            throw new IllegalArgumentException("Only 4 players are allowed");
        }
        if (aiLevel == null) {
            throw new IllegalArgumentException("AI needs to have a difficulty level");
        }
        if (name == null || name.equals("")) {
            name = AI_Names[new Random().nextInt(AI_Names.length)];
        }
        players.add(new Player(PlayerType.AI, aiLevel, name, players.size()));
        return players.size() - 1;
    }


    public Player getPlayer(int ID) {
        return players.get(ID);
    }

    public void setSoundLevel(int soundLevel) {
        if (soundLevel < 0 || soundLevel > 100)
            throw new IllegalArgumentException("soundLevel out of range [0,100]: " + soundLevel);
        this.soundLevel = soundLevel;
    }

    private record Player(PlayerType  playerType, AiLevel aiLevel, String name, int ID) {}
}
