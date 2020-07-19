import biuoop.DrawSurface;
/**
 * interface Animation do something on the screen.
 */
public interface Animation {
    /**
     * draw something on the screen.
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);
    /**
     * @return if the animation should stop
     */
    boolean shouldStop();
    /**
     * @param stop wither the animation should stop
     */
     void setStop(boolean stop);
}
