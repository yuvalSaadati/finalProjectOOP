import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
/**
 * this animation is screen that showed at the end of the game if the player
 * lose.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * constructor.
     * @param myScore the current score of the player
     */
    public EndScreen(int myScore) {
        this.stop = false;
        this.score = myScore;
    }
    /**
     * show in different screen: "Game Over. Your score is x" where x is the
     * final score of the player.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        InputStream is =
                ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/kimcry.jpg");
        Image img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("cant load the image");
        }
            d.drawImage(550, d.getHeight() / 2, img);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2,
                "Game Over. Your score is " + this.score,
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
