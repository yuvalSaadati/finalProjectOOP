import java.util.List;
import java.util.ArrayList;
/**
 * class GameEnvironment. During the game, there are going to be many objects a
 * Ball can collide with. The GameEnvironment class will be a collection of such
 * things. The ball will know the game environment, and will use it to check for
 * collisions and direct its movement.
 *
 * @author YuvalSaadati
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * the constructor initialize the collidables list.
     *
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable that will be added to the collidables list.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * delete the given collidable from the environment.
     *
     * @param c collidable that will be delete from the collidables list.
     */
    public void deleteCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     *
     * in order to find the closet interaction point we will pass on the
     * collidables list and find the interaction point. before that we will
     * check if there is any such point. we will use the method
     * closestIntersectionToStartOfLine the exists in line class to find it.
     *
     * @param trajectory the line between the ball and the object.
     * @return CollisionInfo if exists otherwise null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> copyListCollidables = new ArrayList<Collidable>(this.collidables);
        Rectangle r;
        double minDis = -1;
        // the first Collidable Collidables list
        Collidable saveCollidableObject = copyListCollidables.get(0);
        // get the rectangle from the collidables list
        Rectangle closerRectangle = saveCollidableObject
                .getCollisionRectangle();
        // the closet intersection point between the start of trajectory and
        // each rectangle, we can get null point
        Point colsetP = trajectory
                .closestIntersectionToStartOfLine(closerRectangle);

        Point saveCollisionPoint = colsetP;
        // finding the first distance - making sure that there is at least one
        // collision point that is not null.
        for (Collidable c : copyListCollidables) {
            // getting the rectangle
            r = c.getCollisionRectangle();
            // the closet intersection point between the start of trajectory and
            // each rectangle, we can get null point colsetP
            // trajectory.closestIntersectionToStartOfLine(r);
            colsetP = trajectory.closestIntersectionToStartOfLine(r);
            if (colsetP != null) {
                minDis = colsetP.distance(trajectory.start());
            }
        }

        if (minDis != -1) {
            // if we here it means that at lest one collision point exists.
            for (Collidable c : copyListCollidables) {
                // getting the rectangle
                r = c.getCollisionRectangle();
                // the closet point to the closet collection object.
                colsetP = trajectory.closestIntersectionToStartOfLine(r);
                if (colsetP != null) {
                    double distance = colsetP.distance(trajectory.start());
                    // compare the distance between all the points and find the
                    // closet one
                    if (distance - 0.00001 <= minDis) {
                        minDis = distance;
                        // saving the closet object
                        saveCollidableObject = c;
                        // saving the closet intersiction point
                        saveCollisionPoint = new Point(colsetP.getX(),
                                colsetP.getY());
                    }
                }

            }

            CollisionInfo getCollisionInfo = new CollisionInfo(
                    saveCollisionPoint, saveCollidableObject);
            return getCollisionInfo;

        } else {
            // all the cllidibale list not collide with the start of the
            // trajectory line
            return null;
        }
    }
}
