
/**
 * interface Collidable will be used by things that can be collided with.
 *
 * @author YuvalSaadati
 */
public interface Collidable {
    /**
     * return the "collision shape" of the object.
     * @return the rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the ball that hit
     * @param collisionPoint the collision point on the object
     * @param currentVelocity the velocity that will chamge in the method
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
