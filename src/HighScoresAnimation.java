import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
/**
 * animation that show the high score table of the game.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable scores;

    /**
     * @param myScores all the details of the high score char
     */
    public HighScoresAnimation(HighScoresTable myScores) {
        this.stop = false;
        this.scores = myScores;
    }
    /**
     * show in different screen: "Game Over. Your score is x" where x is the
     * final score of the player.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        // draw the background of the game
        d.setColor(new Color(255, 158, 158));
        d.fillRectangle(0, 0, 800, 600);
        // draw the title of the menu
        d.setColor(new Color(156, 42, 72));
        d.drawText(201, 70,
                "Hight Score Table", 51);
        d.setColor(Color.BLACK);
        d.drawText(200, 70,
                "Hight Score Table", 50);
        List<ScoreInfo> l = this.scores.getHighScores();
        int y = 150;
        for (ScoreInfo s : l) {
            d.drawText(100, y,
                    s.getName() + "  -  " + s.getScore(), 24);
            y += 30;
        }
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
