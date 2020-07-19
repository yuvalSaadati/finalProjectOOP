import java.util.Map;
/**
 * make some method on the two maps that are given.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * @param mySpacerWidths map of the spacer
     * @param myBlockCreators map of the block creator
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> mySpacerWidths,
                                    Map<String, BlockCreator> myBlockCreators) {
        this.spacerWidths = mySpacerWidths;
        this.blockCreators = myBlockCreators;
    }
    /**
     * @param s symbol
     * @return returns true if 's' is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
    /**
     * @param s symbol
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * @param s symbol
     * @return the width in pixels associated with the given spacer-symbol
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
    /**
     * @param s the block symbole
     * @param x pos
     * @param y pos
     * @return Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos)
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
    /**
     * @param s the block symbol
     * @return the with of the block symbol
     */
    public int getBlockWidth(String s) {
        return this.blockCreators.get(s).getWidth();
    }
}
