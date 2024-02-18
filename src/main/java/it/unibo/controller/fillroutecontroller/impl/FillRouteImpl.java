
package it.unibo.controller.fillroutecontroller.impl;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.view.FillRoute.impl.FillRouteViewImpl;
import it.unibo.view.FillRoute.impl.NotEnoughCardsAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

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

    public FillRouteImpl(Player player, Region region, MainController controller) {
        this.player = player;

        this.route = controller.getGameInstance().getRoutes().get(region.getId());

    }

  

    @Override
    public boolean isRouteValid() {
        // controll jolly cards
        if (!route.getColor().equals(Color.GRAY) && !route.getColor().equals(null)) {
            System.out.println("the route is not gray");
            final long totCards = player.getListTrainCards().stream()
                    .filter(card -> card.getColor().equals(route.getColor()) || card.getColor().equals(Color.DARK_GRAY))
                    .count();
            player.getTrainCards().entrySet()
                    .forEach(card -> System.out.println(card.getKey() + " " + card.getValue()));
            if (totCards >= route.getScore() && !route.isCompleted()) {
                return true;
            } else {
                return false;
            }
        } else if ((isColorEnough(Color.RED) || isColorEnough(Color.BLACK) || isColorEnough(Color.PINK)
                || isColorEnough(Color.ORANGE) || isColorEnough(Color.YELLOW) || isColorEnough(Color.GREEN)
                || isColorEnough(Color.BLUE) || isColorEnough(Color.MAGENTA)) && !route.isCompleted()) {
            System.out.println("the route is gray and valid");
            return true;
        } else {
            System.out.println("the route is not valid");
            return false;
        }
    }
    // this function opens a pop up if the player can fill a route with both color
    // or joily train cards and let the player decide

    private void openPopUp() {
        this.popUp = new FillRouteViewImpl(this);
        chosenColor = popUp.openPopUp();

    }
    

    private void openAlert(String message) {
        this.alert = new NotEnoughCardsAlert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.openAlert();

    }

    // the main method of this class, it is called when the player clicks on a route
    @Override
    public boolean clickRoute() {
        System.out.println("is route valid()");
        if (isRouteValid()) {
            System.out.println("the route is valid");
            if (route.getScore() < player.getListTrainCards().stream()
                    .filter(card -> card.getColor().equals(route.getColor())).count()
                    && !route.getColor().equals(Color.GRAY)) {
                for (int i = 0; i < route.getScore(); i++) {
                    if (player.getListTrainCards().stream().filter(card -> card.getColor().equals(route.getColor()))
                            .count() > 0) {
                        player.removeTrainCard(route.getColor(), 1);
                    } else {
                        player.removeTrainCard(Color.DARK_GRAY, 1);
                    }
                }
                player.addRoute(route);
                route.setFilled();
                return true;
            } else if (route.getColor().equals(Color.GRAY) && isRouteValid()) {
                openPopUp();
                if (chosenColor != null) {
                    player.removeTrainCard(chosenColor, route.getScore());
                    player.addRoute(route);
                    route.setFilled();
                    return true;
                } else {
                    openAlert("You didn't choose a color.");
                    return false;
                }
            }
            return false;
        } else {
            openAlert("You don't have enough cards to fill this route.");
            return false;
        }
    }

    @Override
    public boolean isColorEnough(Color color) {
        System.out.println(route.getScore() + " " + color);
        return player.getListTrainCards().stream()
                .filter(card -> card.getColor().equals(color) || card.getColor().equals(Color.DARK_GRAY))
                .count() >= route.getScore();
    }

    @Override
    public ObservableList<Color> getAvailableRoutes(FillRoute fillRoute) {
        final ObservableList<Color> availableRoutes = FXCollections.observableArrayList();
        final List<Color> colors = new ArrayList<Color>(
                List.of(Color.RED, Color.BLACK, Color.PINK, Color.ORANGE, Color.YELLOW,
                        Color.GREEN, Color.BLUE, Color.MAGENTA));

        for (var color : colors) {
            if (fillRoute.isColorEnough(color)) {
                availableRoutes.add(color);
            }
        }
        return availableRoutes;
    }

}
