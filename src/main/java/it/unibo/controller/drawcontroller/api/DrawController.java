package it.unibo.controller.drawcontroller.api;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

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
    ObjectiveCard drawObjectiveCard(WeightedPseudograph<City, Route> graph);
}
