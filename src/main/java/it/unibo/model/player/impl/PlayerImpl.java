package it.unibo.model.player.impl;

import it.unibo.model.player.api.Player;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.card.api.toBeRemovedCard;
import it.unibo.model.route.api.Route;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.awt.Color;

public class PlayerImpl implements Player {

    private final String name;
    private final Color color;
    private final List<TrainCard> trainCards;
    private final Set<ObjectiveCard> objectiveCards;
    private final Set<Route> completedRoutes;
    private int carriageNum;
    private int objectiveScore;
    private int routeScore;

    public PlayerImpl(final String name, final Color color, final int carriageNum) {
        this.name = name;
        this.color = color;
        this.trainCards = new LinkedList<>();
        this.objectiveCards = new LinkedHashSet<>();
        this.completedRoutes = new LinkedHashSet<>();
        this.carriageNum = carriageNum;
        this.objectiveScore = 0;
        this.routeScore = 0;
    }

    public PlayerImpl(final String name, final int carriageNum) {
        this(name, Color.BLACK, carriageNum);
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public List<TrainCard> getTrainCards() {
        List<TrainCard> trainCardSet = new LinkedList<>(trainCards);
        return Collections.unmodifiableList(trainCardSet);
    }

    public Set<ObjectiveCard> getObjectiveCards() {
        return Collections.unmodifiableSet(objectiveCards);
    }

    public Set<Route> getCompletedRoutes() {
        return Collections.unmodifiableSet(completedRoutes);
    }

    public int getCarriageNum() {
        return this.carriageNum;
    }

    public void setCarriageNum(final int number) {
        this.carriageNum = number;
    }

    public int getObjectiveScore() {
        return this.objectiveScore;
    }

    public void setObjectiveScore(final int number) {
        this.objectiveScore += number;
    }

    public int getRouteScore() {
        return this.routeScore;
    }

    public void setRouteScore(final int number) {
        this.routeScore += number;
    }

    @Override
    public void removeTrainCard(Color color) {

    }
}
