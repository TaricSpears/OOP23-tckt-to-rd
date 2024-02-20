package it.unibo.commons;

import java.util.Optional;

/**
 * This class represents a region in the map.
 *
 * 
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

    /**
     * Constructor for the class Region.
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

    /**
     * @return the x coordinate of the center of the region.
     */

    public double getXCenter() {
        return this.xCenter;
    }

    /**
     * @return the y coordinate of the center of the region.
     */
    public double getYCenter() {
        return this.yCenter;
    }

    /**
     * @return the width of the region.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the length of the region.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * @return the angle of the region.
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * @return the id of the region.
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return the default color of the region.
     */
    public java.awt.Color getDefaultColor() {
        return this.defaultColor;
    }

    /**
     * @return the color of the player that has the region, if any.
     */
    public Optional<java.awt.Color> getPlayerColor() {
        return this.playerColor;
    }

}
