package hu.halasz.hexagon;


import hu.halasz.hexagon.imp.AbstractHexagon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Attila
 */
public class FractionalHexagon {
    
    public final double x;
    public final double y;
    public final double z;
    
    public FractionalHexagon(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        if (Math.round(x + y + z) != 0) throw new IllegalArgumentException("x + y + z must be 0");
    }    

    public Hexagon hexagonRound()
    {
        int xToInt = (int)(Math.round(x));
        int yToInt = (int)(Math.round(y));
        int zToInt = (int)(Math.round(z));
        double x_diff = Math.abs(xToInt - x);
        double y_diff = Math.abs(yToInt - y);
        double z_diff = Math.abs(zToInt - z);
        if (x_diff > y_diff && x_diff > z_diff){
            xToInt = -yToInt - zToInt;
        }else
            if (y_diff > z_diff){
                yToInt = -xToInt - zToInt;
            }
            else{
                zToInt = -xToInt - yToInt;
            }
        return new AbstractHexagon(xToInt, yToInt, zToInt);
    }

   /* public FractionalHexagon hexLerp(FractionalHexagon b, double t)
    {
        return new FractionalHexagon(x * (1 - t) + b.x * t, y * (1 - t) + b.y * t, z * (1 - t) + b.z * t);
    }

    static public List<Hexagon> hexLinedraw(Hexagon a, Hexagon b)
    {
        int N = a.distance(b);
        FractionalHexagon a_nudge = new FractionalHexagon(a.getX() + 0.000001, a.getY() + 0.000001, a.getZ() - 0.000002);
        FractionalHexagon b_nudge = new FractionalHexagon(b.getX() + 0.000001, b.getY() + 0.000001, b.getZ() - 0.000002);
        ArrayList<Hexagon> results = new ArrayList<Hexagon>(){{}};
        double step = 1.0 / Math.max(N, 1);
        for (int i = 0; i <= N; i++)
        {
            results.add(a_nudge.hexLerp(b_nudge, step * i).hexagonRound());
        }
        return results;
    }*/
}
