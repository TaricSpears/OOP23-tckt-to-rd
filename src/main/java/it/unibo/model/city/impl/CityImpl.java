package it.unibo.model.city.impl;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

/**
 * Implementation of {@link City}.
 * 
 * Represents a city in the game.
 */
public class CityImpl implements City {
    private final double radius;
    private final String name;
    private final int id;
    private final Pair<Double, Double> coordinates;

    /**
     * Constructor of the city.
     * 
     * @param name   the name of the city.
     * @param id     the id of the city.
     * @param coords the coordinates of the city.
     * @param radius the radius of the city.
     */
    public CityImpl(final String name, final int id, final Pair<Double, Double> coords, final double radius) {
        this.name = name;
        this.id = id;
        this.coordinates = coords;
        this.radius = radius;
    }

    /**
     * Constructor of the city.
     * 
     * @param name the name of the city.
     */
    public CityImpl(final String name) {
        this(name, 0, new Pair<>(0.0, 0.0), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getRadius() {
        return this.radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final City toCheck) {
        return Double.compare(radius, toCheck.getRadius()) == 0
                && this.name.equals(toCheck.getName())
                && this.id == toCheck.getId()
                && Double.compare(this.coordinates.first(), toCheck.getCoordinates().first()) == 0
                && Double.compare(this.coordinates.second(),
                        toCheck.getCoordinates().second()) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ID = " + this.id + "\nNAME = " + this.name + "\nRADIUS = " + this.radius + "\nCOORD1 = "
                + this.coordinates.first() + "," + this.coordinates.second();
    }

}
