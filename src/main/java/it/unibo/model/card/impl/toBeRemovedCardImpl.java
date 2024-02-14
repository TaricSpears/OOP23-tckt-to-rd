package it.unibo.model.card.impl;

import it.unibo.model.card.api.toBeRemovedCard;

public class CardImpl implements toBeRemovedCard {

    final toBeRemovedCardType type;

    public CardImpl(final toBeRemovedCardType type) {
        this.type = type;
    }

}
