import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * a method that will get a file name and returns a list of LevelInformation
 * objects.
 */
public class LevelSpecificationReader {
    /**
     * Splitting the file into levels, reading a single level specification block
     * from file (from START_LEVEL to END_LEVEL). This task will get a
     * java.io.Reader and return a list of strings.
     * @param reader the file that will be read
     * @return list of levels
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        List<LevelInformation> gameLevels = new ArrayList<>();
    // list of all the levels in the file
        Map<String, String> levelMap = new HashMap<>();
        Map<String, String> copyLevelMap = new HashMap<>();

        BufferedReader bufferedReader = new BufferedReader(reader);
        // boolean argument to know when the block set start
        boolean flag = false;
        // integer - the number of the level, List<String> - each line refer
        // to a line in block like in file
        Map<Integer, List<String>> blockDetails = new HashMap<>();
        // count the level num refer to the file
        int levelNum = 0;
        // each line that will be read from the file
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    // START_LEVEL refer that a new level started
                    levelNum++;
                    // create new key by the level number
                    blockDetails.put(levelNum, new ArrayList<>());
                    flag = false;
                    // put all the details of each level in this map
                    levelMap = new HashMap<String, String>(copyLevelMap);
                }

                if (line.contains(":")) {
                    // if the line contain the char : it ia mean that it
                    // contain details of the kevek that need to be put on
                    // the level map
                    String[] detail = line.split(":");
                    levelMap.put(detail[0], detail[1]);
                }
                if (line.equals("START_BLOCKS")) {
                    flag = true;
                }
                if (line.equals("END_BLOCKS")) {
                    // adding level to the game by using the method
                    // LevelDetails which create new level
                    gameLevels.add(new LevelDetails(levelMap,
                            blockDetails.get(levelNum)));
                }
                if (flag) {
                    if (line.equals("START_BLOCKS") || line.equals(
                            "END_BLOCKS") || line.equals("END_LEVEL")) {
                        // it is not information that need to be in those data
                        continue;
                    } else {
                        if (!line.contains("#")) {
                            // putting all the blocks char in string array
                            blockDetails.get(levelNum).add(line);
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("cant read the file");
        }
        return gameLevels;
    }
}
