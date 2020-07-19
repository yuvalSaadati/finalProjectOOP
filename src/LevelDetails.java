import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Color;

/**
 * save the details of the level.
 */
public class LevelDetails implements LevelInformation {
    private Map<String, String> levelMap;
    private List<String> blockChar;

    /**
     * @param myLevelMap all the details of each level
     * @param myBlocksList list of each string of block
     */
    public LevelDetails(Map<String, String> myLevelMap,
                        List<String> myBlocksList) {
        this.levelMap = myLevelMap;
        this.blockChar = myBlocksList;
    }
    /**
     * @return the numbers of balls in the game.
     */
    public int numberOfBalls() {
        String[] parts = this.levelMap.get("ball_velocities").split(" ");
        return parts.length;
    }
    /**
     * @return list of balls velocity.
     */
    public List<Velocity> initialBallVelocities() {
       // ball_velocities:45,200 -45,200 0,300
        List<Velocity> l = new ArrayList<>();
        String[] parts = this.levelMap.get("ball_velocities").split(" ");
        for (String s : parts) {
            String[] value = s.split(",");

            l.add(Velocity.fromAngleAndSpeed(Integer.valueOf(value[0]),
                    Integer.valueOf(value[1])));
}
        return l;
    }
    /**
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        // paddle_speed:650
        return Integer.valueOf(this.levelMap.get("paddle_speed"));
    }
    /**
     * @return the paddle width.
     */
    public int paddleWidth() {
        // paddle_width
        return Integer.valueOf(this.levelMap.get("paddle_width"));
    }
    /**
     * the level name will be displayed at the top of the screen.
     * @return the name of this level.
     */
    public String levelName() {
        return this.levelMap.get("level_name");
    }
    /**
     * @return  a sprite with the background of the level
     */
    public Sprite getBackground() {
// background:color(red)
        String background = this.levelMap.get("background");
        // convert string to char array
        if (background.contains("image")) {
            return new ImageBackground(background);
        } else {
                return new BackGroundColor(ColorsParser.colorFromString(background));
        }
    }
    /**
     * create the Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks
     */
    public List<Block> blocks() {

        List<Block> blockList = new ArrayList<>();

            InputStream is =
                    ClassLoader.getSystemClassLoader().getResourceAsStream(this.levelMap.get(
                                    "block_definitions"));
            BlocksFromSymbolsFactory blocksFromSymbolsFactory =
                    BlocksDefinitionReader.fromReader(new InputStreamReader(is));
            int blockStartX = Integer.parseInt(this.levelMap.get("blocks_start_x"));
            int blockStartY = Integer.parseInt(this.levelMap.get(
                    "blocks_start_y"));
            int height = Integer.parseInt(this.levelMap.get("row_height"));
            for (String line : this.blockChar) {
                char[] lineArray = line.toCharArray();

                for (int i = 0; i < lineArray.length; i++) {
                    if (blocksFromSymbolsFactory.isBlockSymbol(String.valueOf(lineArray[i]))) {
                        blockList.add(blocksFromSymbolsFactory.getBlock(String.valueOf(lineArray[i]), blockStartX,
                                blockStartY));
                        blockStartX += blocksFromSymbolsFactory.getBlockWidth(String.valueOf(lineArray[i]));
                    } else if (blocksFromSymbolsFactory.isSpaceSymbol(String.valueOf(lineArray[i]))) {
                        if (lineArray.length == 1) {
                            // only one space which means empty line
                            blockStartY += height;
                            break;
                        } else {
                            blockStartX += blocksFromSymbolsFactory.getSpaceWidth(String.valueOf(lineArray[i]));
                        }
                    }
                }
                blockStartY += height;
                blockStartX = Integer.parseInt(this.levelMap.get("blocks_start_x"));
            }
        return blockList;
    }
    /**
     * @return the number of block that should be removed.
     */
    public int numberOfBlocksToRemove() {
        //num_blocks
         return Integer.valueOf(this.levelMap.get("num_blocks"));
    }
    /**
     * @return the color of the ball in this level.
     */
    public Color getBallColor() {
        return new Color(59, 229, 195);
    }
}
