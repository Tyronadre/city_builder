package engine;

import eea.engine.entity.StateBasedEntityManager;
import engine.options.Options;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MiningOperation extends StateBasedGame {
    /**
     * Optionsmenü
     */
    public static final int STATE_OPTIONS = -1;
    /**
     * Hauptmenü
     */
    public static final int STATE_MAINMENU = 0;
    /**
     * Gameplay
     */
    public static final int STATE_GAMEPLAY = 2;
    /**
     * Pause
     */
    public static final int STATE_PAUSE = 3;


    public static void main(String[] args) throws SlickException {
        //Setze dll's
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/windows");
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/macosx");
        } else {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/" + System.getProperty("os.name").toLowerCase());
        }
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        System.err.println(System.getProperty("os.name") + ": " + System.getProperty("org.lwjgl.librarypath"));

        AppGameContainer app = new AppGameContainer(new MiningOperation());

        app.setShowFPS(false);
        Options.init();

        app.start();
    }

    public MiningOperation() {
        super("Drop of Water");
    }


    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
// Fuege dem StateBasedGame die States hinzu
// (der zuerst hinzugefuegte State wird als erster State gestartet)
        addState(new MainMenuState(STATE_MAINMENU));
        addState(new GameplayState(STATE_GAMEPLAY));

        // Fuege dem StateBasedEntityManager die States hinzu
        StateBasedEntityManager.getInstance().addState(STATE_MAINMENU);
        StateBasedEntityManager.getInstance().addState(STATE_GAMEPLAY);
    }
}
