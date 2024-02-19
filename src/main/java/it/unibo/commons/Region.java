package it.unibo.commons;

import java.util.Optional;

/**
 * This class represents a region in the map.
 * 
 * @param xCenter      the x coordinate of the center of the region.
 * @param yCenter      the y coordinate of the center of the region.
 * @param width        the width of the region.
 * @param length       the length of the region.
 * @param angle        the angle of the region.
 * @param id           the id of the region.
 * @param defaultColor the default color of the region.
 * @param playerColor  the color of the player that has the region, if any.
 */
public class Region {
    private final double xCenter;
    private final double yCenter;
    private final double width;
    private final double length;
    private final double angle;
    private final int id;
    private final java.awt.Color defaultColor;
    private final Optional<java.awt.Color> playerColor;

    public Region(final double xCenter, final double yCenter, final double width,
            final double length, final double angle, final int id, final java.awt.Color defaultColor,
            final Optional<java.awt.Color> playerColor) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.length = length;
        this.angle = angle;
        this.id = id;
        this.defaultColor = defaultColor;
        this.playerColor = playerColor;
    }

    public double getXCenter() {
        return this.xCenter;
    }

    public double getYCenter() {
        return this.yCenter;
    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }

    public double getAngle() {
        return this.angle;
    }

    public int getId() {
        return this.id;
    }

    public java.awt.Color getDefaultColor() {
        return this.defaultColor;
    }

    public Optional<java.awt.Color> getPlayerColor() {
        return this.playerColor;
    }

}
