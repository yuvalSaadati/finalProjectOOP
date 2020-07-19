/**
 * information about the score of each player.
 */
public class ScoreInfo implements java.io.Serializable {
    private String name;
    private int score;
    /**
     * constructor.
     * @param myName the name of the player
     * @param myScore the score of the player
     */
    public ScoreInfo(String myName, int myScore) {
        this.name = myName;
        this.score = myScore;
    }
    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }
}
