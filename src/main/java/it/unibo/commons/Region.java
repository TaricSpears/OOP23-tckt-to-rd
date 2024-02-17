package it.unibo.commons;

public class Region {
    private final double xCenter;
    private final double yCenter;
    private final double width;
    private final double length;
    private final double angle;
    private final int id;
    private final java.awt.Color color;

    public Region (final double xCenter, final double yCenter, final double width,
        final double length, final double angle, final int id, final java.awt.Color color){
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.length = length;
        this.angle = angle;
        this.id = id;
        this.color = color;
    }

    public double getXCenter(){
        return this.xCenter;
    }
    
    public double getYCenter(){
        return this.yCenter;
    }

    public double getWidth(){
        return this.width;
    }

    public double getLength(){
        return this.length;
    }

    public double getAngle(){
        return this.angle;
    }

    public int getId(){
        return this.id;
    }

    public java.awt.Color getColor(){
        return this.color;
    }

}
