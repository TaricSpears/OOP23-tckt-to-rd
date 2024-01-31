package it.unibo.model.card.impl;

import java.awt.Color;

public class TrainCardImpl extends CardImpl {

    private final Color color;

    public TrainCardImpl(final CardType type, final Color color) {
        super(type);
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

}
