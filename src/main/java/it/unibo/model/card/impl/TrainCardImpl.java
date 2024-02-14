package it.unibo.model.card.impl;

import java.awt.Color;

import it.unibo.model.card.api.TrainCard;

public class TrainCardImpl implements TrainCard {

    private final Color color;

    public TrainCardImpl(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

}
