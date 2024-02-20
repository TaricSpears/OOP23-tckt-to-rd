package it.unibo.model.card.api;

/**
 * This interface represents a card in the game.
 */
public interface Card {

    /**
     * Enumerates the types of cards.
     */
    enum Type {
        /**
         * Represents an objective card.
         */
        OBJECTIVE,

        /**
         * Represents a train card.
         */
        TRAIN

    }

    /**
     * @return the type of the card
     */
    Type getType();
}
