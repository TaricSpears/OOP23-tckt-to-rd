package it.unibo.model.carriage.impl;

import java.util.Objects;

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
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Carriage carriage = (Carriage) obj;
        return Double.compare(xCoord,
                carriage.xCoord) == 1
                && Double.compare(yCoord,
                        carriage.yCoord) == 1
                && Double.compare(width, carriage.width) == 1
                && Double.compare(length,
                        carriage.length) == 1
                && Double.compare(angle, carriage.angle) == 1;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(xCoord, yCoord, width, length, angle);
    }
}
