package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The main view of the game, extends {@link Application}.
 */
public class MainView extends Application {

    private static MainController controller = GameStart.CONTROLLER;
    private MainStage mainStage;
    private FinalScoreBoardView scoreBoardView;

    /**
     * Simple constructor of the main view.
     */
    public MainView() {
        this.mainStage = null;
        this.scoreBoardView = null;
    }

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
     * Refreshes the main stage of the game.
     */
    public void refreshAllMainStage() {
        if (Objects.nonNull(mainStage)) {
            mainStage.refreshAll(controller);
        }
    }

    /**
     * Refreshes the player interface.
     */
    public void refreshPlayerInterface() {
        if (Objects.nonNull(mainStage)) {
            mainStage.refreshPlayerInterface(controller);
        }
    }

    /**
     * Closes the main stage of the game.
     */
    public void closeMainStage() {
        if (Objects.nonNull(mainStage)) {
            mainStage.closeStage();
        }
    }

    /**
     * Launches the player selection view.
     */
    public void launchPlayerSlect() {
        final StartStage startStage = new StartStage(controller);
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
     * Closes the final scoreboard of the game.
     */
    public void closeScoreBoard() {
        System.out.println("Close ScoreBoard");
        if (!Objects.isNull(scoreBoardView)) {
            this.scoreBoardView.closeStage();
        }
    }

}
