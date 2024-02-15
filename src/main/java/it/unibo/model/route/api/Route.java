package it.unibo.model.route.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;

import java.awt.Color;

public interface Route {

    Pair<City, City> getConnectedCity();

    boolean isCompleted();

    void setFilled();

    int getScore();

    Color getColor();

    void setPlayer(Player player);
}
