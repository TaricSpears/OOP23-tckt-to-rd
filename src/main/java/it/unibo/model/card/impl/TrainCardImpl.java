package it.unibo.model.card.impl;

import java.awt.Color;

public class TrainCardImpl extends CardImpl {

    private final Color color;

    public TrainCardImpl(final Color color) {
        super(CardType.TRAIN);
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

}
