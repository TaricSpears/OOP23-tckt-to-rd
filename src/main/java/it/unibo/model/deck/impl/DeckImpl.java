package it.unibo.model.deck.impl;

import java.awt.Color;
import java.util.Random;

import it.unibo.model.card.api.toBeRemovedCard;
import it.unibo.model.card.impl.*;
import it.unibo.model.deck.api.Deck;

public class DeckImpl implements Deck {

    /**
     * @param type the type of the card to draw
     * @return the drawn card
     */
    @Override
    public toBeRemovedCard drawCard(toBeRemovedCardType type) {
        return type == toBeRemovedCardType.TRAIN ? drawTrainCard() : drawObjectiveCard();
    }

    /**
     * @param type the type of the card to draw
     * @return the drawn objectiveCard
     */
    private toBeRemovedCard drawObjectiveCard() {
        return new ObjectiveCardImpl(null, 0);
    }

    /**
     * @param type the type of the card to draw
     * @return the drawn trainCard
     */
    private toBeRemovedCard drawTrainCard() {

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
