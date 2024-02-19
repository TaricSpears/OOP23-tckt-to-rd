package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.drawcontroller.api.DrawController;
import it.unibo.controller.drawcontroller.impl.DrawControllerImpl;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.controller.phasecontroller.impl.PhaseControllerImpl;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.controller.turncontroller.impl.TurnControllerImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

import javafx.application.Application;

/**
 * Implementation of {@link MainController}.
 * It models the main controller that allows access to other controllers
 */
public class MainControllerImpl implements MainController {

    final private GamePrep gamePrep = new GamePrep();
    private MainView view;
    final private GameController gameController = new GameControllerImpl(this);
    private DrawController drawController = new DrawControllerImpl();
    private PhaseController phaseController = new PhaseControllerImpl();
    private TurnController turnController;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startView() {
        Application.launch(MainView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        gamePrep.prepGame(gameController.getTempPlayers(), new RouteReaderController().read());
        turnController = new TurnControllerImpl(gamePrep.getPlayers());
        view.launchMainView();
        gamePrep.getPlayers()
                .forEach(player -> player.addObjectiveCard(drawController.drawObjectiveCard(gamePrep.getGraph())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GamePrep getGameInstance() {
        return this.gamePrep;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMainApp(final MainView app) {
        this.view = app;
        gameController.addView(app);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnController getTurnController() {
        return this.turnController;
    }

    public DrawController getDrawController() {
        return this.drawController;
    }

    public PhaseController getPhaseController() {
        return this.phaseController;
    }

    public void setPhaseController(final PhaseController phaseController) {
        this.phaseController = phaseController;
    }
}
