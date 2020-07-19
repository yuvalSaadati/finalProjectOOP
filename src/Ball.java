import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class Ball have size (radius), color, location (by 2 Points) and game
 * environment.
 *
 * @author YuvalSaadati
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point limitLeft;
    private Point limitRight;
    private GameEnvironment gameEnvironment;

    /**
     * constructor to initialize the class fields by size (radius), color, and
     * location (a Point).
     *
     * @param center is the center of the ball
     * @param r      is the size (radius) of the ball
     * @param color  of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.r = r;
        this.center = center;
        this.color = color;
    }

    /**
     * constructor to initialize the class fields by size (radius), color, and
     * location (x, y values which are the center of the ball).
     *
     * @param x     value of the center of the ball by the 'x' axes.
     * @param y     value of the center of the ball by the 'y' axes.
     * @param r     is the size (radius) of the ball
     * @param color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.r = r;
        this.center = new Point(x, y);
        this.color = new Color(59, 229, 195);
    }

    /**
     * setter method in order to set the gameEnvironment file. at any time that
     * a new ball will be created, this method will come after in order to add
     * the ball to the gameEnvironment of the game.
     *
     * @param environment the place where all the objects in the game in.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * constructor to initialize the class fields of limitLeft and limitRight by
     * creating 2 new points that will be the limit of each ball.
     *
     * @param xLeft  'x' axes of the up left point
     * @param yLeft  'y' axes of the up left point
     * @param xRight 'x' axes of the down right point
     * @param yRight 'y' axes of the down right point
     */
    public void setBallBorder(int xLeft, int yLeft, int xRight, int yRight) {
        this.limitLeft = new Point(xLeft, yLeft);
        this.limitRight = new Point(xRight, yRight);
    }

    /**
     * create new point that is the upper left point of the ball border.
     *
     * @return border the left up point of the ball border
     */
    public Point getBallBorderLeft() {
        Point border = new Point((int) this.limitLeft.getX(),
                (int) this.limitLeft.getY());
        return border;
    }

    /**
     * create new point that is the down right point of the ball border.
     *
     * @return border the down right point of the ball border
     */
    public Point getBallBorderRight() {
        Point border = new Point((int) this.limitRight.getX(),
                (int) this.limitRight.getY());
        return border;
    }

    /**
     * return the value of the axes 'x' of the center of the ball.
     *
     * @return the y position of the center of the ball
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * return the value of the axes 'y' of the center of the ball.
     *
     * @return the y position of the center of the ball
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * return the radius of the ball which is also the size of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * return the color of the ball which is a constructor.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface by constructor files: color, size,
     * location.
     *
     * @param surface type DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), r);
    }

    /**
     * set the value of the velocity by the new velocity.
     *
     * @param velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * create new velocity with the new values of the position on the `x` and
     * 'y' axes.
     *
     * @param dx position on the `x` axes
     * @param dy position on the `y` axes
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * return the velocity of the ball.
     *
     * @return the velocity of the ball which initialized in the constructor.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * the method make sure that the ball does not go outside of the screen.
     * when it hits the border to the left or to the right, it change its
     * horizontal direction, and when it hits the border on the top or the
     * bottom, it change its vertical direction.
     */
    public void hitTheBord() {
        /*
         * when it hits the border to the left or to the right, it change its
         * horizontal direction by setting the velocity's dx to -dx
         */
        if (this.center.getX() - this.r <= this.getBallBorderLeft().getX()
                || this.center.getX() + this.r >= this.getBallBorderRight()
                        .getX()) {
            this.setVelocity((-1 * this.v.getDx()), this.v.getDy());
        }
        /*
         * when it hits the border top or the bottom, it change its vertical
         * direction by setting the velocity's dy to -dy
         */
        if (this.center.getY() - r <= this.getBallBorderLeft().getY()
                || this.center.getY() + r >= this.getBallBorderRight().getY()) {
            this.setVelocity(this.v.getDx(), (-1 * this.v.getDy()));
        }
    }

    /**
     * the method move the ball to the next point by the valocity.
     * trajectory line will be a imaginary line in the game. it will
     * represent the distance and the angle between the ball and block or
     * any collidable object.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.v.getDx(),
                this.center.getY() + this.v.getDy());
        //the closet collision between the ball and block.
        CollisionInfo collision = this.gameEnvironment
                .getClosestCollision(trajectory);
        hitTheBord();
        if (collision == null) {
            // the ball did not collide with any of the collidables so he will
            // keep moving normal by his value.
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // Create 3 corners of block
            Point leftUp = collision.collisionObject().getCollisionRectangle()
                    .getUpperLeft();
            Point rightUp = new Point(leftUp.getX() + collision
                    .collisionObject().getCollisionRectangle().getWidth(),
                    leftUp.getY());
            Point leftDown = new Point(leftUp.getX(), leftUp.getY()
                    + collision
                    .collisionObject().getCollisionRectangle().getHeight());
            Point rightDown = new Point(
                    leftDown.getX() + collision.collisionObject()
                            .getCollisionRectangle().getWidth(),
                    collision.collisionObject().getCollisionRectangle()
                            .getUpperLeft().getY()
                            + collision.collisionObject()
                            .getCollisionRectangle().getHeight());
            // A collision point exists the hit method will return new
            // velocity based on the collisionObject data.
            Velocity newV = collision.collisionObject()
                    .hit(this, collision.collisionPoint(), this.v);
            this.v = newV;
            double speed =
                    Math.sqrt((this.v.getDx() * this.v.getDx())
                            + (this.v.getDy() * this.v.getDy()));
            // if the ball got inside the block, removing it to the edge of
            // the collision point
            if (Math.abs(this.center.getY() - collision.collisionPoint().getY())
                    <= 0.25 * this.r && this.v.getDy() < 0 && speed > 12) {
                // The ball is on the bottom of the block
                this.center.setPoint(this.center.getX(),
                        collision.collisionPoint().getY()
                                - 0.5 * this.v.getDy() - 1.5 * this.r);
            } else if (Math.abs(this.center.getY()
                    - collision.collisionPoint().getY())
                    <= this.r && this.v.getDy() < 0) {
                // The ball is on the bottom of the block
                this.center.setPoint(this.center.getX(),
                        collision.collisionPoint().getY()
                                - 0.5 * this.v.getDy() - this.r);
            } else if (Math.abs(this.center.getY()
                    - collision.collisionPoint().getY())
                    <= 0.25 * this.r && this.v.getDy() > 0 && speed > 12) {
                // The ball is on the top of the block
                this.center.setPoint(this.center.getX(),
                        collision.collisionPoint().getY()
                                -  0.5 * this.v.getDy() + 1.5 * this.r);
            } else if (Math.abs(this.center.getY()
                    - collision.collisionPoint().getY())
                    <= this.r && this.v.getDy() > 0) {
                // The ball is on the top of the block
                this.center.setPoint(this.center.getX(),
                        collision.collisionPoint().getY()
                                -  0.5 * this.v.getDy() + this.r);
            } else if (Math.abs(this.center.getX()
                    - collision.collisionPoint().getX())
                    <=  this.r && this.v.getDx() > 0) {
                // The ball is on the left side of the block
                this.center.setPoint(collision.collisionPoint().getX()
                                -  0.5 * this.v.getDx() + this.r,
                        this.center.getY());
            } else if (Math.abs(this.center.getX()
                    - collision.collisionPoint().getX())
                    <=  0.25 * this.r && this.v.getDx() > 0 && speed > 12) {
                // The ball is on the left side of the block
                this.center.setPoint(collision.collisionPoint().getX()
                                -  0.5 * this.v.getDx() + 1.5 * this.r,
                        this.center.getY());
            } else if (Math.abs(this.center.getX()
                    - collision.collisionPoint().getX())
                    <= this.r && this.v.getDx() < 0) {
                // The ball is on the right side of the block
                this.center.setPoint(collision.collisionPoint().getX()
                                -  0.5 * this.v.getDx() - this.r,
                        this.center.getY());
            } else if (Math.abs(this.center.getX()
                    - collision.collisionPoint().getX())
                    <= 0.25 * this.r && this.v.getDx() < 0 && speed > 12) {
                // The ball is on the right side of the block
                this.center.setPoint(collision.collisionPoint().getX()
                                -  0.5 * this.v.getDx() - 1.5 * this.r,
                        this.center.getY());
            } else if (Math.abs(this.center.getX() - leftUp.getX()) <= 0.1
                    && Math.abs(this.center.getY() - leftUp.getY()) <= 0.1) {
                // if the ball got to the vertex of the block, removing it to
                // outside
                this.center.setPoint(leftUp.getX() - this.r,
                        leftUp.getY() - this.r);
            } else if (Math.abs(this.center.getX() - rightUp.getX()) <= 0.1
                    && Math.abs(this.center.getY() - rightUp.getY()) <= 0.1) {
                this.center.setPoint(rightUp.getX() + this.r,
                        rightUp.getY() - this.r);
            } else if (Math.abs(this.center.getX() - leftDown.getX()) <= 0.1
                    && Math.abs(this.center.getY() - leftDown.getY()) <= 0.1) {
                this.center.setPoint(leftDown.getX() - this.r,
                        leftDown.getY() + this.r);
            } else if (Math.abs(this.center.getX() - rightDown.getX()) <= 1
                    && Math.abs(this.center.getY() - rightDown.getY()) <= 1) {
                this.center.setPoint(rightDown.getX() + this.r,
                        rightDown.getY() + this.r);
            } else if (this.center.getX() <= rightUp.getX() + this.r
                    && this.center.getX() >= leftUp.getX() - this.r
                    && this.center.getY() >= leftUp.getY() - this.r
                    && this.center.getY() <= leftDown.getY() + this.r) {
                // In case that after all the conditions the ball enters into the
                // block we will take it out by copying out, based on ball data and
                // block data
                if (this.v.getDy() > 0 && this.v.getDx() > 0) {
                    // the ball came from the bottom of the block
                    this.center.setPoint(collision.collisionPoint().getX()
                                    - this.r,
                            collision.collisionPoint().getY() - this.r);
                } else if (this.v.getDy() > 0 && this.v.getDx() < 0) {
                    // the ball came from the bottom of the block
                    this.center.setPoint(collision.collisionPoint().getX()
                                    + this.r,
                            collision.collisionPoint().getY() - this.r);
                } else if (this.v.getDy() < 0 && this.v.getDx() < 0) {
                    // the ball came from the bottom of the block
                    this.center.setPoint(collision.collisionPoint().getX()
                                    + this.r,
                            collision.collisionPoint().getY() + this.r);
                } else if (this.v.getDy() < 0 && this.v.getDx() > 0) {
                    // the ball came from the bottom of the block
                    this.center.setPoint(collision.collisionPoint().getX()
                                    - this.r,
                            collision.collisionPoint().getY() + this.r);
                }
            }
        }
    }

    /**
     * implementaion Sprite interface. the method do the same like the method
     * moveOneStep.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * implementaion Sprite interface. the method add the object ball to the
     * sprite collection in the game.
     *
     * @param game the object that the game run on
     */
    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }
    /**
     * @param game the object game that this ball will be remove from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
