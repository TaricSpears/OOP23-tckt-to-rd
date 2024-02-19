package it.unibo.commons;

/**
 * This class represents a pair of objects.
 * 
 * @param <X> the type of the first object.
 * @param <Y> the type of the second object.
 */
public record Pair<X, Y>(X first, Y second) {
}
