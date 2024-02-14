package it.unibo.model.deck.api;

import it.unibo.model.card.api.toBeRemovedCard;
import it.unibo.model.card.impl.toBeRemovedCardType;

public interface Deck {

    /**
     * @param type the type of the card to draw
     * @return the drawn card
     */
    toBeRemovedCard drawCard(toBeRemovedCardType type);

}
