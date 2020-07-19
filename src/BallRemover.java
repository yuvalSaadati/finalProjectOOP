
/**
 * charge of removing balls, and updating an availabe-balls counter.
 * Create a special block that will sit at the bottom
 * of the screen, and will function as a "death region".
 * BallRemover will be notified whenever a ball hits the death-region.
 * Whenever this happens, the BallRemover will remove the ball from the game
 * and update the balls counter
 *
 * @author YuvalSaadati
 */
    public class BallRemover implements HitListener {
        private GameLevel game;
        private Counter remainingBalls;

    /**
     * constructor of the class.
     * @param g the game we are at
     * @param r the counter of BallRemover
     */
        public BallRemover(GameLevel g, Counter r) {
            this.game = g;
            this.remainingBalls = r;
        }
    /**
     * @return the remaining balls value of the counter.
     */
        public int getRemainingBalls() {
            return this.remainingBalls.getValue();
        }
    /**
     * @param number the new value of remaining balls.
     */
        public void setremainingBalls(int number) {
            this.remainingBalls.setCount(number);
        }
    /**
     * if the balls got to the death region so one live will decrease and the
     * ball will be remove from the game.
     * @param deathRegion  the bottom of the screen
     * @param hitter the ball that is the hotter
     */
        public void hitEvent(Block deathRegion, Ball hitter) {
            if (deathRegion.getBlockName().equals("deathRegion")) {
                this.remainingBalls.decrease(1);
                hitter.removeFromGame(this.game);
            }
        }
    }

