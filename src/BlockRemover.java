
/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author YuvalSaadati
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor of the class.
     * @param g the game we are at
     * @param r the counter of BlockRemover
     */
    public BlockRemover(GameLevel g, Counter r) {
        this.game = g;
        this.remainingBlocks = r;
    }
    /**
     * @return the remaining blocks value of the counter.
     */
    public int getRemainingBlocks() {
            return this.remainingBlocks.getValue();
    }

    /**
     * Blocks that are hit and reach 0 hit-points will be removed
     * from the game. listener from the block
     *  is being removed from the game.
     * @param beingHit  the bottom of the screen
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
}
