import biuoop.DrawSurface;

/**
 * show the level on the screen.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation level;

    /**
     * the constructor of the class that get thr level information.
     * @param levelInformation the counter of lives in the game
     */
    public LevelIndicator(LevelInformation levelInformation) {
        this.level = levelInformation;
    }
    /**
     * draw the level name at the right up of the screen.
     * @param d the surface of the game
     */
    public void drawOn(DrawSurface d) {
            d.drawText(640, 13, level.levelName(), 14);
    }

    /**
     * do nothing.
     */
    public void timePassed() {
    }
}
