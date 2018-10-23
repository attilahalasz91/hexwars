package hu.halasz.hexagon;

/**
 * The <code>Point2D</code> class defines a point representing a location
 * in {@code (x,y)} coordinate space.
 * <p>
 */
public class Point2D {

    /**
     * The X coordinate of this <code>Point2D</code>.
     */
    public float x;

    /**
     * The Y coordinate of this <code>Point2D</code>.
     */
    public float y;

    /**
     * Constructs and initializes a <code>Point2D</code> with
     * the specified coordinates.
     *
     * @param x the X coordinate of the newly
     *          constructed <code>Point2D</code>
     * @param y the Y coordinate of the newly
     *          constructed <code>Point2D</code>
     */
    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between two points.
     *
     * @param x1 the X coordinate of the first specified point
     * @param y1 the Y coordinate of the first specified point
     * @param x2 the X coordinate of the second specified point
     * @param y2 the Y coordinate of the second specified point
     * @return the distance between the two sets of specified
     * coordinates.
     */
    public static float distance(float x1, float y1, float x2, float y2) {
        x1 -= x2;
        y1 -= y2;
        return (float) Math.sqrt(x1 * x1 + y1 * y1);
    }
}
