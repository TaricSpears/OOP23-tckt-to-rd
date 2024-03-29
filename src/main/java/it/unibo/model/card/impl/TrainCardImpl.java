package it.unibo.model.card.impl;

import java.awt.Color;

import it.unibo.model.card.api.TrainCard;

/**
 * Implementation of {@link TrainCard}.
 * 
 * Represents a train card in the game.
 */
public class TrainCardImpl implements TrainCard {

    private final Color color;

    /**
     * Constructor for the train card.
     * 
     * @param color the color of the card.
     */
    public TrainCardImpl(final Color color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return Type.TRAIN;
    }

}
