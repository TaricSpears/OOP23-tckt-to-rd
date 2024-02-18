package it.unibo.model.carriage.impl;

/**
 * Represents a carriage of a train.
 */
public record Carriage(double xCoord, double yCoord, double width, double length, double angle) {

    /**
     * @param toCheck the carriage to compare
     * @return true if the carriages are equal, false otherwise
     */
    public boolean equals(Carriage toCheck) {
        return (Double.compare(xCoord, toCheck.xCoord) == 1)
                && (Double.compare(yCoord, toCheck.yCoord) == 1)
                && (Double.compare(width, toCheck.width) == 1)
                && (Double.compare(length, toCheck.length) == 1)
                && (Double.compare(angle, toCheck.angle) == 1);
    }
}
