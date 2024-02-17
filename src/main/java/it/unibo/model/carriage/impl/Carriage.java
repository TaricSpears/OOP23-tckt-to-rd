package it.unibo.model.carriage.impl;

public record Carriage(double xCoord, double yCoord, double width, double length, double angle) {
    public boolean equals(Carriage toCheck){
        return (Double.compare(xCoord, toCheck.xCoord) == 1)
            && (Double.compare(yCoord, toCheck.yCoord) == 1)
            && (Double.compare(width, toCheck.width) == 1)
            && (Double.compare(length, toCheck.length) == 1)
            && (Double.compare(angle, toCheck.angle) == 1);
    }
}
