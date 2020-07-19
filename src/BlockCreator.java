/**
 * abstract class that create blocks.
 */
public interface BlockCreator {
    /**
     * @param xpos the x pos of the block.
     * @param ypos the y pos of the block.
     * @return block based on x, y pose.
     */
    // Create a block at the specified location.
    Block create(int xpos, int ypos);
    /**
     * @return the with of the block
     */
    int getWidth();
}
