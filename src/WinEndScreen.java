import biuoop.DrawSurface;
/**
 * this animation is screen that showed at the end of the game if the player
 * win.
 */
public class WinEndScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * constructor.
     * @param myScore the current score of the player
     */
    public WinEndScreen(int myScore) {
        this.stop = false;
        this.score = myScore;
    }
    /**
     * show in different screen: "You Win! Your score is x" where x is the
     * final score of the player.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + this.score,
                    32);
    }
    /**
     * @return boolean file that present when the anumation should stop.
     */
    public boolean shouldStop() { return this.stop; }
    /**
     * @param myStop if the animation should stop
     */
    public void setStop(boolean myStop) {
        this.stop = myStop;
    }
}
