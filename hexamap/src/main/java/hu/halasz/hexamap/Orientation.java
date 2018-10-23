package hu.halasz.hexamap;

/**
 *
 * @author Attila
 */
public class Orientation {
    
    private final float forwardMatrixElement0;
    private final float forwardMatrixElement1;
    private final float forwardMatrixElement2;
    private final float forwardMatrixElement3;
    
    private final float inverseMatrixElement0;
    private final float inverseMatrixElement1;
    private final float inverseMatrixElement2;
    private final float inverseMatrixElement3;
    
    private final float startAngleWhichMultipliesBy60degress;

    public Orientation(float forwardMatrixElement0, float forwardMatrixElement1, float forwardMatrixElement2,
                       float forwardMatrixElement3, float inverseMatrixElement0, float inverseMatrixElement1,
                       float inverseMatrixElement2, float inverseMatrixElement3, float startAngleWhichMultipliesBy60degress) {
        this.forwardMatrixElement0 = forwardMatrixElement0;
        this.forwardMatrixElement1 = forwardMatrixElement1;
        this.forwardMatrixElement2 = forwardMatrixElement2;
        this.forwardMatrixElement3 = forwardMatrixElement3;
        this.inverseMatrixElement0 = inverseMatrixElement0;
        this.inverseMatrixElement1 = inverseMatrixElement1;
        this.inverseMatrixElement2 = inverseMatrixElement2;
        this.inverseMatrixElement3 = inverseMatrixElement3;
        this.startAngleWhichMultipliesBy60degress = startAngleWhichMultipliesBy60degress;
    }

    public float getForwardMatrixElement0() {
        return forwardMatrixElement0;
    }

    public float getForwardMatrixElement1() {
        return forwardMatrixElement1;
    }

    public float getForwardMatrixElement2() {
        return forwardMatrixElement2;
    }

    public float getForwardMatrixElement3() {
        return forwardMatrixElement3;
    }

    public float getInverseMatrixElement0() {
        return inverseMatrixElement0;
    }

    public float getInverseMatrixElement1() {
        return inverseMatrixElement1;
    }

    public float getInverseMatrixElement2() {
        return inverseMatrixElement2;
    }

    public float getInverseMatrixElement3() {
        return inverseMatrixElement3;
    }

    public float getStartAngleWhichMultipliesBy60degress() {
        return startAngleWhichMultipliesBy60degress;
    }
    
    
}
