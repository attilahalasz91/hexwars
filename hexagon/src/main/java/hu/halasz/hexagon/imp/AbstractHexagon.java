package hu.halasz.hexagon.imp;

import hu.halasz.hexagon.Hexagon;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hexagon in cubic coordinates(x,y,z).
 * @author Attila
 */
public class AbstractHexagon implements Hexagon{

    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final int z;
    @Getter
    private List<AbstractHexagon> neighbors = new ArrayList<>();

    public AbstractHexagon(int xCoordinate, int yCoordinate, int zCoordinate) {

        if (xCoordinate + yCoordinate + zCoordinate != 0) {
            throw new IllegalArgumentException("x + y + z must be 0");
        }

        this.x = xCoordinate;
        this.y = yCoordinate;
        this.z = zCoordinate;
    }

    public AbstractHexagon(int xCoordinate, int yCoordinate) {
        this(xCoordinate, yCoordinate, -xCoordinate-yCoordinate);
    }
    
    public Hexagon add(Hexagon other){
        AbstractHexagon hexagonToAdd = (AbstractHexagon) other;
        return new AbstractHexagon(
                this.x + hexagonToAdd.x,
                this.y + hexagonToAdd.y,
                this.z + hexagonToAdd.z);
    }
    
    public Hexagon subtract(Hexagon other){
        AbstractHexagon hexagonToSubtract = (AbstractHexagon) other;
        return new AbstractHexagon(
                this.x - hexagonToSubtract.x,
                this.y - hexagonToSubtract.y,
                this.z - hexagonToSubtract.z);
    }
    
    public Hexagon multiply(int multiplyBy){
        return new AbstractHexagon(
                this.x * multiplyBy,
                this.y * multiplyBy,
                this.z * multiplyBy);
    }
    
    public int length(){
        return (Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z)) / 2;
    }
    
    public int distance(Hexagon other){
        return subtract(other).length();
    }
    
    private static List<AbstractHexagon> directions = new ArrayList<AbstractHexagon>(){
        {
            add(new AbstractHexagon(1, 0, -1));
            add(new AbstractHexagon(1, -1, 0));
            add(new AbstractHexagon(0, -1, 1));
            add(new AbstractHexagon(-1, 0, 1));
            add(new AbstractHexagon(-1, 1, 0));
            add(new AbstractHexagon(0, 1, -1));
        }
    };    

    public static AbstractHexagon direction(int direction){
        return AbstractHexagon.directions.get(direction);
    }

    public Hexagon neighbor(int direction){
        return add(AbstractHexagon.direction(direction));
    }

    @Override
    public boolean equals(Object otherHexagon) {
        if (this == otherHexagon) {
            return true;
        }
        if (otherHexagon == null) {
            return false;
        }
        if (getClass() != otherHexagon.getClass()) {
            return false;
        }
        final AbstractHexagon other = (AbstractHexagon) otherHexagon;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + this.z;
        return hash;
    }

    @Override
    public String toString() {
        return "AbstractHexagon{" +
                "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    public void addNeighbor(Hexagon neighbor) {
        this.neighbors.add(((AbstractHexagon) neighbor));
    }

}
