package it.unibo.commons;

import it.unibo.model.city.api.City;

public record EdgeData(City city1, City city2, Integer weight) {

}
