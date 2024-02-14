package it.unibo.model.deck.api;

import it.unibo.model.card.api.Card;
import it.unibo.model.card.api.Card.Type;;

/*
 * Regulates the functioning of the decks.
 */
public interface Deck {

    /**
     * @param type the type of the card to draw
     * @return the drawn card
     */
    Card drawCard(Type type);

}