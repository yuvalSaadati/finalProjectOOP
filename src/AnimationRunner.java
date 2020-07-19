import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor fill by gui to display something on he screen.
     * @param myGui the gui screen
     */
    public AnimationRunner(GUI myGui) {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper(); // sleeper
        this.gui = myGui;
    }

    /**
     * the run method run the game anumation by thus data of time.
     * @param animation that runs
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            // create the surface of thr game
            DrawSurface d = gui.getDrawSurface();
            // display the animation
            animation.doOneFrame(d);
            if (animation.shouldStop()) {
                return;
            }
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
