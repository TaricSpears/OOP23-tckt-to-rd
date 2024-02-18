
package it.unibo.controller.fillroutecontroller.impl;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.view.FillRoute.impl.FillRouteViewImpl;
import it.unibo.view.FillRoute.impl.NotEnoughCardsAlert;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.converter.ShortStringConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

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
    private NotEnoughCardsAlert alert;
    private Player player;
    private Route route;
    private Color chosenColor;

    final List<Color> colors = new ArrayList<Color>(
            List.of(Color.RED, Color.BLACK, Color.PINK, Color.ORANGE, Color.YELLOW,
                    Color.GREEN, Color.BLUE, Color.WHITE));

    public FillRouteImpl(Player player, Region region, MainController controller) {

        this.player = player;

        this.route = controller.getGameInstance().getRoutes().get(region.getId());

    }

    @Override
    public boolean isRouteValid() {

        if (route.getColor().equals(Color.GRAY)) {
            for (final var color : colors) {
                if (isColorEnough(color)) {
                    return true;
                }
            }
        } else {
            if (isColorEnough(route.getColor())) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    private void openPopUp() {
        this.popUp = new FillRouteViewImpl(this);
        chosenColor = popUp.openPopUp();

    }

    private void openAlert(String message) {
        this.alert = new NotEnoughCardsAlert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.openAlert();

    }

    @Override
    public boolean clickRoute() {
        if (isRouteValid()) {
            if (route.getColor().equals(Color.GRAY)) {
                openPopUp();
                if (!chosenColor.equals(null)) {
                    if (route.getScore() <= player.getTrainCards().get(chosenColor)) {
                        player.removeTrainCard(chosenColor, route.getScore());
                    } else {
                        final int colorCardNum = player.getTrainCards().get(chosenColor);
                        player.removeTrainCard(chosenColor, colorCardNum);
                        player.removeTrainCard(Color.DARK_GRAY, route.getScore() - colorCardNum);
                    }
                    player.addRoute(route);
                    route.setFilled();
                    return true;
                } else {
                    openAlert("You didn't choose a color.");
                    return false;
                }
            } else {
                if (route.getScore() <= player.getTrainCards().get(route.getColor())) {
                    player.removeTrainCard(route.getColor(), route.getScore());
                } else {
                    final int colorCardNum = player.getTrainCards().get(route.getColor());
                    player.removeTrainCard(route.getColor(), colorCardNum);
                    player.removeTrainCard(Color.DARK_GRAY, route.getScore() - colorCardNum);
                }
                player.addRoute(route);
                route.setFilled();
            }
            return true;
        } else {
            openAlert("You don't have enough cards to fill this route.");
            return false;
        }
    }

    @Override
    public boolean isColorEnough(Color color) {
        return player.getTrainCards().get(color) + player.getTrainCards().get(Color.DARK_GRAY) >= route.getScore();

    }

    @Override
    public ObservableList<Color> getAvailableRoutes(FillRoute fillRoute) {
        final ObservableList<Color> availableRoutes = FXCollections.observableArrayList();

        for (var color : colors) {
            if (fillRoute.isColorEnough(color)) {
                availableRoutes.add(color);
            }
        }
        return availableRoutes;
    }
}
