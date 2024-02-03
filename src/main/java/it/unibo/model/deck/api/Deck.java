package it.unibo.model.deck.api;

import it.unibo.model.card.api.Card;
import it.unibo.model.card.impl.CardType;

public interface Deck {

    Card drawCard(CardType type);

}
