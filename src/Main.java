import engine.MiningOperation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
    public static void main(String[] args) throws SlickException {
        System.out.println("Hello world!");
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/windows");
        } else if (
                System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
        }


            AppGameContainer app = new AppGameContainer(new MiningOperation());

            app.setDisplayMode(800, 600, false);
            app.start();
        }
    }