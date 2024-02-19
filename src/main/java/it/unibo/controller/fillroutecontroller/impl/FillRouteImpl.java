
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
            List.of(Color.RED, Color.BLACK, Color.MAGENTA, Color.ORANGE, Color.YELLOW,
                    Color.GREEN, Color.BLUE, Color.WHITE));

    public FillRouteImpl(Player player, Region region, MainController controller) {

        this.player = player;

        this.route = controller.getGameInstance().getRoutes().get(region.getId());

    }

    /**
     * @return true if the cards of the player are enough to fill the route
     */
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

    /**
     * @return true if the action of filling the route is successful
     */
    @Override
    public boolean clickRoute() {
        if (isRouteValid() && this.route.getScore() <= this.player.getCarriageNum()) {
            if (this.route.getColor().equals(Color.GRAY)) {
                openPopUp();
                if (!this.chosenColor.equals(null)) {
                    if (this.route.getScore() <= this.player.getTrainCards().get(this.chosenColor)) {
                        this.player.removeTrainCard(this.chosenColor, this.route.getScore());
                        this.player.setCarriageNum(this.player.getCarriageNum() - this.route.getScore());
                    } else {
                        final int colorCardNum = this.player.getTrainCards().get(this.chosenColor);
                        this.player.removeTrainCard(chosenColor, colorCardNum);
                        this.player.removeTrainCard(Color.DARK_GRAY, this.route.getScore() - colorCardNum);
                        this.player.setCarriageNum(this.player.getCarriageNum() - this.route.getScore());
                    }
                    this.player.addRoute(route);
                    this.route.setFilled();
                    return true;
                } else {
                    openAlert("You didn't choose a color.");
                    return false;
                }
            } else {
                if (this.route.getScore() <= this.player.getTrainCards().get(this.route.getColor())) {
                    this.player.removeTrainCard(this.route.getColor(), this.route.getScore());
                } else {
                    final int colorCardNum = this.player.getTrainCards().get(this.route.getColor());
                    this.player.removeTrainCard(this.route.getColor(), colorCardNum);
                    this.player.removeTrainCard(Color.DARK_GRAY, this.route.getScore() - colorCardNum);
                }
                this.player.setCarriageNum(this.player.getCarriageNum() - this.route.getScore());
                this.player.addRoute(route);
                this.route.setFilled();
            }
            return true;
        } else {
            openAlert("You don't have enough cards to fill this route.");
            return false;
        }
    }

    /**
     * @param color the color of the route.
     * 
     * @return true if the cards of the player are enough to fill the route
     */
    @Override
    public boolean isColorEnough(Color color) {
        return this.player.getTrainCards().get(color) + this.player.getTrainCards().get(Color.DARK_GRAY) >= this.route
                .getScore();

    }

    /**
     * @param fillRoute the FillRoute object.
     * 
     * @return the list of the Colors that can fill a GRAY route
     */
    @Override
    public ObservableList<String> getAvailableRoutes(FillRoute fillRoute) {
        final ObservableList<String> availableRoutes = FXCollections.observableArrayList();

        for (var color : this.colors) {
            if (fillRoute.isColorEnough(color)) {
                if (color.equals(Color.RED)) {
                    availableRoutes.add("RED");
                } else if (color.equals(Color.BLACK)) {
                    availableRoutes.add("BLACK");
                } else if (color.equals(Color.MAGENTA)) {
                    availableRoutes.add("MAGENTA");
                } else if (color.equals(Color.ORANGE)) {
                    availableRoutes.add("ORANGE");
                } else if (color.equals(Color.YELLOW)) {
                    availableRoutes.add("YELLOW");
                } else if (color.equals(Color.GREEN)) {
                    availableRoutes.add("GREEN");
                } else if (color.equals(Color.BLUE)) {
                    availableRoutes.add("BLUE");
                } else if (color.equals(Color.WHITE)) {
                    availableRoutes.add("WHITE");
                }
            }
        }
        return availableRoutes;
    }
}
