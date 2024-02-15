package it.unibo.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final StartView startStage = new StartView();
        final MainStage mainStage = new MainStage();
        startStage.setTitle("Ticket to Ride");
        startStage.showAndWait();
        if (startStage.isReady()) {
            mainStage.show();
        }
    }
}
