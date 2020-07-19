import java.awt.Color;
import biuoop.DrawSurface;

/**
 * sit at the top of the screen and indicate the number of lives.
 *
 * @author YuvalSaadati
 */
public class LivesIndicator implements Sprite {
    private Counter liveCounter;

    /**
     * the constructor of the class.
     * @param live the counter of lives in the game
     */
    public LivesIndicator(Counter live) {
        this.liveCounter = live;
    }
    /**
     * @param number the number will be decreasing from the current value of
     * live in the game.
     */
    public void setDecreaseLiveCounter(int number) {
        this.liveCounter.decrease(number);
    }
    /**
     * @return the value of lives in the game.
     */
    public int getLiveCounter() {
        return this.liveCounter.getValue();
    }
    /**
     * sit at the top of the screen and indicate the number of lives.
     * @param d the surface of the game
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(20, 13, "Lives: " + this.liveCounter.getValue(),
                12);
    }

    /**
     * do nothing.
     */
    public void timePassed() {

    }
}
