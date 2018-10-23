package hu.halasz.hexamap;

import hu.halasz.hexagon.FractionalHexagon;
import hu.halasz.hexagon.Hexagon;
import hu.halasz.hexagon.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Attila
 */
public class Layout {

    public static final Orientation POINTY_LAYOUT = new Orientation(
            (float) (Math.sqrt(3.0)),
            (float) (Math.sqrt(3.0)) / 2.0f,
            0.0f,
            3.0f / 2.0f,
            (float) (Math.sqrt(3.0)) / 3.0f,
            -1.0f / 3.0f,
            0.0f,
            2.0f / 3.0f,
            0.5f);

    public static final Orientation FLAT_LAYOUT = new Orientation(
            3.0f / 2.0f,
            0.0f,
            (float) (Math.sqrt(3.0)) / 2.0f,
            (float) (Math.sqrt(3.0)),
            2.0f / 3.0f,
            0.0f,
            -1.0f / 3.0f,
            (float) (Math.sqrt(3.0)) / 3.0f,
            0.0f);

    private final Orientation orientation;
    private final Point2D origin;
    private final Point2D size;

    public Layout(Orientation orientation, Point2D origin, Point2D size) {
        this.orientation = orientation;
        this.origin = origin;
        this.size = size; //for streth to match pixel art
    }

    public Point2D hexToPixel(Hexagon hexagon) {
        Orientation orient = orientation;
        float x = (orient.getForwardMatrixElement0() * hexagon.getX() + orient.getForwardMatrixElement1() * hexagon.getY()) * size.x;
        float y = (orient.getForwardMatrixElement2() * hexagon.getX() + orient.getForwardMatrixElement3() * hexagon.getY()) * size.y;
        return new Point2D(x + origin.x, y + origin.y);
    }

    public FractionalHexagon pixelToHex(Point2D p) {
        Orientation M = orientation;
        Point2D pt = new Point2D((p.x - origin.x) / size.x, (p.y - origin.y) / size.y);
        double q = M.getInverseMatrixElement0() * pt.x + M.getInverseMatrixElement1() * pt.y;
        double r = M.getInverseMatrixElement2() * pt.x + M.getInverseMatrixElement3() * pt.y;
        return new FractionalHexagon(q, r, -q - r);
    }


    public Point2D hexCornerOffset(int corner) {
        Orientation M = orientation;
        float angle = (float) (2.0 * Math.PI * (M.getStartAngleWhichMultipliesBy60degress() - corner) / 6);
        return new Point2D(size.x * (float) Math.cos(angle), size.y * (float) Math.sin(angle));
    }


    public List<Point2D> polygonCorners(Hexagon h) {
        List<Point2D> corners = new ArrayList<>();
        Point2D center = hexToPixel(h);
        for (int i = 0; i < 6; i++) {
            Point2D offset = hexCornerOffset(i);
            corners.add(new Point2D(center.x + offset.x, center.y + offset.y));
        }
        return corners;
    }

}
