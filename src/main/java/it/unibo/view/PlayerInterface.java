package it.unibo.view;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * The class that represents the interface of the player, extends {@link VBox}.
 * 
 * It contains all the buttons and information that the player needs to play the
 * game.
 */
public class PlayerInterface extends VBox {

    private static final int SPACING_VALUE = 10;
    private static final int SPACINGBOX_VALUE = 2;
    private static final int PADDING_VALUE = 20;
    private static final double BOX_SCALE = 0.8;
    private static final double ALERT_WIDTH_SCALE = 0.8;
    private static final double ALERT_HEIGHT_SCALE = 0.7;

    private final HBox controlBox;
    private final HBox drawBox;
    private final ObjectiveBox objectiveBox = new ObjectiveBox();
    private final CardBox cardBox;
    private final Text phase;

    /**
     * Constructor for the player interface.
     * 
     * @param controller the main controller of the game
     * @param width      the width of this view
     * @param height     the height of this view
     */
    public PlayerInterface(final MainController controller, final double width, final double height) {
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();

        final Button rules = new Button("Rules");
        phase = new Text(controller.getPhaseController().toString());
        cardBox = new CardBox(controller);
        cardBox.initialize();

        phase.setWrappingWidth(this.getMinWidth() * BOX_SCALE);
        objectiveBox.setMaxWidth(this.getMinWidth() * BOX_SCALE);

        this.setPadding(new Insets(PADDING_VALUE));
        this.setSpacing(SPACING_VALUE);

        rules.setOnAction(event -> {

            try {
                final String rulesText = Files.readString(Path.of("src/main/resources/text/Rules.txt"),
                        StandardCharsets.UTF_8);
                final Alert alert = new Alert(AlertType.INFORMATION, rulesText);
                alert.setResizable(false);
                alert.getDialogPane().setMaxSize(bounds.getWidth() * ALERT_WIDTH_SCALE,
                        bounds.getHeight() * ALERT_WIDTH_SCALE);
                alert.getDialogPane().setMinSize(bounds.getWidth() * ALERT_WIDTH_SCALE,
                        bounds.getHeight() * ALERT_HEIGHT_SCALE);

                alert.setTitle("Rules");
                alert.showAndWait();

            } catch (IOException e) {
                Logger.getLogger(PlayerInterface.class.getName()).fine("Exception in file path operations");
            }

        });

        final Button endTurn = new Button("End Turn");
        endTurn.setOnAction(e -> {
            controller.getGameController().endTurn();
            if (!controller.getGameController().isGameEnded()) {
                if (controller.getGameController().isLastTurn()) {
                    new LastTurnPopUp().display();
                }
                new EndTurnPopUp(controller);
            }
        });
        endTurn.setDisable(!controller.getPhaseController().isEndPhase(controller));

        final Button drawTrain = new Button("Draw Train Card");
        drawTrain.setOnAction(event -> {
            new DrawTrainCardPopUp(controller.getGameController().handleDrawTrainCard().getColor());
        });
        drawTrain.setDisable(controller.getPhaseController().isEndPhase(controller));

        final Button drawObjective = new Button("Draw new Objective");
        drawObjective.setOnAction(event -> {
            controller.getGameController().handleDrawObjectiveCard();
        });
        drawObjective.setDisable(!controller.getPhaseController().isMidPhase());

        controlBox = new HBox(endTurn, rules);
        drawBox = new HBox(drawObjective, drawTrain);
        controlBox.setSpacing(SPACINGBOX_VALUE);
        drawBox.setSpacing(SPACINGBOX_VALUE);
    }

    /**
     * A method to initialize the player interface.
     * 
     * @param controller the main controller of the game
     */
    public void initialize(final MainController controller) {
        objectiveBox.initialize(controller, this);
        this.getChildren().addAll(phase, drawBox, controlBox, objectiveBox, cardBox);
    }
}
