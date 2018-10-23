package hu.halasz.holding;

import lombok.Getter;

public enum HoldingType {

    ROAD(1),
    GRASSLAND(2),
    FOREST(3),
    SWAMP(4),
    HILL(4),
    MOUNTAIN(5),
    WATER(Integer.MAX_VALUE);

    @Getter
    private Integer movementCost;

    TerrainType(Integer movementCost){
        this.movementCost = movementCost;
    }
}
