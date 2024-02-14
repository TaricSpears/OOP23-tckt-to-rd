package it.unibo.model.deck.impl;

import java.awt.Color;
import java.util.Random;

import it.unibo.model.card.api.Card;
import it.unibo.model.card.api.Card.Type;
import it.unibo.model.card.impl.*;
import it.unibo.model.deck.api.Deck;

/*
 * Implementation of {@link Deck}.
 * Regulates the functioning of the decks.
 */
public class DeckImpl implements Deck {

    /**
     * @param train the type of the card to draw
     * @return the drawn card
     */
    @Override
    public Card drawCard(Type type) {
        return type == Type.TRAIN ? drawTrainCard() : drawObjectiveCard();
    }

    /**
     * @param type the type of the card to draw
     * @return the drawn objectiveCard
     */
    private ObjectiveCardImpl drawObjectiveCard() {
        return new ObjectiveCardImpl();
    }

    /**
     * @param type the type of the card to draw
     * @return the drawn trainCard
     */
    private TrainCardImpl drawTrainCard() {

        final Color[] colors = { Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW,
                Color.ORANGE, Color.GREEN, Color.PINK, Color.BLUE,
                Color.DARK_GRAY };

        final int[] probabilities = { 6, 6, 6, 6, 6, 6, 6, 6, 7 };

        int temp = new Random().nextInt(55);
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
