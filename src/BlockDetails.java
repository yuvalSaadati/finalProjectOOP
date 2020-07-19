import java.util.List;
/**
 * the class implement BlockCreator and we will use it in order to save
 * details of the block.
 */
public class BlockDetails implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private String stroke;
    private List<String> fillArray;
    /**
     * @param myWidth the block width
     * @param myHeight the block height
     * @param myHitPoints the sum of hit points of the block
     * @param myStroke the color of the frame
     * @param myColorArray fill array
     */
    public BlockDetails(int myWidth, int myHeight, int myHitPoints,
                        String myStroke,
                        List<String> myColorArray) {
            this.width = myWidth;
            this.height = myHeight;
            this.hitPoints = myHitPoints;
            this.stroke = myStroke;
            this.fillArray = myColorArray;
    }
    /**
     * Create a block at the specified location.
     * @param xpos the x pos of the block.
     * @param ypos the y pos of the block.
     * @return new block
     */
    public Block create(int xpos, int ypos) {
        Rectangle rectangle = new Rectangle(xpos, ypos, this.width,
                this.height);
        if (this.stroke != null) {
            return new Block(rectangle, "b",
                    ColorsParser.colorFromString(this.stroke), this.fillArray,
                    this.hitPoints);
        } else {
            return new Block(rectangle, "b", this.fillArray,
                    this.hitPoints);
        }
    }
    /**
     * @return the width of the block.
     */
    public int getWidth() {
        return this.width;
    }

}
