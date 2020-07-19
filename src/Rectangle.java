import java.util.ArrayList;
import java.util.List;
/**
 * class Rectangle has the files upperLeft, width, height. we will use this
 * class in order to create block.
 *
 * @author YuvalSaadati
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the left upper point of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param x      position on the 'x' axes
     * @param y      position on the 'y' axes
     * @param width  of the rectangle
     * @param height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * setting the values of the rectangle.
     *
     * @param x      position on the 'x' axes
     * @param y      position on the 'y' axes
     * @param w of the rectangle
     * @param h of the rectangle
     */
    public void setRectangle(double x, double y, double w, double h) {
        this.upperLeft = new Point(x, y);
        this.width = w;
        this.height = h;
    }

    /**
     * creating 4 lines that are the walls of the rectangle and checking the
     * intersection of each wall with the given line by using the
     * 'intersectionWith' method at line class that return the point
     * intersection or null. after getting the point' we will check if it on the
     * line and also on the wall and check that it is not null point. if all
     * this 3 condition happened so the intersection point will be added to the
     * list.
     *
     * @return a (possibly empty) List of intersection points with the specified
     *         line.
     * @param line trajectory
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsList = new ArrayList<Point>();

        // points of the rectangle
        Point leftUp = getUpperLeft();
        Point rightUp = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY());
        Point leftDown = new Point(getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());
        Point rightDown = new Point(leftDown.getX() + getWidth(),
                getUpperLeft().getY() + getHeight());

        // up wall of the rectangle -
        Line upLine = new Line(leftUp, rightUp);
        Point intersectionUp = upLine.intersectionWith(line);
        if (intersectionUp != null && line.onLine(intersectionUp)
                && upLine.onLine(intersectionUp)) {
            intersectionPointsList.add(intersectionUp);
        }

        // down wall of the rectangle _
        Line downLine = new Line(leftDown, rightDown);
        Point intersectionDown = downLine.intersectionWith(line);
        if (intersectionDown != null && line.onLine(intersectionDown)
                && downLine.onLine(intersectionDown)) {
            intersectionPointsList.add(intersectionDown);

        }

        // right wall of the rectangle _|
        Line rightLine = new Line(rightUp, rightDown);
        Point intersectionRight = rightLine.intersectionWith(line);
        if (intersectionRight != null && line.onLine(intersectionRight)
                && rightLine.onLine(intersectionRight)) {

            intersectionPointsList.add(intersectionRight);

        }

        // left wall of the rectangle |_
        Line leftLine = new Line(leftUp, leftDown);
        Point intersectionLeft = leftLine.intersectionWith(line);
        if (intersectionLeft != null && line.onLine(intersectionLeft)
                && leftLine.onLine(intersectionLeft)) {

            intersectionPointsList.add(intersectionLeft);
        }
        return intersectionPointsList;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
