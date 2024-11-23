package game.dev.catapult;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEquation {

    public float gravity = -9.8f; // Default gravity value (downward force)
    public Vector2 startVelocity = new Vector2(); // Initial velocity vector
    public Vector2 startPoint = new Vector2();    // Starting position

    /**
     * Computes the X-coordinate of the projectile at time t.
     * @param t Time in seconds
     * @return X-coordinate at time t
     */
    public float getX(float t) {
        return startVelocity.x * t + startPoint.x;
    }

    /**
     * Computes the Y-coordinate of the projectile at time t.
     * @param t Time in seconds
     * @return Y-coordinate at time t
     */
    public float getY(float t) {
        return 0.5f * gravity * t * t + startVelocity.y * t + startPoint.y;
    }
}
