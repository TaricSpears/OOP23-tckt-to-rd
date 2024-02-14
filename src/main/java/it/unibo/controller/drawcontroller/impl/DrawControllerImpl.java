package it.unibo.controller.drawcontroller.impl;

import it.unibo.controller.drawcontroller.api.DrawController;

import it.unibo.model.card.api.Card;
import it.unibo.model.deck.impl.DeckImpl;

/**
 * Interface to control card drawing.
 */
public class DrawControllerImpl implements DrawController {

    /*
     * @return a Train card from the deck.
     */
    @Override
    public Card drawTrainCard() {
        return new DeckImpl().drawCard(Card.Type.TRAIN);
    }

    /*
     * @return an Objective card from the deck.
     */
    @Override
    public Card drawObjectiveCard() {
        return new DeckImpl().drawCard(Card.Type.OBJECTIVE);
    }

}
