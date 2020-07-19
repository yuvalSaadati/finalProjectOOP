
/**
 * class CollisionInfo has the information of the collision point and collision
 * Object.
 *
 * @author YuvalSaadati
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor to initialize the class fields by collision point and
     * collision Object.
     *
     * @param collisionP the collision point.
     * @param collisionC the collidable object.
     * @return
     */
    public CollisionInfo(Point collisionP, Collidable collisionC) {
        this.collisionPoint = collisionP;
        this.collisionObject = collisionC;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
