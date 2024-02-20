package it.unibo.model.deck.impl;

import java.awt.Color;
import java.util.Random;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.card.impl.ObjectiveCardImpl;
import it.unibo.model.card.impl.TrainCardImpl;
import it.unibo.model.city.api.City;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.route.api.Route;

/**
 * Implementation of {@link Deck}.
 * 
 * Regulates the functioning of the decks.
 */
public class DeckImpl implements Deck {

    private static final int JOLLY_PROBABILITY = 7;
    private static final int OTHER_PROBABILITY = 6;
    private static final int TOTAL_CARDS = 55;

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveCard drawObjectiveCard(final WeightedPseudograph<City, Route> graph) {
        return new ObjectiveCardImpl(graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainCard drawTrainCard() {

        final Color[] colors = { Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW,
                Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.BLUE,
                Color.DARK_GRAY };

        final int[] probabilities = { OTHER_PROBABILITY, OTHER_PROBABILITY, OTHER_PROBABILITY, OTHER_PROBABILITY,
                OTHER_PROBABILITY, OTHER_PROBABILITY, OTHER_PROBABILITY, OTHER_PROBABILITY, JOLLY_PROBABILITY };

        int temp = new Random().nextInt(TOTAL_CARDS);
        int index = 0;

        for (int i = 0; i < probabilities.length; i++) {
            temp -= probabilities[i];

            if (temp < 0) {
                index = i;
                break;
            }
        }

        return new TrainCardImpl(colors[index]);
    }

}
