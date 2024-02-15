package it.unibo.model.route.api;

import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.carriage.impl.Carriage;

import java.awt.Color;

public interface Route {

    Pair<City, City> getConnectedCity();

    boolean isCompleted();

    void setFilled();

    int getScore();

    Color getColor();

    void setPlayer(Player player);

    int getId();

    Set<Carriage> getRailUnits();

}
