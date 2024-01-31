package it.unibo.model.card.impl;

import it.unibo.model.card.api.Card;

public class CardImpl implements Card {

    final CardType type;

    public CardImpl(final CardType type) {
        this.type = type;
    }

}
