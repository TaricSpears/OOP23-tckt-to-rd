package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;
import it.unibo.commons.Pair;

import java.awt.Color;
import java.util.List;

import javafx.application.Application;

public class StartControllerImpl implements StartController {

    final private MainController mainController;

    public StartControllerImpl() {
        this.mainController = new MainControllerImpl();
    }

    @Override
    public void startView() {
        Application.launch(MainView.class);
    }

    @Override
    public void closeView() {

    }

    @Override
    public void startGame(final List<Pair<String, Color>> players) {
        final GamePrep gamePrep = new GamePrep();
        gamePrep.prepGame(players, new RouteReaderController().read());
    }

    @Override
    public MainController getMainController() {
        return this.mainController;
    }

}
