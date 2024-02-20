package it.unibo.view;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * View for the final score board, extends {@link Stage}.
 */
public class FinalScoreBoardView extends Stage {

    private static final double SCENE_WIDTH_SCALE = 0.3;
    private static final double SCENE_HEIGHT_SCALE = 0.5;
    private static final int FONT_SIZE = 25;
    private static final int TOP_BOTTOM_PADDING = 15;
    private static final int LEFT_RIGHT_PADDING = 12;
    private static final int SPACING_VALUE = 30;

    /**
     * Constructor for the final score board view.
     * 
     * @param controller the main controller of the game
     */
    public FinalScoreBoardView(final MainController controller) {

        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();

        this.setTitle("Final Game Score Board");

        final Button endButton = new Button("Close");
        endButton.setOnAction(event -> {
            controller.getGameController().closeGame();
        });

        final Button newGame = new Button("New Game");
        newGame.setOnAction(event -> {
            controller.getGameController().newGame();
        });

        final BorderPane root = new BorderPane();
        final VBox playersList = new VBox();
        final HBox gameControls = new HBox();
        final BorderPane pane = new BorderPane();

        final Image image = new Image("/img/Wallpapers/Wallpaper1.jpg");
        final BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false));
        root.setBackground(new Background(backgroundImage));

        gameControls.getChildren().addAll(newGame, endButton);
        gameControls
                .setPadding(new Insets(TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING, TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING));
        gameControls.setSpacing(SPACING_VALUE);
        gameControls.setAlignment(Pos.CENTER);

        final List<Pair<String, Double>> players = controller.getGameController().getScore();

        for (final Pair<String, Double> player : players) {
            final Text playerEntry = new Text(player.first() + " : " + player.second());

            playerEntry.setTextAlignment(TextAlignment.CENTER);
            playerEntry.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, FONT_SIZE));
            playersList.getChildren().add(playerEntry);
        }

        playersList
                .setPadding(new Insets(TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING, TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING));
        playersList.setAlignment(Pos.CENTER);

        final BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);

        pane.setBackground(background);
        pane.setCenter(playersList);
        pane.setMaxHeight(bounds.getHeight() * SCENE_HEIGHT_SCALE * SCENE_HEIGHT_SCALE * SCENE_HEIGHT_SCALE);
        pane.setMaxWidth(bounds.getWidth() * SCENE_WIDTH_SCALE * SCENE_WIDTH_SCALE * SCENE_WIDTH_SCALE);
        root.setCenter(pane);
        root.setBottom(gameControls);

        this.setScene(new Scene(root, bounds.getWidth() * SCENE_WIDTH_SCALE, bounds.getHeight() * SCENE_HEIGHT_SCALE));
        this.setMinWidth(bounds.getWidth() * SCENE_WIDTH_SCALE);
        this.setMinHeight(bounds.getHeight() * SCENE_HEIGHT_SCALE);
    }

    /**
     * A method to close the stage.
     */
    public void closeStage() {
        this.close();
    }
}
