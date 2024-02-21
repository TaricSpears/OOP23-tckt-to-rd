package it.unibo.view.entitysetter.api;

import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;

/**
 * This interface models a generic entity setter for a GUI.
 * 
 * @param <T> the type of entity to be returned.
 */
public interface EntitySetter<T> {
    /**
     * @param width  the width of the container where entities will be placed
     * @param height the heigth of the container where entities will be placed
     * @return returns the set of initialized entities
     */
    Set<T> getEntities(double width, double height);

    /**
     * @return the controller
     */
    MainController getController();
}
