package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

import javafx.application.Application;

public class StartControllerImpl implements StartController {

    final private GamePrep gamePrep = new GamePrep();
    private MainView view;
    final private MainController mainController = new MainControllerImpl(this);

    @Override
    public void startView() {
        Application.launch(MainView.class);
    }

    @Override
    public void startGame() {
        gamePrep.prepGame(mainController.getTempPlayers(), new RouteReaderController().read());
        view.launchMainView();
    }

    @Override
    public MainController getMainController() {
        return this.mainController;
    }

    @Override
    public GamePrep getGameInstance() {
        return this.gamePrep;
    }

    @Override
    public void setMainApp(final MainView app) {
        this.view = app;
        mainController.addView(app);
    }

}
