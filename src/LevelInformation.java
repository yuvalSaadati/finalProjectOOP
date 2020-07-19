import java.awt.Color;
import java.util.List;
/**
 * The LevelInformation interface specifies the information required to fully
 * describe a level.
 */
public interface LevelInformation {
    /**
     * @return the number of the balls in the level
     */
    int numberOfBalls();
    /**
     * @return list of balls velocity.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle speed.
     */
    int paddleSpeed();
    /**
     * @return the paddle width.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return the name of this level.
     */
    String levelName();
    /**
     * @return  a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * create the Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks
     */
    List<Block> blocks();
    /**
     * @return the number of block that should be removed.
     */
    int numberOfBlocksToRemove();
    /**
     * @return the color of the ball in this level.
     */
     Color getBallColor();
    }
