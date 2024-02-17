package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.controller.turncontroller.impl.TurnControllerImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

import javafx.application.Application;

public class MainControllerImpl implements MainController {

    final private GamePrep gamePrep = new GamePrep();
    private MainView view;
    final private GameController gameController = new GameControllerImpl(this);
    private TurnController turnController;

    @Override
    public void startView() {
        Application.launch(MainView.class);
    }

    @Override
    public void startGame() {
        gamePrep.prepGame(gameController.getTempPlayers(), new RouteReaderController().read());
        turnController = new TurnControllerImpl(gamePrep.getPlayers());
        view.launchMainView();
    }

    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    @Override
    public GamePrep getGameInstance() {
        return this.gamePrep;
    }

    @Override
    public void setMainApp(final MainView app) {
        this.view = app;
        gameController.addView(app);
    }

    @Override
    public TurnController getTurnController() {
        return this.turnController;
    }
}
