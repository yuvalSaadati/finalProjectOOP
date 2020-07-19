import biuoop.DrawSurface;
import java.awt.Color;

/**
 * create color for the background.
 */
public class BackGroundColor implements Sprite {
    private Color color;

    /**
     * constructor.
     * @param myColor the color of the background
     */
    public BackGroundColor(Color myColor) {
        this.color = myColor;
    }
    /**
     * draw the sprite to the screen.
     * @param d the surface of the game
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, 800, 600);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
