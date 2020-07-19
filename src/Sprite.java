
import biuoop.DrawSurface;

/**
 * class Block is also type Collidable and Sprite and has thr files
 * collisionRectangle, color and numberOfHits.
 *
 * @author YuvalSaadati
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface of the game
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     */
    void timePassed();
}
