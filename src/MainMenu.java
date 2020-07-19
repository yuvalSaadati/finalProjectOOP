import biuoop.GUI;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * the menu of the game.
 */
public class MainMenu {

    /**
     * run all the program.
     * @param fileName the file of the set levels
     */
        public static void run(String fileName) {
        GUI gui = new GUI("Animation", 800, 500);
        AnimationRunner animationRunner = new AnimationRunner(gui);
            // list of all the levels in the game
            Task<Void> scoreTable = new Task<Void>() {
                @Override
                public Void run() {
                    File f = new File("highscores.ser");
                    HighScoresTable highScoresTable =
                            HighScoresTable.loadFromFile(f);
                    animationRunner.run(new KeyPressStoppableAnimation(
                            gui.getKeyboardSensor(), "space",
                            new HighScoresAnimation(highScoresTable)));
                    return null;                }
            };
            Task quit = new Task<Void>() {
                @Override
                public Void run() {
                    System.exit(0);
                    return null;
                }
            };
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                    "Arkanoid", gui.getKeyboardSensor(),
                            animationRunner);
            menu.addSelection("q", "quit", quit);
            menu.addSelection("h", "see the high scores",
                    scoreTable);
            Menu<Task<Void>> levelSetsMenu = new MenuAnimation<>(
                    "Level Sets",
                    gui.getKeyboardSensor(), animationRunner);
            Map<String, List<LevelInformation>> levelSets;
                InputStream is =
                        ClassLoader.getSystemClassLoader()
                                .getResourceAsStream(fileName);
            levelSets = getLevelMap(new InputStreamReader(is));
            for (Map.Entry<String,
                    List<LevelInformation>> entry : levelSets.entrySet()) {
                levelSetsMenu.addSelection(entry.getKey().substring(0, 1),
                        entry.getKey().substring(2), new Task<Void>() {
                    public Void run() {
                        GameFlow gameFlow = new GameFlow(
                                new AnimationRunner(gui),
                                gui.getKeyboardSensor(), gui);
                        gameFlow.runLevels(entry.getValue());
                        return null;
                    }
                });
            }
            menu.addSubMenu("s", "Start Game", levelSetsMenu);
            while (true) {
           animationRunner.run(menu);
                Task<Void> task = (Task<Void>) menu.getStatus();
                task.run();
                menu.setStop(false);
                menu.reset();
            }
    }
    /**
     * convert from file into data of map that contain all the details of the
     * levels.
     * @param reader the file of the set levels
     * @return map that the ket is to odd number of the file that contain the
     * name of the level and his message and the value of the map is the
     * level path
     */
    public static Map<String, List<LevelInformation>> getLevelMap(
            java.io.Reader reader) {
        Map<String, List<LevelInformation>> levelMap = new HashMap<>();
        LineNumberReader lnr = null;
        String str;
                try {
            // create new reader
            lnr = new LineNumberReader(reader);
            // read lines till the end of the stream
            String s = null;
            while ((str = lnr.readLine()) != null) {
                if (lnr.getLineNumber() % 2 == 1) {
                     s = str;
                } else {
                    LevelSpecificationReader levelSpecificationReader =
                            new LevelSpecificationReader();
                        InputStream is =
                                ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(str);
                        List<LevelInformation> levelList =
                                levelSpecificationReader.fromReader(
                                        new InputStreamReader(is));
                    levelMap.put(s, levelList);
                }
            }
                    reader.close();
            } catch (Exception e) {
                    System.out.println("cant read the file");
        }
        return levelMap;
    }
    }
