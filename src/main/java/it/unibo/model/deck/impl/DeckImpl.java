package it.unibo.model.deck.impl;

import java.awt.Color;
import java.util.Random;

import it.unibo.model.card.api.Card;
import it.unibo.model.card.impl.*;
import it.unibo.model.deck.api.Deck;

public class DeckImpl implements Deck {

    @Override
    public Card drawCard(CardType type) {
        if (type == CardType.TRAIN) {
            return drawTrainCard(type);
        } else {
            return drawObjectiveCard(type);
        }

    }

    private Card drawObjectiveCard(CardType type) {
        return new ObjectiveCardImpl(type, null, 0);
    }

    private Card drawTrainCard(CardType type) {

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

        return new TrainCardImpl(type, colors[index]);
    }
}
