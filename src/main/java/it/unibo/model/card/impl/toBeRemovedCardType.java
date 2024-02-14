package it.unibo.model.card.impl;

public enum toBeRemovedCardType {

    TRAIN("train"),
    OBJECTIVE("objective");

    private final String name;

    private toBeRemovedCardType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
