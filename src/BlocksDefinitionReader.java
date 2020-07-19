import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * in charge of reading a block-definitions file and returning a
 * BlocksFromSymbolsFactory object.
 */
public class BlocksDefinitionReader {
    /**
     * constructor.
     * @param reader the blocks file
     * @return blocksFromSymbolsFactory class type
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        // map of the spacerWidths that contain the symbol of the spacer and
        // its value
        Map<String, Integer> spacerWidths = new HashMap<>();
        // map of all the blocks type. conatain the symbol of the block and
        // the block
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        // a map that contain the symbol and all the detalilsof this type of
        // block
        Map<String, String> symbolDetails = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    // the firs word in the line
                if (parts[0].equals("default")) {
                    for (int i = 1; i < parts.length; i++) {
                        String[] defaultStringArray = parts[i].split(":");
                        // put in the symbolDetails map the key and the value
                        symbolDetails.put(defaultStringArray[0],
                                defaultStringArray[1]);
                    }
                    // followed by a space-separated list of properties
                } else if (parts[0].equals("sdef")) {
                    String[] withKey = parts[1].split(":");
                    String[] withValue = parts[2].split(":");
                    spacerWidths.put(withKey[1],
                            Integer.parseInt(withValue[1]));
                    //each bdef line specifies one block type, and lists its
                    // properties
                } else if (parts[0].equals("bdef")) {
                    Map<String, String> mySymbol = new HashMap<String, String>(symbolDetails);
                    List<String> colorArray = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        String[] blckData = parts[i].split(":");
                        // put a key and value of the symbol and its descreption
                        mySymbol.put(blckData[0],
                                blckData[1]);
                        // creat array of all the fills of the block
                        if (blckData[0].contains("fill")) {
                            if (blckData[0].length() > 5) {
                                colorArray.add(parts[i]);
                            } else {
                                colorArray.add(blckData[0] + "-1:" + blckData[1]);
                            }
                        }
                    }
                    String stroke = mySymbol.get("stroke");
                    // creation of BlockDetails
                    BlockDetails blockDetails =
                            new BlockDetails(Integer.valueOf(mySymbol.get(
                                    "width")),
                                    Integer.valueOf(mySymbol.get("height")),
                                    Integer.valueOf(mySymbol.get("hit_points")), stroke,
                            colorArray);
                    blockCreators.put(mySymbol.get("symbol"), blockDetails);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

}
