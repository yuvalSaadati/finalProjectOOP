import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation decorator-class that will wrap an existing
 * animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;
    private boolean startPlay;

    /**
     * constructor.
     * @param sensor keyboardSensor
     * @param myKey string of the ket that is press
     * @param myAnimation animation that will be display
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String myKey,
                                      Animation myAnimation) {
        this.keyboard = sensor;
        this.key = myKey;
        this.animation = myAnimation;
        this.isAlreadyPressed = true;
        this.startPlay = true;
    }
    /**
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        // if the key was pressed before the animation started,
        // we ignore the key press
        if (this.startPlay) {
            this.isAlreadyPressed = this.keyboard.isPressed(this.key);
            this.startPlay = false;
        }
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            // there was a time point after the animation started
            // in which m was not pressed
            this.isAlreadyPressed = false;
        }
    }
    /**
     * @return if the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * @param myStop if the animation should stop
     */
    public void setStop(boolean myStop) {
        this.stop = myStop;
    }
}
