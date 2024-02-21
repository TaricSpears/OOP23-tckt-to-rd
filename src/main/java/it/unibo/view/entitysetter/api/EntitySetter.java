package it.unibo.view.entitysetter.api;

import java.util.Set;

/**
 * This interface models a generic entity setter for a GUI
 */
public interface EntitySetter<T> {
    /**
     * @param width  the width of the container where entities will be placed
     * @param height the heigth of the container where entities will be placed
     * @return returns the set of initialized entities
     */
    Set<T> getEntities(double width, double height);
}
