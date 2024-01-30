package it.unibo.model.player.api;

import java.util.Set;

import it.unibo.model.card.api.Card;
import it.unibo.model.route.api.Route;

import java.awt.Color;

public interface Player {

    String getName();

    Color getColor();

    Set<Card> getTrainCards();

    Set<Card> getObjectiveCards();

    Set<Route> getCompletedRoutes();

    int getCarriageNum();

    void setCarriageNum(int number);

    int getObjectiveScore();

    void setObjectiveScore(int number);

    int getRouteScore();

    void setRouteScore(int number);
}
