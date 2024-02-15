package it.unibo.view;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.StartController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinalScoreBoardView extends Stage {

    public FinalScoreBoardView(final StartController controller) {
        this.setTitle("Final Game Score Board");

        final Button endButton = new Button("Close");
        endButton.setOnAction(event -> {
            controller.closeView();
        });

        final Button newGame = new Button("New Game");

        final BorderPane root = new BorderPane();
        final VBox playersList = new VBox();
        final HBox gameControls = new HBox();

        gameControls.getChildren().addAll(endButton, newGame);

        final List<Pair<String, Integer>> players = controller.getMainController().getScore();

        for (final Pair<String, Integer> player : players) {
            playersList.getChildren().add(new Text(player.first() + " - " + player.second()));
        }

        root.setCenter(playersList);
        root.setBottom(gameControls);

        this.setScene(new Scene(root, 300, 500));
    }
}
