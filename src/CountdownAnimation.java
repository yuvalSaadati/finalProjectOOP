import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double seconds;
    private int countFromOnScreen;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private boolean beginOneTurn = true;
    private int myCount;
    /**
     * constructor to build this class.
     * @param numOfSeconds is the sum of seconds that the numbers will
     * display on the screen
     * @param countFrom the number that we will start to count from. int this
     * game we will start to count from 3
     * @param gameScreen in order to display all the sprites on the screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.countFromOnScreen = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper(); // sleeper
        this.stop = false;
        this.myCount = countFrom;
    }
    /**
     * display the counting 3 2 1.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d); // display all the sprite collection
        // display the numbers on the screen
        d.setColor(Color.WHITE.darker());
        d.drawText(400, 300, Integer.toString(this.myCount), 42);
        // each number will appear on the screen for (numOfSeconds /
        // countFrom) secods, before it is replaced with the next one.
        double appearOnTheScreenTime =
                (this.seconds / (double) this.countFromOnScreen) * 800;
        if (this.beginOneTurn) {
            this.beginOneTurn = false; // this was the begging of the section
        } else {
            // from the counting, the screen will stop for seconds
            // at the beging of the game hw screen do not need to sleep
            sleeper.sleepFor((long) appearOnTheScreenTime);
        }
        // decrease the counting number
        this.myCount--;
        // when getting to zero, the game will start
        if (this.myCount == -1) {
            this.stop = true;
        }
    }
    /**
     * @return when the anumation should stop
     */
    public boolean shouldStop() { return this.stop; }
    /**
     * @param myStop if the animation should stop
     */
    public void setStop(boolean myStop) {
        this.stop = myStop;
    }
}
