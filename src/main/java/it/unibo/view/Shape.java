package it.unibo.view;

import javafx.scene.shape.Polygon;

//this class is used to create the shape of the routes
//the shape is a polygon that can be filled with the color of the player, and tilted to the right direction

public class Shape extends Polygon {

    public Shape(double x, double y, double width, double height) {
        super(x - (width / 2), y - (height / 2), x + (width / 2), y - (height / 2), x + (width / 2), y + (height / 2),
                x - (width / 2), y + (height / 2));
    }

    public void setFill(String color) {
        super.setFill(javafx.scene.paint.Color.web(color));
    }

    public void setTilt(double angle) {
        super.setRotate(angle);
    }
}