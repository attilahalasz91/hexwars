package hu.halasz.bmpmap;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pixel {

    @Getter
    int x;
    @Getter
    int y;
    @Getter
    List<Pixel> neighborPixels = new ArrayList<>();
    @Getter
    int rgbId;

    public Pixel(int x, int y, int rgbId){
        this.x = x;
        this.y = y;
        this.rgbId = rgbId;
    }

    public void addNeighbourPixel(Pixel pixel){
        neighborPixels.add(pixel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x &&
                y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
