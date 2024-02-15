package it.unibo.view;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.start.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

    public static StartController controller = GameStart.controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final StartView startStage = new StartView(controller);
        final MainStage mainStage = new MainStage(controller);

        startStage.setTitle("Ticket to Ride");
        startStage.showAndWait();
        if (startStage.isReady()) {
            mainStage.showAndWait();
        }
    }
}
