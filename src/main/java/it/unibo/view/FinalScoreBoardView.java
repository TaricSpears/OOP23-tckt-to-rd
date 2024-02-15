package it.unibo.view;

import it.unibo.controller.gamecontroller.api.StartController;
import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinalScoreBoardView extends Stage {

    public FinalScoreBoardView(final StartController controller) {
        this.setTitle("Final Game Score Board");

        final VBox playersList = new VBox();

        final Button endButton = new Button("Close");
        endButton.setOnAction(event -> {
            controller.closeView();
        });
    }
}
