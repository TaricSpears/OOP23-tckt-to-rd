package it.unibo.controller.drawcontroller.api;

import it.unibo.model.card.api.Card;

/**
 * Interface to control card drawing.
 */
public interface DrawController {

    /*
     * @return a train card from the deck.
     */
    Card drawTrainCard();

    /*
     * @return an Objective card from the deck.
     */
    Card drawObjectiveCard();
}
