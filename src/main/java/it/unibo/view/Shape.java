package it.unibo.view;

import javafx.scene.shape.Polygon;

//this class is used to create the shape of the routes
//the shape is a polygon that can be filled with the color of the player, and tilted to the right direction

public class Shape extends Polygon {

    public Shape(double x, double y, double width, double height) {
        super(x, y, x + width, y, x + width, y + height, x, y + height);
    }

    public void setFill(String color) {
        super.setFill(javafx.scene.paint.Color.web(color));
    }

    public void setTilt(double angle) {
        super.setRotate(angle);
    }
}