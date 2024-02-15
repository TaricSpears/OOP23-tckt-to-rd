package it.unibo.view;

import it.unibo.controller.gamecontroller.api.StartController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainStage extends Stage {

    public MainStage(final StartController controller) {
        final BorderPane root = new BorderPane();

        final Button endGame = new Button("End Game");
        endGame.setOnAction(event -> {
            this.close();
            final FinalScoreBoardView scoreBoardView = new FinalScoreBoardView(controller);
            scoreBoardView.show();
        });

        root.setCenter(endGame);

        this.setScene(new Scene(root, 900, 500));
    }
}
