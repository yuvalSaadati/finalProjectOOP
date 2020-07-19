import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import biuoop.DialogManager;

/**
 * This class will be in charge of creating the differnet levels,
 * and moving from one level to the next.
 * @param <T> the type of the game
 */
public class GameFlow<T> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter counterScore;
    private Counter counterNumberOfLives;
    private ScoreTrackingListener scoreTracking;
    private LivesIndicator livesIndicator;
    /**
     * constructor.
     * @param ar current animation runner
     * @param ks the keyboard sensor
     * @param myGui the gui of this game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI myGui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = myGui;
        this.counterNumberOfLives = new Counter(7);
        this.counterScore = new Counter(0);
        this.livesIndicator = new LivesIndicator(this.counterNumberOfLives);
        this.scoreTracking = new ScoreTrackingListener(this.counterScore);
    }

    /**
     * moving from one level to the next.
     * @param levels list that include all the levels in the game
     */
    public void runLevels(List<LevelInformation> levels) {
        this.scoreTracking = new ScoreTrackingListener(this.counterScore);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.gui,
                    this.counterNumberOfLives,
                    this.scoreTracking, this.livesIndicator);
            level.initialize();
            // play each level until finshing all the block
            while (level.getRemainingBlock() > 0
                    && level.getRemainingLives() > 0) {
                level.playOneTurn();
            }
            // when all the leves of the player ended the game ending so
            // the loop stop
            if (level.getRemainingLives() < 1) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, "space",
                        new EndScreen(getScore())));
                break;
            }
        }
        // if after running all the levels the player still has lives it is
        // mean that he won the game
        if (this.livesIndicator.getLiveCounter() > 0) {
            this.animationRunner.run(
                    new KeyPressStoppableAnimation(this.keyboardSensor,
                            "space", new WinEndScreen(getScore())));
        }
        File f = new File("highscores.ser");
        HighScoresTable highScoresTable =
                HighScoresTable.loadFromFile(f);
        if (highScoresTable.canInsert(getScore())) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo s = new ScoreInfo(name, getScore());
            highScoresTable.add(s);
            try {
                highScoresTable.save(f);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                gui.getKeyboardSensor(), "space",
                new HighScoresAnimation(highScoresTable)));
    }
    /**
     * @return the current score of the player
     */
    public int getScore() {
        return this.scoreTracking.getScore();
    }
}
