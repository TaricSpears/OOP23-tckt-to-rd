package it.unibo.view;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.paint.Color;

import java.util.Set;
import java.util.HashSet;

/**
 * The class that represents the stage where the players are selected, extends
 * {@link Stage}.
 */
public final class StartStage extends Stage {

    private static final int TOP_BOTTOM_PADDING = 15;
    private static final int LEFT_RIGHT_PADDING = 12;
    private static final int SPACING_VALUE = 10;
    private static final double WIDTH_SCALE = 0.3;
    private static final double HEIGHT_SCALE = 0.5;
    private final Set<Pair<String, Color>> players = new HashSet<>();

    /**
     * Constructor for the start stage.
     * 
     * @param controller the main controller of the game
     */
    public StartStage(final MainController controller) {

        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();

        this.setTitle("Player Select");

        final TextField nameField = new TextField();
        final Button submitButton = new Button("Submit");
        final ColorPicker colorPicker = new ColorPicker();
        final ListView<Pair<String, Color>> playersList = new ListView<>();

        nameField.setPromptText("Insert the player name");
        nameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                submitButton.fire();
            }
        });

        final Button startButton = new Button("Start Game");
        startButton.setDisable(true);
        startButton.setOnAction(event -> {
            controller.startGame();
            this.close();
        });

        submitButton.setOnAction(event -> {
            if (controller.getGameController()
                    .addPlayer(new Pair<String, java.awt.Color>(nameField.getText(),
                            new java.awt.Color((float) colorPicker.getValue().getRed(),
                                    (float) colorPicker.getValue().getGreen(),
                                    (float) colorPicker.getValue().getBlue())))) {
                players.add(new Pair<String, Color>(nameField.getText(), colorPicker.getValue()));
                nameField.clear();
            }
            if (controller.getGameController().canStart()) {
                startButton.setDisable(false);
            } else {
                startButton.setDisable(true);
            }
            playersList.setItems(FXCollections.observableArrayList(players));
            playersList
                    .setCellFactory(new Callback<ListView<Pair<String, Color>>, ListCell<Pair<String, Color>>>() {
                        @Override
                        public ListCell<Pair<String, Color>> call(final ListView<Pair<String, Color>> param) {
                            return new ColorRectCell();
                        }
                    });
        });

        final Image image = new Image("/img/Wallpapers/Wallpaper2.jpg");
        final BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false));

        final BorderPane root = new BorderPane();
        final VBox nameSelect = new VBox();
        final HBox nameInput = new HBox();
        final HBox startBox = new HBox();

        nameInput.setPadding(new Insets(TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING, TOP_BOTTOM_PADDING,
                LEFT_RIGHT_PADDING));
        nameInput.setSpacing(SPACING_VALUE);
        nameInput.getChildren().addAll(colorPicker, nameField, submitButton);
        nameInput.setAlignment(Pos.CENTER);

        nameSelect.getChildren().addAll(playersList, nameInput);
        nameSelect.setAlignment(Pos.CENTER);

        root.setCenter(nameSelect);
        root.setBackground(new Background(backgroundImage));

        startBox.getChildren().add(startButton);
        startBox.setPadding(new Insets(TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING, TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING));
        startBox.setAlignment(Pos.CENTER);

        root.setBottom(startBox);

        final Scene scene = new Scene(root, bounds.getWidth() * WIDTH_SCALE, bounds.getHeight() * HEIGHT_SCALE);
        this.setScene(scene);

        playersList.setMaxSize(scene.getWidth() * WIDTH_SCALE, scene.getHeight() * HEIGHT_SCALE);
        this.setMinWidth(bounds.getWidth() * WIDTH_SCALE);
        this.setMinHeight(bounds.getHeight() * HEIGHT_SCALE);
    }

    /**
     * A nested static class that represents the cell of the list view, extends
     * {@link ListCell}.
     */
    static class ColorRectCell extends ListCell<Pair<String, Color>> {
        private static final int LEFT_RIGHT_PADDING = 2;
        private static final int TOP_BOTTOM_PADDING = 5;
        private static final int WIDTH_AND_HEIGHT = 20;

        @Override
        public void updateItem(final Pair<String, Color> item, final boolean empty) {
            super.updateItem(item, empty);
            final Rectangle rect = new Rectangle(WIDTH_AND_HEIGHT, WIDTH_AND_HEIGHT);
            if (item != null) {
                rect.setFill(item.second());
                final HBox entry = new HBox();
                entry.setPadding(
                        new Insets(TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING, TOP_BOTTOM_PADDING, LEFT_RIGHT_PADDING));
                entry.setSpacing(SPACING_VALUE);
                entry.getChildren().addAll(rect, new Label(item.first()));
                setGraphic(entry);
            } else {
                setGraphic(null);
            }
        }
    }
}
