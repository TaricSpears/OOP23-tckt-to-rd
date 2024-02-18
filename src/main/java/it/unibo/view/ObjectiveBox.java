package it.unibo.view;

import javafx.scene.paint.Color;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ObjectiveBox extends VBox {

    private boolean isShown = false;

    public ObjectiveBox(final MainController controller, final PlayerInterface playerInterface) {

        this.setPadding(new Insets(5));
        this.setSpacing(5);
        this.setAlignment(Pos.TOP_LEFT);

        this.getChildren()
                .add(new Text("Current player: " + controller.getTurnController().getCurrentPlayer().getName()));

        final Button showButton = new Button("Reveal objectives");
        showButton.setOnAction(event -> {
            toggleShown();
            if (isShown) {
                this.getChildren().removeIf(x -> this.getChildren().indexOf(x) > 1);
                this.getChildren()
                        .addAll(controller.getTurnController().getCurrentPlayer().getObjectiveCards().stream()
                                .map(x -> {
                                    final Text objective = new Text(
                                            x.getCities().first().getName() + " - " + x.getCities().second().getName()
                                                    + " (" + x.getScore() + ")");
                                    objective.setTextAlignment(TextAlignment.LEFT);
                                    objective.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 12));
                                    objective.setWrappingWidth(playerInterface.getMinWidth() * 0.8);
                                    return objective;
                                })
                                .toList());
            } else {
                this.getChildren().removeIf(x -> this.getChildren().indexOf(x) > 1);
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

        this.setBorder(new Border(new BorderStroke(
            Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
        this.getChildren().add(showButton);
        this.getChildren().add(placeholderText);
        
    }

    private void toggleShown() {
        this.isShown = !this.isShown;
    }
}
