package it.unibo.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The class that represents the box containing the objectives of the current
 * player, extends {@link VBox}.
 */
public class ObjectiveBox extends VBox {

    private boolean isShown;

    /**
     * Constructor for the objective box.
     * 
     * @param controller      the main controller of the game
     * @param playerInterface the interface containg all the current player's
     *                        information
     */
    public ObjectiveBox(final MainController controller, final PlayerInterface playerInterface) {

        this.setPadding(new Insets(5));
        this.setSpacing(5);
        this.setAlignment(Pos.TOP_LEFT);

        final Text currentPlayer = new Text(
                "Current player: " + controller.getTurnController().getCurrentPlayer().getName());
        final Rectangle currentPlayerColor = new Rectangle(10, 10);

        currentPlayerColor.setFill(Color.rgb(
                controller.getTurnController().getCurrentPlayer().getColor().getRed(),
                controller.getTurnController().getCurrentPlayer().getColor().getGreen(),
                controller.getTurnController().getCurrentPlayer().getColor().getBlue()));

        final HBox playerInfo = new HBox(currentPlayer, currentPlayerColor);
        playerInfo.setSpacing(5);
        playerInfo.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().add(playerInfo);
        this.getChildren()
                .add(new Text("Carriages left: " + controller.getTurnController().getCurrentPlayer().getCarriageNum()));

        final Button showButton = new Button("Reveal objectives");
        showButton.setOnAction(event -> {
            toggleShown();
            if (isShown) {
                this.getChildren().removeIf(x -> this.getChildren().indexOf(x) > 2);
                this.getChildren()
                        .addAll(controller.getTurnController().getCurrentPlayer().getObjectiveCards().stream()
                                .map(x -> {
                                    final Text objective = new Text(
                                            x.getCities().first().getName() + " - " + x.getCities().second().getName()
                                                    + " (" + x.getScore() + " - " + (x.isCompleted() ? "Completed)"
                                                            : "Not completed)"));
                                    objective.setTextAlignment(TextAlignment.LEFT);
                                    objective.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 12));
                                    objective.setWrappingWidth(playerInterface.getMinWidth() * 0.8);
                                    return objective;
                                })
                                .toList());
            } else {
                this.getChildren().removeIf(x -> this.getChildren().indexOf(x) > 2);
                final Text placeholderText = new Text("The objectives are hidden");
                placeholderText.setTextAlignment(TextAlignment.LEFT);
                placeholderText.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 12));
                placeholderText.setWrappingWidth(playerInterface.getMinWidth() * 0.8);
                this.getChildren().add(placeholderText);
            }
        });

        final Text placeholderText = new Text("The objectives are hidden");
        placeholderText.setTextAlignment(TextAlignment.LEFT);
        placeholderText.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 12));
        placeholderText.setWrappingWidth(playerInterface.getMinWidth() * 0.8);

        this.getChildren().add(showButton);
        this.getChildren().add(placeholderText);

    }

    /**
     * Toggles the visibility of the objectives.
     */
    private void toggleShown() {
        this.isShown = !this.isShown;
    }
}
