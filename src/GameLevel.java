import biuoop.DrawSurface;
import biuoop.GUI;
import java.util.List;

/**
 * class GameLevel hold the sprites and the collidables, and will run one
 * level until all the blocks gone.
 *
 * @author YuvalSaadati
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter counterBalls; //keeping track of the number of remained (or
    // removed) balls
    private Counter counterBlocks; //keeping track of the number of remained
    // (or
    private Counter counterScore;
    private Counter counterNumberOfLives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracking;
    private LivesIndicator livesIndicator;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    /**
     * the constructor initialize the LevelInformation, KeyboardSensor,
     * AnimationRunner, counter of tha score, counter of the lives, and
     * LivesIndicator.
     * object.
     * @param levelInfor the information about the level
     * @param myKeyboard the keyboardSensor
     * @param myRunner the animation that run
     * @param myGui the gui of the game
     * @param myCunterNumberOfLives counter of the player lives
     * @param myScoreTracking listener of the player score
     * @param myLivesIndicator listener of the player lives
     */
    public GameLevel(LevelInformation levelInfor,
                     biuoop.KeyboardSensor myKeyboard,
                     AnimationRunner myRunner, GUI myGui,
                     Counter myCunterNumberOfLives,
                     ScoreTrackingListener myScoreTracking,
                     LivesIndicator myLivesIndicator) {
        this.levelInformation = levelInfor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        // has this information fron the level info.
        this.counterBalls = new Counter(levelInfor.numberOfBalls());
        this.ballRemover = new BallRemover(this, this.counterBalls);
        // has this information fron the level info.
        this.counterBlocks = new Counter(levelInfor.numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, this.counterBlocks);
        this.counterScore = myScoreTracking.getCounterScore();
        this.scoreTracking = myScoreTracking;
        this.counterNumberOfLives = myCunterNumberOfLives;
        this.livesIndicator = myLivesIndicator;
        this.runner = myRunner;
        this.keyboard = myKeyboard;
        this.running = true;
        this.gui = myGui;
    }

    /**
     * @return gui of the game.
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * @return the collection sprites.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * @return the game environment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @param c the Collidable object that will be added to the game
     *          environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s sprite that will be added to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInformation.getBackground());
        ScoreIndicator scoreSprite = new ScoreIndicator(this.counterScore);
        addSprite(scoreSprite);
        addSprite(new LevelIndicator(this.levelInformation));
        addSprite(this.livesIndicator);
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracking);
        }
        /*
         * creating 4 gray blocks that will be the frame of all the game, the
         * aim of those blocks is to make sure that the ball wount run out. eack
         * block will be added to the sprite collection and also will be added
         * to the collidable list.
         */
        // the left block
        Rectangle left = new Rectangle(0, 26, 25, 475);
        Block leftBlock = new Block(left, java.awt.Color.GRAY, "left");
        leftBlock.addToGame(this);
        leftBlock.setNumberOfHits(-100);
        // the upper block
        Rectangle up = new Rectangle(0, 18, 800, 25);
        Block upBlock = new Block(up, java.awt.Color.GRAY,
                "upper");
        upBlock.addToGame(this);
        upBlock.setNumberOfHits(-100);

        // the bottom block
        Rectangle down = new Rectangle(0, 500, 800, 25);
        Block deathRegion = new Block(down, java.awt.Color.GRAY
                , "deathRegion");
        deathRegion.addToGame(this);
        deathRegion.setNumberOfHits(-100);
        deathRegion.addHitListener(this.ballRemover);
        // the right block
        Rectangle right = new Rectangle(775, 26, 25, 475);
        Block rightBlock = new Block(right, java.awt.Color.GRAY
                , "right");
        rightBlock.addToGame(this);
        rightBlock.setNumberOfHits(-100);
    }

    /**
     * removing Collidable frim the game.
     * @param c the colidable that will remove from the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.deleteCollidable(c);
    }
    /**
     * removing Sprite frim the game.
     * @param s the Sprite that will remove from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.deleteSprite(s);
    }

    /**
     * create 2 balls, paddle, and run the game by drawing the objects on the
     * screen. return when either there are no more balls or no more blocks
     * (as it is currently doing).
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3,
                this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * is in charge of stopping condition.
     * @return stopping condition.
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * doOneFrame(DrawSurface) is in charge of the logic of each level.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        // drawing all the sprite collection
        this.sprites.drawAllOn(d);
        // making the sprite collection moving
        this.sprites.notifyAllTimePassed();
        // when p is press the pause screen come
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    "space", new PauseScreen()));
        }
        // when all the balls gone one live decrease
        if (this.ballRemover.getRemainingBalls() == 0) {
            this.paddle.removeFromGame(this);
            this.livesIndicator.setDecreaseLiveCounter(1);
            this.running = false;
        }
        // when all the blocks gone, thr game ends and the player gets
        // 100 points.
        if (this.blockRemover.getRemainingBlocks() == 0) {
            this.scoreTracking.setincreaseCounter(100);
            this.running = false;
        }
    }
    /**
     * create balls and paddle.
     */
    public void createBallsOnTopOfPaddle() {
        // creating the paddle of the game and adding him to the game
        this.paddle = new Paddle(getGUI(), this.levelInformation.paddleSpeed()
                , this.levelInformation.paddleWidth());
        this.paddle.addToGame(this);
        Ball ball;
        int x = 0;
        // list of all the balls velocities
        List<Velocity> velocities =
                this.levelInformation.initialBallVelocities();
        int size = this.levelInformation.numberOfBalls();
        for (int i = 0; i < size; i++) {
            ball = new Ball(400 + x, 400, 6,
                    this.levelInformation.getBallColor());
            ball.setBallBorder(0, 0, 1000, 1000);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
            x += 10;
        }
        // update the listener with the numbers of balls
        this.ballRemover.setremainingBalls(
                this.levelInformation.numberOfBalls());
    }
    /**
     * @return number of blocks in this level.
     */
    public int getRemainingBlock() {
        return this.blockRemover.getRemainingBlocks();
    }
    /**
     * @return number of lives in the current time.
     */
    public int getRemainingLives() {
        return this.livesIndicator.getLiveCounter();
    }
    /**
     * @return the current score of the player
     */
    public int getScore() {
        return this.scoreTracking.getScore();
    }
    /**
     * @param stop if the animation should stop
     */
    public void setStop(boolean stop) {
        this.running = stop;
    }
}
