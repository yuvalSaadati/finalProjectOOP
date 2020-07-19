import biuoop.DrawSurface;
import java.awt.Color;
/**
 * in charge of displaying the current score.
 * @author YuvalSaadati
 */
public class ScoreIndicator implements Sprite {
    private Counter scoresCounter;

    /**
     * constructor.
     * @param score the counter of the score
     */
    public ScoreIndicator(Counter score) {
        this.scoresCounter = score;
    }
    /**
     * draw the score in the middle of white rectangle.
     * @param d the surface of the game
     */
        public void drawOn(DrawSurface d) {
            d.setColor(Color.WHITE.darker());
            d.fillRectangle(0, 0, 800, 18);
            d.setColor(Color.BLACK);
            d.drawText(375, 13, "Score: "
                    + this.scoresCounter.getValue(), 12);
        }

    /**
     * do nothing.
     */
        public void timePassed() {
        }
}
