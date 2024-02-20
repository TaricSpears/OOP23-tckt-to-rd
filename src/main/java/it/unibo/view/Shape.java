package it.unibo.view;

import javafx.scene.shape.Polygon;

//this class is used to create the shape of the routes
//the shape is a polygon that can be filled with the color of the player, and tilted to the right direction

/**
 * This class is used to create the shape of the routes,
 * extends {@link Polygon}.
 * 
 * The shape is a polygon that can be filled with the color of the player, and
 * tilted to the right direction
 */
public class Shape extends Polygon {
    private final int id;
    private static final double SCALE = 0.5;

    /**
     * Constructor for the shape.
     * 
     * @param x      the x coordinate of the center of the shape.
     * @param y      the y coordinate of the center of the shape.
     * @param width  the width of the shape.
     * @param height the height of the shape.
     * @param id     the id of the shape.
     */
    public Shape(final double x, final double y, final double width, final double height, final int id) {
        super(x - (width * SCALE), y - (height * SCALE), x + (width * SCALE), y - (height * SCALE),
                x + (width * SCALE),
                y + (height * SCALE),
                x - (width * SCALE), y + (height * SCALE));
        this.id = id;
    }

    /**
     * Method to set the color of the shape.
     * 
     * @param color the color to set.
     */
    public void setFill(final String color) {
        super.setFill(javafx.scene.paint.Color.web(color));
    }

    /**
     * Method to tilt the shape.
     * 
     * @param angle the angle to tilt the shape.
     */
    public void setTilt(final double angle) {
        super.setRotate(angle);
    }

    /**
     * Method to get the id of the shape.
     * 
     * @return the id of the shape.
     */
    public int getShapeId() {
        return this.id;
    }
}
