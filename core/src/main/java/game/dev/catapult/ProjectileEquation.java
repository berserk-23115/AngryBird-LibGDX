package game.dev.catapult;

import com.badlogic.gdx.math.Vector2;

public class ProjectileEquation {
    public Vector2 startVelocity; // Initial velocity of the projectile
    public float gravity;         // Gravity affecting the projectile
    public Vector2 startPosition; // Starting position of the projectile

    // Constructor
    public ProjectileEquation() {
        this.startVelocity = new Vector2(0, 0);
        this.startPosition = new Vector2(0, 0);
        this.gravity = -9.8f; // Default gravity
    }

    /**
     * Sets the start position of the projectile.
     * @param x The x-coordinate of the start position.
     * @param y The y-coordinate of the start position.
     */
    public void setStartPosition(float x, float y) {
        this.startPosition.set(x, y);
    }

    /**
     * Calculates the x-coordinate of the projectile at time t.
     * @param t Time in seconds.
     * @return x-coordinate at time t.
     */
    public float getX(float t) {
        return startPosition.x + startVelocity.x * t;
    }

    /**
     * Calculates the y-coordinate of the projectile at time t.
     * @param t Time in seconds.
     * @return y-coordinate at time t.
     */
    public float getY(float t) {
        return startPosition.y + startVelocity.y * t + 0.5f * gravity * t * t;
    }
}
