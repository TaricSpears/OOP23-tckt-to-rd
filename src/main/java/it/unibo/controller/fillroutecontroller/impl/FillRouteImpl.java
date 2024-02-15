
package it.unibo.controller.fillroutecontroller.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.view.FillRoute.impl.FillRouteViewImpl;

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

    // this function returns true if the player has enough train cards with the
    // color of the chosen route

    @Override
    public boolean isRouteValid(Player player, Route route) {
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
        } else if (isColorEnough(player, Color.RED, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.BLUE, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.WHITE, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.GREEN, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.YELLOW, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.BLACK, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.ORANGE, route) && !route.isCompleted()) {
            return true;
        } else if (isColorEnough(player, Color.PINK, route) && !route.isCompleted()) {
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
    public void setColor(Player player, Route route) {
        route.setPlayer(player);
        route.setFilled();

    }

    // the main method of this class, it is called when the player clicks on a route
    @Override
    public void clickRoute(Player player, Route route) {
        if (isRouteValid(player, route)) {
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
                setColor(player, route);
            } else if (route.getColor().equals(Color.GRAY) && isRouteValid(player, route)) {
                openPopUp();

            }

        } else {
            openPopUp();
        }
    }

    @Override
    public void removeCards(Player player, Color color, int quantity) {
        for (int i = 0; i < quantity; i++) {
            player.removeTrainCard(color);
        }
    }

    @Override
    public boolean isColorEnough(Player player, Color color, Route route) {
        return player.getTrainCards().stream()
                .filter(card -> card.getColor().equals(color) || card.getColor().equals(Color.DARK_GRAY))
                .count() >= route.getScore();
    }
}
