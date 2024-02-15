package it.unibo.view;

import it.unibo.controller.gamecontroller.api.StartController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainStage extends Stage {

    public MainStage(final StartController controller) {
        final BorderPane root = new BorderPane();

        this.setScene(new Scene(root, 900, 500));
    }
}
