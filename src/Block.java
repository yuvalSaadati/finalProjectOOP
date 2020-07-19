import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * class Block is also type Collidable and Sprite and has the files
 * collisionRectangle, color and numberOfHits.
 *
 * @author YuvalSaadati
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // static value that will represented the block walls
    private static final int LEFT_BLOCK = 1;
    private static final int RIGHT_BLOCK = 2;
    private static final int UPPER_BLOCK = 3;
    private static final int BOTTOM_BLOCK = 4;

    private Rectangle collisionRectangle;
    private java.awt.Color color;
    private int numberOfHits = 1;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private String blockName;
    private Color stroke;
    private List<String> fillArray;
    private Image fillImage;
    private boolean firstColor = true;
    /**
     * constructor to initialize the class fields by rectangle, color, and the
     * number of hits will 2 for all blocks.
     *
     * @param collision is the rectangl that is the block.
     * @param color     the color of each block.
     * @param name of this block
     */
    public Block(Rectangle collision,
                 java.awt.Color color, String name) {
        this.collisionRectangle = collision;
        this.color = color;
        this.blockName = name;
    }
    /**
     * constructor to initialize the class fields.
     * build a block from file that contain strike.
     * @param collision is the rectangl that is the block.
     * @param myStroke     the color of the frame of the block.
     * @param name of this block
     * @param myFillArray the names of the filles of thr block
     * @param hitPoints the current hit points of the block
     */
    public Block(Rectangle collision, String name, Color myStroke,
                 List<String> myFillArray, int hitPoints) {
        this.collisionRectangle = collision;
        this.blockName = name;
        this.stroke = myStroke;
        this.fillArray = myFillArray;
        this.numberOfHits = hitPoints;
        this.hitColor();
    }
    /**
     * constructor to initialize the class fields.
     * build a block from file without strike.
     * @param collision is the rectangl that is the block.
     * @param name of this block
     * @param myFillArray the names of the filles of thr block
     * @param hitPoints the current hit points of the block
     */
    public Block(Rectangle collision, String name,
                 List<String> myFillArray, int hitPoints) {
        this.collisionRectangle = collision;
        this.blockName = name;
        this.fillArray = myFillArray;
        this.numberOfHits = hitPoints;
        this.hitColor();
    }
    /**
     * @return the block name.
     */
    public String getBlockName() {
        return this.blockName;
    }
    /**
     * @return the numbers of hits.
     */
    public int getHitPoints() {
        return this.numberOfHits;
    }

    /**
     * set the value of numberOfHits file. this files is the number that will be
     * show on each block in white font.
     *
     * @param n the color of each block.
     */
    public void setNumberOfHits(int n) {
        this.numberOfHits = n;
    }

    /**
     * at each hit this method will be called. at each hit the sum of numbers of
     * hit decrease in 1.
     *
     */
    public void declineNumberOfHits() {
        this.numberOfHits -= 1;
    }

    /**
     * @return the "collision shape" of the object.
     *
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     * @param hitter the ball that hit this block
     * @param collisionPoint  the point on the block that the ball collided
     *                        with.
     * @param currentVelocity the velocity of the ball.
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        // at each hit the sum of hits that represent on the blocks decrease in
        // 1 at every hit.
        if (this.getBlockName().equals("b")) {
            this.fillImage = null;
            this.color = null;
            declineNumberOfHits();
            // loading the next color of the block
            this.hitColor();
        }
        //hit the bootom
        if (this.getBlockName().equals("deathRegion")) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        // creating 4 points that are the vertex of the block.
        Point leftUp = getCollisionRectangle().getUpperLeft();
        Point rightUp = new Point(
                getCollisionRectangle().getUpperLeft().getX()
                        + getCollisionRectangle().getWidth(),
                getCollisionRectangle().getUpperLeft().getY());
        Point leftDown = new Point(
                getCollisionRectangle().getUpperLeft().getX(),
                getCollisionRectangle().getUpperLeft().getY()
                        + getCollisionRectangle().getHeight());
        Point rightDown = new Point(
                leftDown.getX() + getCollisionRectangle().getWidth(),
                getCollisionRectangle().getUpperLeft().getY()
                        + getCollisionRectangle().getHeight());

        // collide with the UpperLeft vertex
        if (Math.abs(collisionPoint.getX() - leftUp.getX()) <= 1
                && Math.abs(collisionPoint.getY() - leftUp.getY()) <= 1) {
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        // collide with the rightUp vertex
        if (Math.abs(collisionPoint.getX() - rightUp.getX()) <= 1
                && Math.abs(collisionPoint.getY() - rightUp.getY()) <= 1) {
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        // collide with the leftDown vertex
        if (Math.abs(collisionPoint.getX() - leftDown.getX()) <= 2
                && Math.abs(collisionPoint.getY() - leftDown.getY()) <= 1) {
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        // collide with the rightDown vertex
        if (Math.abs(collisionPoint.getX() - rightDown.getX()) <= 2
                && Math.abs(collisionPoint.getY() - rightDown.getY()) <= 1) {
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy() * -1);

        }

        /*
         * in the following terms we will check in which wall did the ball hit
         * and based on this information, changing the velocity of the ball.
         */

        if (whichBlockCollide(collisionPoint) == LEFT_BLOCK) {
            // the ball collided with the left wall of the block so the velocity
            // on the 'x' axis will change.
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        if (whichBlockCollide(collisionPoint) == RIGHT_BLOCK) {
            // the ball collided with the right wall of the block so the
            // velocity
            // on the 'x' axis will change.
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        if (whichBlockCollide(collisionPoint) == UPPER_BLOCK) {
            // the ball collided with the upper wall of the block so the
            // velocity
            // on the 'y' axis will change.
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        // the ball collided with the bottom wall of the block so the velocity
        // on the 'y' axis will change.
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx(),
                -1 * currentVelocity.getDy());
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a
     * given.
     *
     * @param collisionPoint the point where the block and the ball collided.
     * @return a number that represented the wall of the block that the ball
     *         collided with.
     */
    public int whichBlockCollide(Point collisionPoint) {
        // left up vertex of the ball
        Point leftUp = getCollisionRectangle().getUpperLeft();
        // right up vertex of the ball
        Point rightUp = new Point(
                getCollisionRectangle().getUpperLeft().getX()
                        + getCollisionRectangle().getWidth(),
                getCollisionRectangle().getUpperLeft().getY());
        // left down vertex of the ball
        Point leftDown = new Point(
                getCollisionRectangle().getUpperLeft().getX(),
                getCollisionRectangle().getUpperLeft().getY()
                        + getCollisionRectangle().getHeight());
        // right down vertex of the ball
        Point rightDown = new Point(
                leftDown.getX() + getCollisionRectangle().getWidth(),
                getCollisionRectangle().getUpperLeft().getY()
                        + getCollisionRectangle().getHeight());

        if (collisionPoint != null) {
            if (collisionPoint.getY() <= leftDown.getY() + 0.001
                    && collisionPoint.getY() >= leftUp.getY() && Math.abs(
                            leftUp.getX() - collisionPoint.getX()) <= 0.001) {
                /*
                 * the collision point is on the left block based on the value
                 * of the collision point and value of the block
                 */
                return LEFT_BLOCK;
            }
            if (collisionPoint.getY() <= rightDown.getY() + 0.001
                    && collisionPoint.getY() >= rightUp.getY() && Math.abs(
                            rightUp.getX() - collisionPoint.getX()) <= 0.001) {
                /*
                 * the collision point is on the right block based on the value
                 * of the collision point and value of the block
                 */
                return RIGHT_BLOCK;
            }
            if (collisionPoint.getX() <= rightUp.getX() + 0.001
                    && collisionPoint.getX() >= leftUp.getX() && Math.abs(
                            leftUp.getY() - collisionPoint.getY()) <= 0.001) {
                /*
                 * the collision point is on the upper block based on the value
                 * of the collision point and value of the block
                 */
                return UPPER_BLOCK;
            }
            if (collisionPoint.getX() <= rightDown.getX() + 0.001
                    && collisionPoint.getX() >= rightDown.getX()
                    && Math.abs(rightDown.getY()
                            - collisionPoint.getY()) <= 0.001) {
                /*
                 * the collision point is on the bottom block based on the value
                 * of the collision point and value of the block
                 */
                return BOTTOM_BLOCK;
            }
        }
        return 0;
    }

    /**
     * drawing the block frame and fill it by the giving color in the file. also
     * drawing numbers on each block based on the sum of hits it has.
     * checking the type of the the fill and by it fill the block.
     * set the fill color of the block based on the file data.
     * @param surface e surface that all the game will be on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
//data of the block - rectangle
        int x = (int) this.collisionRectangle.getUpperLeft().getX();
        int y = (int) this.collisionRectangle.getUpperLeft().getY();
        int width = (int) this.collisionRectangle.getWidth();
        int height = (int) this.collisionRectangle.getHeight();
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            // drawing the frame of the block
            surface.drawRectangle(x, y, width, height);
        }
        if (this.fillImage != null) {
            surface.drawImage(x, y, this.fillImage);
            return;
        }
        if (this.stroke != null) {
            surface.setColor(this.color);
            // fill the frame by the size of the rectangle
            surface.fillRectangle(x + 1, y + 1, width - 1, height - 1);
        } else {
            surface.setColor(this.color);
            // fill the frame by the size of the rectangle
            surface.fillRectangle(x, y, width, height);
        }
    }
    /**
     * change the color of the block by the details given in the level fil.
     */
public void hitColor() {
    for (int i = 0; i < this.fillArray.size(); i++) {
        String[] fillLine = this.fillArray.get(i).split(":");
            int number =
                    Integer.parseInt(Character.toString(
                            fillLine[0].charAt(5)));
            if (this.numberOfHits == number) {
                if (this.fillArray.get(i).contains("color")) {
                    this.color =
                            ColorsParser.colorFromString(this.fillArray.get(i).substring(7));
                    break;
                } else {
                    String imageName = fillLine[1].substring(6, fillLine[1].length() - 1);
                    InputStream is =
                            ClassLoader.getSystemClassLoader().getResourceAsStream(imageName);
                    Image img = null;
                    try {
                        img = ImageIO.read(is);
                    } catch (IOException e) {
                        System.out.println("can not load thw image " + imageName);
                    }
                    this.fillImage = img;
                }
            }
        }
    }

    /**
     * @return the width of this block
     */
    public int getWidth() {
        return (int) this.getCollisionRectangle().getWidth();
    }
    /**
     * the method to nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adding sprite to the sprite colliction in the game environment and adding
     * collidable objects.
     *
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * removing this block from the collidable list and from the sprite
     * collection.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Add hl as a listener to hit events.
     * @param hl the listener that will be add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener that will be delete
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * the method make a copy of the hitListeners before iterating over them
     * and notify all listeners about a hit event.
     * @param hitter the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
