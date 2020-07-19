/**
 * class Point A point has an x and a y value, and can measure the distance to
 * other points, and if its is equal to another point.
 *
 * @author YuvalSaadati
 */

public class Point {
    private double x;
    private double y;

    /**
     * constructor to initialize the class fields.
     *
     * @param x is the x axis value of the point
     * @param y is the y axis value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method measure the distance to other points.
     *
     * @param other is other point
     * @return rootSum the distance of this point to the other point
     */
    public double distance(Point other) {
        double sum = ((other.x - this.x) * (other.x - this.x)
                + (other.y - this.y) * (other.y - this.y));
        double rootSum = Math.sqrt(sum);
        return rootSum;

    }

    /**
     * The method checks whether two points are equal.
     *
     * @param other is other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) <= 0.1
                && Math.abs(this.y - other.y) <= 0.1);

    }

    /**
     * Returns the position of the point by the x-axis.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the position of the point by the y-axis.
     *
     * @return the y values of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * updating.
     * @param x1 is the new value of x at the point
     * @param y1 is the new value of y at the point
     */
    public void setPoint(double x1, double y1) {
        this.x = x1;
        this.y = y1;
    }
}
