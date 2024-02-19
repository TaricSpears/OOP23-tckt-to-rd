package it.unibo.commons;

import it.unibo.model.city.api.City;
/**
 * This class represents the data of an edge in a graph.
 */
public record EdgeData(City city1, City city2, Integer weight) {

}
