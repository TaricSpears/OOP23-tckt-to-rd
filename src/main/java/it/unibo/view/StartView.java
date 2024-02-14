package it.unibo.view;

import it.unibo.commons.Pair;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.util.Set;
import java.util.HashSet;

public final class StartView extends Application {

    final Set<Pair<String, Color>> players = new HashSet<>();

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("Ticket To Ride");

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

        playersList.setMaxSize(350, 200);

        final Button startButton = new Button("Start Game");
        startButton.setDisable(true);
        startButton.setOnAction(event -> {

        });

        submitButton.setOnAction(event -> {
            if (!players.stream().anyMatch(
                    x -> x.first().equals(nameField.getText()) || x.second().equals(colorPicker.getValue()))) {
                players.add(new Pair<String, Color>(nameField.getText(), colorPicker.getValue()));
                nameField.clear();
            }
            if (players.size() >= 6) {
                submitButton.setDisable(true);
            }
            if (players.size() >= 2) {
                startButton.setDisable(false);
            }
            playersList.setItems(FXCollections.observableArrayList(players));
            playersList
                    .setCellFactory(new Callback<ListView<Pair<String, Color>>, ListCell<Pair<String, Color>>>() {
                        @Override
                        public ListCell<Pair<String, Color>> call(ListView<Pair<String, Color>> param) {
                            return new ColorRectCell();
                        }
                    });
        });

        final BorderPane root = new BorderPane();
        final VBox nameSelect = new VBox();
        final HBox nameInput = new HBox();
        final HBox startBox = new HBox();

        nameInput.setPadding(new Insets(15, 12, 15, 12));
        nameInput.setSpacing(10);
        nameInput.getChildren().addAll(colorPicker, nameField, submitButton);
        nameInput.setAlignment(Pos.CENTER);

        nameSelect.getChildren().addAll(playersList, nameInput);
        nameSelect.setAlignment(Pos.CENTER);

        root.setCenter(nameSelect);

        startBox.getChildren().add(startButton);
        startBox.setPadding(new Insets(15, 12, 15, 12));
        startBox.setAlignment(Pos.CENTER);

        root.setBottom(startBox);

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch(args);
    }

    static class ColorRectCell extends ListCell<Pair<String, Color>> {
        @Override
        public void updateItem(final Pair<String, Color> item, final boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(20, 20);
            if (item != null) {
                rect.setFill(item.second());
                final HBox entry = new HBox();
                entry.setPadding(new Insets(5, 2, 5, 2));
                entry.setSpacing(10);
                entry.getChildren().addAll(rect, new Label(item.first()));
                setGraphic(entry);
            } else {
                setGraphic(null);
            }
        }
    }
}
