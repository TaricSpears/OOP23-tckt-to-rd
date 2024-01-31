package it.unibo.model.card.impl;

public enum CardType {

    TRAIN("train"),
    OBJECTIVE("objective");

    private final String name;

    private CardType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
