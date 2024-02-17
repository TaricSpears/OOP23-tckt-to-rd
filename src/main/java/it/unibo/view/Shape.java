package it.unibo.view;

import javafx.scene.shape.Polygon;

//this class is used to create the shape of the routes
//the shape is a polygon that can be filled with the color of the player, and tilted to the right direction

public class Shape extends Polygon {
    private final int id;

    public Shape(final double x, final double y, final double width, final double height, final int id) {
        super(x - (width / 2), y - (height / 2), x + (width / 2), y - (height / 2), x + (width / 2), y + (height / 2),
                x - (width / 2), y + (height / 2));
        this.id = id;
    }

    public void setFill(String color) {
        super.setFill(javafx.scene.paint.Color.web(color));
    }

    public void setTilt(double angle) {
        super.setRotate(angle);
    }

    public int getShapeId(){
        return this.id;
    }
}