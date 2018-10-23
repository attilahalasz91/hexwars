package hu.halasz.hexagon;

import hu.halasz.hexagon.imp.AbstractHexagon;

public interface Hexagon {

    Hexagon add(Hexagon other);

    Hexagon subtract(Hexagon other);

    Hexagon multiply(int multiplyBy);

    int length();

    int distance(Hexagon other);

    Hexagon neighbor(int direction);

    void addNeighbor(Hexagon neighbor);

}
