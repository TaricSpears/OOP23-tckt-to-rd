package it.unibo.model.player.api;

import java.util.Set;
import java.util.List;

import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.route.api.Route;

import java.awt.Color;

public interface Player {

    String getName();

    Color getColor();

    List<TrainCard> getTrainCards();

    void removeTrainCard(Color color);

    Set<ObjectiveCard> getObjectiveCards();

    Set<Route> getCompletedRoutes();

    int getCarriageNum();

    void setCarriageNum(int number);

    int getObjectiveScore();

    void setObjectiveScore(int number);

    int getRouteScore();

    void setRouteScore(int number);
}
