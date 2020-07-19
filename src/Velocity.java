/**
 * class Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author YuvalSaadati
 */
public class Velocity {

    // position on the `x` axes
    private double dx;

    // position on the'y` axes
    private double dy;

    /**
     * Calculate the velocity of the ball by angle and speed.
     *
     * @param angle of the ball
     * @param speed of the ball
     * @return the new Velocity
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }


    /**
     * constructor to initialize the class fields by 2 values.
     *
     * @param x position on the `x` axes
     * @param y position on the `y` axes
     */
    public Velocity(double x, double y) {
        this.dx = x;
        this.dy = y;

    }

    /**
     * set the value of dx and dy of the current velocity.
     *
     * @param x the dx of the velocity
     * @param y the dy of the velocity
     */
    public void setVelocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     *
     * @param p point that that the values will be added to it
     * @return new point with new values
     */
    public Point applyToPoint(Point p) {
        Point pNew = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return pNew;
    }

    /**
     * return the value of the constructor dx.
     *
     * @return position on the `x` axes
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * return the value of the constructor dy.
     *
     * @return position on the `y` axes
     */
    public double getDy() {
        return this.dy;
    }
}
