package it.unibo.view.entitysetter.impl;

import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.view.entitysetter.api.EntitySetter;

/**
 * Abstract implementation of {@link EntitySetter}.
 * 
 * @param <T> the type of entity to be returned
 */
public abstract class AbstractEntitySetter<T> implements EntitySetter<T> {
    private final MainController controller;

    /**
     * Constructor of the class.
     * 
     * @param controller the controller to be set
     */
    public AbstractEntitySetter(final MainController controller) {
        this.controller = controller;
    }

    /**
     * @inheritDoc
     */
    @Override
    public abstract Set<T> getEntities(double width, double height);

    /**
     * @inheritDoc
     */
    @Override
    public MainController getController(){
        return this.controller;
    }
}
