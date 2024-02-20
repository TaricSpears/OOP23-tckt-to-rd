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
 * It models the main controller that allows access to other controllers.
 */
public class MainControllerImpl implements MainController {

    private final GameControllerImpl gameController = new GameControllerImpl(this);
    private GamePrep gamePrep;
    private MainView view;
    private DrawController drawController;
    private PhaseController phaseController;
    private TurnController turnController;

    /**
     * Constructor for the main controller.
     */
    public MainControllerImpl() {
        this.gamePrep = new GamePrep();
        this.drawController = new DrawControllerImpl();
        this.view = null;
    }

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
        this.gamePrep = new GamePrep();
        this.phaseController = new PhaseControllerImpl();
        this.drawController = new DrawControllerImpl();
        this.gamePrep.prepGame(this.gameController.getTempPlayers(), new RouteReaderController().read());
        this.turnController = new TurnControllerImpl(this.gamePrep.getPlayers());

        this.view.launchMainView();
        this.gamePrep.getPlayers()
                .forEach(player -> player.addObjectiveCard(this.drawController.drawObjectiveCard(
                        this.gamePrep.getGraph())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getGameController() {
        return this.gameController.clone();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public DrawController getDrawController() {
        return this.drawController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhaseController getPhaseController() {
        return this.phaseController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPhaseController(final PhaseController phaseController) {
        this.phaseController = phaseController;
    }
}
