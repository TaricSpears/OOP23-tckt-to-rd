package it.unibo.view.entitysetter.impl;

import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import it.unibo.view.entitysetter.api.EntitySetter;

/**
 * Abstract implementation of {@link EntitySetter}
 */
public abstract class AbstractEntitySetter<T> implements EntitySetter<T> {
    protected final MainController controller;

    /**
     * Constructor of the class
     */
    public AbstractEntitySetter() {
        this.controller = GameStart.CONTROLLER;
    }

    /**
     * @inheritDoc
     */
    @Override
    public abstract Set<T> getEntities(final double width, final double height);
}
