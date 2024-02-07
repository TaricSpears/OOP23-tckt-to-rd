package it.unibo.model.deck.api;

import it.unibo.model.card.api.Card;
import it.unibo.model.card.impl.CardType;

public interface Deck {

    /**
     * @param type the type of the card to draw
     * @return the drawn card
     */
    Card drawCard(CardType type);

}
