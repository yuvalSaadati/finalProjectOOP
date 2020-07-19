import java.util.List;
/**
 * class Line connects two points - a start point and an end point. Lines have
 * lengths, and may intersect with other lines. It can also tell if it is the
 * same as another line segment.
 * @author YuvalSaadati
 */
public class Line {
    private double epsilon = 0.1;
    private Point s;
    private Point e;

    /**
     * constructor to initialize the class fields by 2 points.
     *
     * @param start point of the line
     * @param end   point of the line
     */
    public Line(Point start, Point end) {
        this.s = start;
        this.e = end;
    }

    /**
     * constructor to initialize the class fields by 2 values of x, y.
     *
     * @param x1 is the x axis value of the start point
     * @param y1 is the y axis value of the start point
     * @param x2 is the x axis value of the end point
     * @param y2 is the y axis value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.s = new Point(x1, y1);
        this.e = new Point(x2, y2);
    }

    /**
     * set the values of the line.
     *
     * @param start the start of the line
     * @param end   the end of the line
     */
    public void setLine(Point start, Point end) {
        this.s = start;
        this.e = end;
    }

    /**
     * calculate the distance between two points (x1,y1) and (x2,y2), by the
     * square root of: ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)).
     *
     * @return rootSum the length of the line
     */
    public double length() {
        double sum = ((this.s.getX() - this.e.getX())
                * (this.s.getX() - this.e.getX())
                + (this.s.getY() - this.e.getY())
                        * (this.s.getY() - this.e.getY()));
        double rootSum = Math.sqrt(sum);
        return rootSum;
    }

    /**
     * The method returns the starting point of the line.
     *
     * @return this.s the start point of the line
     */
    public Point start() {
        return this.s;
    }

    /**
     * The method returns the end point of the line.
     *
     * @return this.e the end point of the line
     */
    public Point end() {
        return this.e;
    }

    /**
     *
     * Calculate according to a formula a slope of a straight line.
     *
     * @return m the incline of a line
     */
    public double incline() {
        double yRemainder = this.e.getY() - this.s.getY();
        double xRemainder = this.e.getX() - this.s.getX();
        double m = yRemainder / xRemainder;
        return m;
    }

    /**
     * First, finding the x point is common to two straight equations. Second,
     * placing the point in each of the equations and checking whether y that
     * obtained is the same. if the difference between yOther to yThis is zero
     * or is very close to zero then the two lines are intersecting and
     * intersection point is returned and null otherwise.
     *
     * @param other the other line
     *
     * @return p the point that is the intersection with the other line
     */
    public Point intersectionWith(Line other) {

        if (other.e.equals(start())) {
            return start();
        }
        if (other.e.equals(end())) {
            return end();
        }
        if (other.s.equals(start())) {
            return start();
        }
        if (other.s.equals(end())) {
            return end();
        }

        double x1 = start().getX();
        double x2 = other.s.getX();
        double y1 = start().getY();
        double y2 = other.s.getY();
        // the incline of the line that we get
        double mOther = other.incline();
        // the incline of this line
        double mThis = this.incline();

        // two vertical lines
        if (Math.abs(mThis) <= 0.1
                && (Math.abs(other.s.getX() - other.e.getX()) <= 0.1)) {
            Point p = new Point(other.s.getX(), start().getY());
            if (onLine(p) && other.onLine(p)) {
                return p;

            }
        }

        // two vertical lines
        if (Math.abs(mOther) <= 0.1
                && Math.abs(start().getX() - end().getX()) <= 0.1) {

            Point p = new Point(start().getX(), other.s.getY());
            if (onLine(p) && other.onLine(p)) {
                return p;
            }
        }

        // Create an equation of two straight.

        // the remainder of this line and the line that we got
        double mRemainder = mThis - mOther;

        // doubling the remainder of this line and the X value of the start line
        double mXThis1 = mThis * x1;
        double mXOther2 = mOther * x2;

        // using equation of straight
        double x = (y2 - y1) + (mXThis1 - mXOther2);
        x = (x / mRemainder);

        // finding the y vaue of the line by equation of straight
        double yOther = (mOther * x) + y2 - mXOther2;
        double yThis = (mThis * x) + y1 - mXThis1;
        // remainder between the 'y' value of 2 lines
        double yRemainder = yOther - yThis;

        // Handling the case of a straight line with a gradient and a vertical
        // line to it
        if (Math.abs(other.s.getX() - other.e.getX()) <= 0.1 && mThis != 0) {
            double yThis2 = (mThis * other.s.getX()) - mXThis1 + y1;
            double maxX = Math.max(start().getX(), end().getX());
            double minX = Math.min(start().getX(), end().getX());
            if ((other.s.getX() <= maxX + 0.1)
                    && (0.1 + other.s.getX() >= minX)) {
                Point p = new Point(other.s.getX(), yThis2);

                return p;
            }
        }

        // Handling the case of a straight line with a gradient and a vertical
        // line to it
        if (Math.abs(start().getX() - end().getX()) <= 0.1 && mOther != 0) {
            double yOther2 = (mOther * start().getX()) - mXOther2 + y2;
            double maxX = Math.max(other.s.getX(), other.e.getX());
            double minX = Math.min(other.s.getX(), other.e.getX());
            if ((start().getX() <= maxX + 0.1)
                    && (0.1 + start().getX() >= minX)) {
                Point p = new Point(start().getX(), yOther2);
                return p;
            }
        }

        // The point of intersection between two straight
        Point p = new Point(x, yOther);
        if (yRemainder == 0 && onLine(p) && other.onLine(p)) {
            return p;
        }
        if ((0 < yRemainder) && (yRemainder <= 0.1) && onLine(p)
                && other.onLine(p)) {
            return p;
        }
        if ((0 > yRemainder) && (yRemainder >= -0.1) && onLine(p)
                && other.onLine(p)) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * check if on line.
     *
     * @param p is a point that will be checked whether she is on the line
     * @return true if the point in range and false otherwise
     */
    public boolean onLine(Point p) {
        double maxX = Math.max(this.s.getX(), this.e.getX()) + epsilon;
        double minX = Math.min(this.s.getX(), this.e.getX()) - epsilon;
        double maxY = Math.max(this.s.getY(), this.e.getY()) + epsilon;
        double minY = Math.min(this.s.getY(), this.e.getY()) - epsilon;

        return ((p.getX() <= maxX) && (p.getX() >= minX) && (p.getY() <= maxY)
                && (p.getY() >= minY));

    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect the rectangle that we will find the closet intersection point
     *             so the start Of the trajectory line
     * @return the closet intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int saveIndexPoint = 0;
        Line l1 = new Line(start(), end());
        // getting the intersection Points List between the rectangle and this
        // line
        List<Point> intersectionPointsList = rect.intersectionPoints(l1);
        if (!intersectionPointsList.isEmpty()) {
            // the lne between the start of the point and one of the
            // intersection point in the list
            Line distanceLine = new Line(start(),
                    intersectionPointsList.get(0));
            // distance of the line
            double minDistance = distanceLine.length();

            /*
             * in this loop we will find the shorter distance between all the
             * intersection points in the list
             */
            for (int i = 0; i < intersectionPointsList.size(); i++) {
                distanceLine.setLine(start(), intersectionPointsList.get(i));
                double distance = distanceLine.length();
                if (distance <= minDistance) {
                    minDistance = distance;
                    saveIndexPoint = i;
                }
            }

            return intersectionPointsList.get(saveIndexPoint);
        } else {
            // if the intersection points list is empty so null will be returned
        return null;
        }
    }
}
