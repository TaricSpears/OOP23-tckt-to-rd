package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main view of the game, extends {@link Application}.
 */
public class MainView extends Application {

    private static MainController controller = GameStart.CONTROLLER;
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
     * @return the main stage of the game.
     */
    public MainStage getMainStage() {
        return this.mainStage;
    }

    /**
     * @return the final score board view.
     */
    public FinalScoreBoardView getScoreBoardView() {
        return this.scoreBoardView;
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

}
