package it.unibo.view;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FinalScoreBoardView extends Stage {

    public FinalScoreBoardView(final MainController controller) {
        this.setTitle("Final Game Score Board");

        final Button endButton = new Button("Close");
        endButton.setOnAction(event -> {
            this.close();
        });

        final Button newGame = new Button("New Game");
        newGame.setOnAction(event -> {
            controller.getMainController().newGame();
            this.close();
        });

        final BorderPane root = new BorderPane();
        final VBox playersList = new VBox();
        final HBox gameControls = new HBox();

        gameControls.getChildren().addAll(newGame, endButton);
        gameControls.setPadding(new Insets(15, 12, 15, 12));
        gameControls.setSpacing(30);
        gameControls.setAlignment(Pos.CENTER);

        final List<Pair<String, Integer>> players = controller.getMainController().getScore();

        for (final Pair<String, Integer> player : players) {
            final Text playerEntry = new Text(player.first() + " - " + player.second());

            playerEntry.setTextAlignment(TextAlignment.CENTER);
            playerEntry.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 25));

            playersList.getChildren().add(playerEntry);
        }

        playersList.setPadding(new Insets(15, 12, 15, 12));
        playersList.setAlignment(Pos.CENTER);

        root.setCenter(playersList);
        root.setBottom(gameControls);

        this.setScene(new Scene(root, 300, 500));
        this.setMinWidth(300);
        this.setMinHeight(500);
    }
}
