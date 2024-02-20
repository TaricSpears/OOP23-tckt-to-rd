package it.unibo.view;

import java.util.Objects;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main view of the game, extends {@link Application}.
 */
public class MainView extends Application {

    private static MainController controller = GameStart.CONTROLLER;
    private StartStage startStage;
    private MainStage mainStage;
    private FinalScoreBoardView scoreBoardView;

    /**
     * The main method that starts the game.
     * 
     * @param unusedStage the primary stage.
     * 
     * @throws Exception if the stage cannot be started.
     */
    @Override
    public void start(final Stage unusedStage) throws Exception {
        controller.setMainApp(this);

        launchPlayerSlect();
    }

    /**
     * Launches the player selection view.
     */
    public void launchPlayerSlect() {
        startStage = new StartStage(controller);
        startStage.show();
    }

    /**
     * Launches the main view of the game.
     */
    public void launchMainView() {
        mainStage = new MainStage(controller);
        mainStage.show();
    }

    /**
     * Launches the final score board view.
     */
    public void launchScoreBoard() {
        this.scoreBoardView = new FinalScoreBoardView(controller);
        scoreBoardView.show();
    }

    /**
     * Closes the main view of the game.
     */
    public void closeMainView() {
        if (!Objects.isNull(mainStage)) {
            mainStage.close();
        }
    }

    /**
     * Refreshes the player interface and the shapes.
     */
    public void refreshAll() {
        refreshPlayerInterface();
        refreshShapes();
    }

    /**
     * Refreshes the player interface.
     */
    public void refreshPlayerInterface() {
        mainStage.refreshPlayerInterface(controller);
    }

    /**
     * Refreshes the shapes.
     */
    public void refreshShapes() {
        mainStage.refreshShapes(controller);
    }

    public void closeFinalScoreBoard() {
        if (!Objects.isNull(scoreBoardView)) {
            scoreBoardView.close();
        }
    }
}
