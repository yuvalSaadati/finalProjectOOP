
import biuoop.DrawSurface;

/**
 * class Paddle is the player in the game. It is a rectangle that is controlled
 * by the arrow keys, and moves according to the player key presses. It should
 * implement the Sprite and the Collidable interface
 *
 * @author YuvalSaadati
 */
public class Paddle implements Sprite, Collidable {
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIFTH = 4;

    private biuoop.KeyboardSensor keyboard;
    private biuoop.GUI gui;
    private Rectangle paddleTop;
    private Rectangle paddleBottom;
    private int paddleSpeed;
    private int width;

    /**
     * constructor to initialize the gui and keyboard files. the size of paddle
     * will be 70x20
     *
     * @param myPaddleSpeed the speen of the paddle
     * @param gui of the game
     * @param paddleWidth the width of the paddle
     */
    public Paddle(biuoop.GUI gui, int myPaddleSpeed, int paddleWidth) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.paddleSpeed = myPaddleSpeed;
        this.width = paddleWidth;
        this.paddleTop = new Rectangle(400 - (width / 2), 455, width,
                5);
        this.paddleBottom = new Rectangle(400 - (width / 2), 460, width,
                15);
    }

    /**
     * making the paddle moving left be decreasing 5 inches on the x axes of the
     * paddle.
     */
    public void moveLeft() {
        if (this.paddleTop.getUpperLeft().getX() > 25) {
            this.paddleTop.setRectangle(
                    this.paddleTop.getUpperLeft().getX() - this.paddleSpeed,
                    this.paddleTop.getUpperLeft().getY(),
                    this.paddleTop.getWidth(), this.paddleTop.getHeight());
        }
        if (this.paddleBottom.getUpperLeft().getX() > 25) {
            this.paddleBottom.setRectangle(
                    this.paddleBottom.getUpperLeft().getX()
                            - this.paddleSpeed,
                    this.paddleBottom.getUpperLeft().getY(),
                    this.paddleBottom.getWidth(),
                    this.paddleBottom.getHeight());
        }
    }

    /**
     * making the paddle moving left be adding 5 inches on the x axes of the
     * paddle.
     */
    public void moveRight() {
        if (this.paddleTop.getUpperLeft().getX() + this.width < 775) {
            this.paddleTop.setRectangle(
                    this.paddleTop.getUpperLeft().getX() + this.paddleSpeed,
                    this.paddleTop.getUpperLeft().getY(),
                    this.paddleTop.getWidth(), this.paddleTop.getHeight());
        }
        if (this.paddleBottom.getUpperLeft().getX() + this.width < 775) {
            this.paddleBottom.setRectangle(
                    this.paddleBottom.getUpperLeft().getX()
                            + this.paddleSpeed,
                    this.paddleBottom.getUpperLeft().getY(),
                    this.paddleBottom.getWidth(),
                    this.paddleBottom.getHeight());
        }
    }

    /**
     * Sprite method. the method check if the left or right keys are pressed,
     * and if so move it accordingly. by using the methods moveLeft and
     * moveRight.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }

    }

    /**
     * drawing rectangle based on the size that are in the file of paddleTop.
     *
     * @param d the surface of the game
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.YELLOW);
        d.fillRectangle((int) this.paddleTop.getUpperLeft().getX(),
                (int) this.paddleTop.getUpperLeft().getY(),
                (int) this.paddleTop.getWidth(),
                (int) this.paddleTop.getHeight());

        d.fillRectangle((int) this.paddleBottom.getUpperLeft().getX(),
                (int) this.paddleBottom.getUpperLeft().getY(),
                (int) this.paddleBottom.getWidth(),
                (int) this.paddleBottom.getHeight());
    }

    /**
     * method of Collidable interface.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleTop;
    }

    /**
     *
     * this method will be call in order to change the velocity of ball that hit
     * this paddle. the paddle as having 5 equally-spaced regions. The behavior
     * of the ball's bounce depends on where it hits the paddle : regions 1 -
     * the angle will be 60. regions 2 - the angle will be 30. regions 3 - like
     * hitting block. regions 4 - the angle will be 300. regions 5 - the angle
     * will be 330. if the ball hit the paddle in his left side the angle will
     * be 60, the ball hit the paddle in his right side the angle will be 330.
     *
     * @param hitter the ball that hit the paddle
     * @param collisionPoint  of the object
     * @param currentVelocity of the object
     * @return the new valocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity v;
        double speed = fromDXDYtoSpeed(currentVelocity.getDx(),
                currentVelocity.getDy());

        // the ball hit the paddle in his left side
        if (collisionPoint.getY() >= this.paddleTop.getUpperLeft().getY()
                && collisionPoint.getY() >= this.paddleTop.getUpperLeft().getY()
                + this.paddleTop.getHeight()
                && Math.abs(this.paddleTop.getUpperLeft().getX()
                - collisionPoint.getX()) <= 1) {
            v = Velocity.fromAngleAndSpeed(210, speed);
            return v;
        }
        // the ball hit the paddle in his right side
        if (collisionPoint.getY() >= this.paddleTop.getUpperLeft().getY()
                && collisionPoint.getY() >= this.paddleTop.getUpperLeft().getY()
                + this.paddleTop.getHeight()
                && Math.abs(this.paddleTop.getUpperLeft().getX()
                + this.paddleTop.getWidth()
                - collisionPoint.getX()) <= 1) {
            v = Velocity.fromAngleAndSpeed(330, speed);
            return v;
        }

        /*
         * finding the region that the collisionPoint on and changing the angle
         * accordingly
         */
        if (part12345(collisionPoint) == FIRST) {
            v = Velocity.fromAngleAndSpeed(210, speed);
            return v;
        }
        if (part12345(collisionPoint) == SECOND) {
            v = Velocity.fromAngleAndSpeed(240, speed);
            return v;
        }
        if (part12345(collisionPoint) == THIRD) {
            return new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        if (part12345(collisionPoint) == FOURTH) {
            v = Velocity.fromAngleAndSpeed(300, speed);
            return v;
        }
        if (part12345(collisionPoint) == FIFTH) {
            v = Velocity.fromAngleAndSpeed(330, speed);
            return v;
        }
        return new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * by using Pythagoras formula finding the angle of the object.
     *
     * @param dx of the current velocity
     * @param dy of the current velocity
     * @return the speed of the object
     */
    public double fromDXDYtoSpeed(double dx, double dy) {
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        return speed;
    }

    /**
     * finding the region that the collisionPoint on.
     *
     * @param collisionPoint of the object
     * @return a number that represents the region of the paddle
     */
    public int part12345(Point collisionPoint) {
        double bullX = collisionPoint.getX();
        double limitX = this.paddleTop.getUpperLeft().getX();
        // divide the paddle into five parts
        double part = this.paddleTop.getWidth() / 5;
        if (bullX >= limitX && bullX <= limitX + part) {
            return FIRST;
        }
        if (bullX >= limitX + part && bullX <= limitX + 2 * part) {
            return SECOND;
        }
        if (bullX >= limitX + 2 * part && bullX <= limitX + 3 * part) {
            return THIRD;
        }
        if (bullX >= limitX + 3 * part && bullX <= limitX + part * 4) {
            return FOURTH;
        }
        if (bullX >= limitX + 4 * part && bullX <= limitX + part * 5) {
            return FIFTH;
        }
        return 0;

    }

    /**
     * Add this paddle to the game, by adding it to the sprite and collidable
     * lists.
     *
     * @param g the game object
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * remove this paddle from the game.
     * @param g he current game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
