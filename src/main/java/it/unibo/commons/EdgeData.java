package it.unibo.commons;

import it.unibo.model.city.api.City;

/**
 * This class represents the data of an edge in a graph.
 * 
 * @param city1  the first city of the edge.
 * @param city2  the second city of the edge.
 * @param weight the weight of the edge.
 */
public record EdgeData(City city1, City city2, Integer weight) {

}
