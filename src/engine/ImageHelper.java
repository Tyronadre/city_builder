package engine;

import eea.engine.component.render.ImageRenderComponent;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ImageHelper {

    public static Image load(String name, int width, int heigth) throws SlickException {
        return new Image(name).getScaledCopy(width,heigth);
    }
}
