import biuoop.DrawSurface;
/**
 * display a screen with the message paused -- press space to continue until
 * a key is pressed.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }
    /**
     * show in different screen: "paused -- press space to continue" when
     * press the p bottom.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);
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
