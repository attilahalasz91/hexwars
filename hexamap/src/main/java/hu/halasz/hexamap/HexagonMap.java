package hu.halasz.hexamap;

import hu.halasz.hexagon.Hexagon;
import hu.halasz.hexagon.Point2D;
import hu.halasz.hexagon.imp.AbstractHexagon;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Attila on 2018/03/15.
 */

public class HexagonMap {

    private Map<Hexagon, Hexagon> map;

    @Getter
    private Layout layout;
    private int createdHexagonGridRadius;

    public int getCreatedHexagonGridRadius() {
        return createdHexagonGridRadius;
    }

    public Map<Hexagon, Hexagon> getMap() {
        return map;
    }

    public HexagonMap() {
        map = new HashMap<>();
        layout = new Layout(Layout.POINTY_LAYOUT, new Point2D(100, 50), new Point2D(20, 20));
    }

    public void createEmptyMapGridInHexagonForm(int hexagonMapRadius) {
        createdHexagonGridRadius = hexagonMapRadius;
        for (int i = -hexagonMapRadius; i <= hexagonMapRadius; i++) {
            int j1 = Math.max(-hexagonMapRadius, -i - hexagonMapRadius);
            int j2 = Math.min(hexagonMapRadius, -i + hexagonMapRadius);
            for (int j = j1; j <= j2; j++) {
                if ((i != 1 || j != 0) && (i != 0 || j != -1) && (i != -1 || j != 1)) {
                    Hexagon hexagon = new AbstractHexagon(i, j, -i - j);

                    map.put(hexagon, hexagon);
                }
            }
        }
        setNeighborsOfMapHexagons();
    }

    public void createEmptyMapGridInREctangularForm(int gridX, int gridY) {
        for (int i = 0; i < gridY; i++) {
            int rOffset = (int) Math.floor(i / 2);
            for (int j = -rOffset; j < gridX - rOffset; j++) {
                Hexagon hexagon = new AbstractHexagon(j, i, -j - i);
                //hexagon.setTerrainType(TerrainType.FOREST);
                map.put(hexagon, hexagon);
            }
        }
        setNeighborsOfMapHexagons();
    }

    private void setNeighborsOfMapHexagons() {
        for (Hexagon hexagon : map.values()) {
            for (int j = 0; j < 6; j++) {
                Hexagon neighbor = hexagon.neighbor(j);
                if (map.containsKey(neighbor)) hexagon.addNeighbor(map.get(neighbor));
            }
        }
    }
}
