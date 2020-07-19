

/**
 * the class update the counter when blocks are being hit and removed.
 *
 * @author YuvalSaadati
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * constructor of the class.
     * @param scoreCounter the counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * @param number the value that will be added the to current value.
     */
    public void setincreaseCounter(int number) {
        this.currentScore.increase(number);
    }
    /**
     * if the ball it a regular block so the player get 5 points, if the ball
     * hit the block so in the next time the ball will be removed and the
     * player get more 10 points.
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getBlockName().equals("b")) {
            this.currentScore.increase(5);
            if (beingHit.getHitPoints() == 0) {
                this.currentScore.increase(10);
            }
        }
    }
    /**
     * @return the value of the score
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
    /**
     * @return the counter of the score.
     */
    public Counter getCounterScore() {
        return this.currentScore;
    }

}
