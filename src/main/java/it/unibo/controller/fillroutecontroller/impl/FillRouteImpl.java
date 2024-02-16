
package it.unibo.controller.fillroutecontroller.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.view.FillRoute.impl.FillRouteViewImpl;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.ShortStringConverter;

import java.util.HashSet;
import java.util.Set;

import java.awt.Color;

/**
 * This class is used to fill the routes with the color of the player.
 * It provides methods to check if a route can be filled by a player, open a
 * pop-up for player's decision,
 * set the color of a route with the player's color, and handle the click event
 * on a route.
 */

public class FillRouteImpl implements FillRoute {

    private FillRouteViewImpl popUp;
    private Player player;
    private Route route;

    public FillRouteImpl(Player player, Route route) {
        this.player = player;
        this.route = route;
    }

    // this function returns true if the player has enough train cards with the
    // color of the chosen route

    @Override
    public boolean isRouteValid() {
        // controll jolly cards
        if (route.getColor().equals(Color.GRAY) != true) {
            final long totCards = player.getTrainCards().stream()
                    .filter(card -> card.getColor().equals(route.getColor()) || card.getColor().equals(Color.DARK_GRAY))
                    .count();

            if (totCards >= route.getScore() && !route.isCompleted()) {
                return true;
            } else {
                return false;
            }
        } else if (isColorEnough(Color.RED) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.BLUE) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.WHITE) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.GREEN) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.YELLOW) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.BLACK) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.ORANGE) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(Color.PINK) && !route.isCompleted()) {
            return true;
        } else {
            return false;
        }
    }
    // this function opens a pop up if the player can fill a route with both color
    // or joily train cards and let the player decide

    private void openPopUp() {
        this.popUp = new FillRouteViewImpl(this);
    }

    // this function sets the color of the button of the chosen route with the
    // player color
    @Override
    public void setColor() {
        route.setPlayer(player);
        route.setFilled();

    }

    // the main method of this class, it is called when the player clicks on a route
    @Override
    public void clickRoute() {
        if (isRouteValid()) {
            if (route.getScore() < player.getTrainCards().stream()
                    .filter(card -> card.getColor().equals(route.getColor())).count()) {
                for (int i = 0; i < route.getScore(); i++) {
                    if (player.getTrainCards().stream().filter(card -> card.getColor().equals(route.getColor()))
                            .count() > 0) {
                        player.removeTrainCard(route.getColor());
                    } else {
                        player.removeTrainCard(Color.DARK_GRAY);
                    }
                }
                setColor();
            } else if (route.getColor().equals(Color.GRAY) && isRouteValid()) {
                openPopUp();

            }

        } else {
            openPopUp();
        }
    }

    @Override
    public void removeCards(Color color, int quantity) {
        for (int i = 0; i < quantity; i++) {
            player.removeTrainCard(color);
        }
    }

    @Override
    public boolean isColorEnough(Color color) {
        return player.getTrainCards().stream()
                .filter(card -> card.getColor().equals(color) || card.getColor().equals(Color.DARK_GRAY))
                .count() >= route.getScore();
    }

    @Override
    public ObservableList<String> getAvailableRoutes(FillRoute fillRoute) {
        final ObservableList<String> availableRoutes = FXCollections.observableArrayList();
        if (isColorEnough(Color.BLACK)) {
            availableRoutes.add(Color.BLACK.toString());
        } else if (isColorEnough(Color.BLUE)) {
            availableRoutes.add(Color.BLUE.toString());
        } else if (isColorEnough(Color.GREEN)) {
            availableRoutes.add(Color.GREEN.toString());
        } else if (isColorEnough(Color.ORANGE)) {
            availableRoutes.add(Color.ORANGE.toString());
        } else if (isColorEnough(Color.PINK)) {
            availableRoutes.add(Color.PINK.toString());
        } else if (isColorEnough(Color.RED)) {
            availableRoutes.add(Color.RED.toString());
        } else if (isColorEnough(Color.WHITE)) {
            availableRoutes.add(Color.WHITE.toString());
        } else if (isColorEnough(Color.YELLOW)) {
            availableRoutes.add(Color.YELLOW.toString());
        }
        return availableRoutes;
    }

}
