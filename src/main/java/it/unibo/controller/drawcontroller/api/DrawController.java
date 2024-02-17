package it.unibo.controller.drawcontroller.api;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;

/**
 * Interface to control card drawing.
 */
public interface DrawController {

    /**
     * @return a train card from the deck.
     */
    TrainCard drawTrainCard();

    /**
     * @return an Objective card from the deck.
     */
    ObjectiveCard drawObjectiveCard();
}
