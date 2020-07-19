
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * class SpriteCollection hold a collection of sprites.
 *
 * @author YuvalSaadati
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor to initialize the list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * add sprite to the sprite collection.
     * @param s the sprite that will be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * remove sprite from the sprite collection.
     * @param s the sprite that will be delete
     */
    public void deleteSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : copyList) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface of the game
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
