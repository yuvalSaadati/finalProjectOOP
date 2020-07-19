import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * manage a table of size high-scores.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreInfosList;
    /**
     * constructor.
     * @param mySize the size means that the table holds up to size top scores.
     * the char
     */
    public HighScoresTable(int mySize) {
        this.size = mySize;
        this.scoreInfosList = new ArrayList<ScoreInfo>(mySize);
    }

    /**
     * Add a high-score.
     * @param myScore adding this high-score
     */
    public void add(ScoreInfo myScore) {
        if (this.scoreInfosList.isEmpty()) {
            this.scoreInfosList.add(myScore);
            return;
        } else {
            this.scoreInfosList = addMyScore(myScore);
        }
    }

    /**
     * @return if the list is full or not
     */
    public boolean isFullList() {
        return (this.scoreInfosList.size() >= size());
    }
    /**
     * @param myScore all the information of the score
     * @return new list of scores with thr current score
     */
    public List<ScoreInfo> addMyScore(ScoreInfo myScore) {
        List<ScoreInfo> l = new ArrayList<>(size());
        // the current size of the list
        int s = this.scoreInfosList.size() - 1;
        if (myScore.getScore() >= this.scoreInfosList.get(0).getScore()) {
            if (isFullList()) {
                // the list is full so we need to remove one persone from the
                // table in order to have place
                this.scoreInfosList.remove(size() - 1);
                l.add(myScore);
                l.addAll(this.scoreInfosList);
            } else {
                // adding a score to a list that is not full
                l.add(myScore);
                l.addAll(this.scoreInfosList);
            }
            return l;
        } else if (myScore.getScore() >= this.scoreInfosList.get(s).getScore()) {
                if (isFullList()) {
                    l = this.scoreInfosList;
                    l.remove(size() - 1);
                    l.add(myScore);
                } else {
                        l = this.scoreInfosList;
                        l.add(myScore);
                    }
                return l;
            }
        List<ScoreInfo> copyScoreInfoList = this.scoreInfosList;
        copyScoreInfoList.add(myScore);
        for (int i = 0; i < this.scoreInfosList.size() - 1; i++) {
            if (this.scoreInfosList.get(i).getScore() >= myScore.getScore()
                    && copyScoreInfoList.get(i + 1).getScore()
                    <= myScore.getScore()) {
                l.add(this.scoreInfosList.get(i));
                // coping the list
                l.add(myScore);
            } else {
                l.add(this.scoreInfosList.get(i));
            }
        }
        return l;
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * The list is sorted such that the highest scores come first.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfosList;
    }

    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     * @param score the value of the score
     * @return the rank of the score
     */
    public int getRank(int score) {
        // Rank 1 means the score will be highest on the list.
        if (!this.scoreInfosList.isEmpty()) {
            if (score >= this.scoreInfosList.get(0).getScore()) {
                return 1;
            } else if (this.scoreInfosList.size() == size()) {
                if (score >= this.scoreInfosList.get(size() - 1).getScore()) {
                    return 1;
                }
            } else {
                // the score is too low and will not be added to the list
                return -1;
            }
        }
        return 1;
    }

    /**
     * @param score the value of the current score
     * @return if we can insert score to the table
     */
    public boolean canInsert(int score) {
        if (this.scoreInfosList.size() == size()) {
            return this.scoreInfosList.get(size() - 1).getScore() < score;
        } else {
            return true;
        }
    }

    /**
     * Load table data from file.
     * @param filename the name of the file that all the scores are at.
     * @throws IOException cant read the file
     */
    public void load(File filename) throws IOException {
        ScoreInfo s;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(filename.getName());
            in = new ObjectInputStream(fileIn);
            while ((s = (ScoreInfo) in.readObject()) != null) {
                    this.add(s);
            }
            in.close();
            fileIn.close();
            return;
        } catch (IOException i) {
            System.out.println("cant read the file");
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ScoreInfo class not found");
            c.printStackTrace();
            return;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    /**
     * Save table data to the specified file.
     * saving the list into the file.
     * @param filename the name of the file that all the scores are at
     * @throws IOException cant read the file
     */
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filename.getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // put all the scores on the file
            for (ScoreInfo s : this.scoreInfosList) {
                out.writeObject(s);
            }
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename the name of the file that all the scores are at
     * @return cant read the file
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(10);
        try {
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (IOException i) {
            i.printStackTrace();
        }
        return highScoresTable;
    }
}
