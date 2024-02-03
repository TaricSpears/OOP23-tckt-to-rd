package it.unibo.model.player.impl;

import it.unibo.model.player.api.Player;
import it.unibo.model.card.api.Card;
import it.unibo.model.route.api.Route;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import java.awt.Color;

public class PlayerImpl implements Player {

    private final String name;
    private final Color color;
    private final Set<Card> trainCards;
    private final Set<Card> objectiveCards;
    private final Set<Route> completedRoutes;
    private int carriageNum;
    private int objectiveScore;
    private int routeScore;

    public PlayerImpl(final String name, final Color color, final int carriageNum) {
        this.name = name;
        this.color = color;
        this.trainCards = new LinkedHashSet<>();
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

    public Set<Card> getTrainCards() {
        return Collections.unmodifiableSet(trainCards);
    }

    public Set<Card> getObjectiveCards() {
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
}
