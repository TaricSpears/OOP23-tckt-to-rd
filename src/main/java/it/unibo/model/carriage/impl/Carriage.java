package it.unibo.model.carriage.impl;

/**
 * This class represents a carriage in the game.
 * 
 * @param xCoord the x coordinate of the carriage
 * @param yCoord the y coordinate of the carriage
 * @param width  the width of the carriage
 * @param length the length of the carriage
 * @param angle  the angle of the carriage
 */
public record Carriage(double xCoord, double yCoord, double width, double length, double angle) {

    /**
     * @param toCheck the carriage to compare
     * @return true if the carriages are equal, false otherwise
     */
    public boolean equals(final Carriage toCheck) {
        return Double.compare(xCoord, toCheck.xCoord) == 1
                && Double.compare(yCoord, toCheck.yCoord) == 1
                && Double.compare(width, toCheck.width) == 1
                && Double.compare(length, toCheck.length) == 1
                && Double.compare(angle, toCheck.angle) == 1;
    }
}
