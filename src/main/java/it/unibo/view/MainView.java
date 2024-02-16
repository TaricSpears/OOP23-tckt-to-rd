package it.unibo.view;

import java.util.Objects;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.start.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

    public static StartController controller = GameStart.controller;
    private StartStage startStage;
    private MainStage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller.setMainApp(this);

        launchPlayerSlect();
    }

    public void launchPlayerSlect() {
        startStage = new StartStage(controller);
        startStage.show();
    }

    public void launchMainView() {
        mainStage = new MainStage(controller);
        mainStage.show();
    }

    public void launchScoreBoard() {
        final FinalScoreBoardView scoreBoardView = new FinalScoreBoardView(controller);
        scoreBoardView.show();
    }

    public void closeMainView() {
        if (!Objects.isNull(mainStage)) {
            mainStage.close();
        }
    }
}
