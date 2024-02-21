package it.unibo.view.entitysetter.impl;

import java.util.Set;

import it.unibo.view.entitysetter.api.EntitySetter;

/**
 * Abstract implementation of {@link EntitySetter}.
 * 
 * @param <T> the type of entity to be returned
 */
public abstract class AbstractEntitySetter<T> implements EntitySetter<T> {
    /**
     * @inheritDoc
     */
    @Override
    public abstract Set<T> getEntities(double width, double height);
}
