package it.unibo.model.card.impl;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.commons.Pair;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.city.api.City;
import it.unibo.model.objectivegeneration.api.ObjectiveGenerator;
import it.unibo.model.objectivegeneration.impl.ObjectiveGeneratorImpl;
import it.unibo.model.route.api.Route;

/*
 * Implementation of {@link ObjectiveCard}.
 * Represents an objective card in the game.
 */
public class ObjectiveCardImpl implements ObjectiveCard {

    private final Pair<City, City> objective;
    private final double scoreValue;
    private boolean completed;

    /*
     * Constructor for the objective card.
     * 
     * @param objective the pair of cities that the player has to connect.
     * 
     * @param scoreValue the score value of the objective card.
     */
    public ObjectiveCardImpl(final WeightedPseudograph<City, Route> graph) {
        final ObjectiveGenerator objectiveGenerator = new ObjectiveGeneratorImpl(graph);

        this.objective = objectiveGenerator.generateObjective();
        this.scoreValue = objectiveGenerator.calculateScore(this.objective);
        this.completed = false;
    }

    /*
     * @return the score value of the objective card;
     */
    @Override
    public double getScore() {
        return this.scoreValue;
    }

    /*
     * @return the type of the card
     */
    @Override
    public Type getType() {
        return Type.OBJECTIVE;
    }

    /**
     * @return the pair of the cities to be connceted as objective
     */
    @Override
    public Pair<City, City> getCities() {
        return this.objective;
    }

    @Override
    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
