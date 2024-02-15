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
    final private GamePrep gamePrep = new GamePrep();

    public StartControllerImpl() {
        this.mainController = new MainControllerImpl(this);
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
        gamePrep.prepGame(players, new RouteReaderController().read());
    }

    @Override
    public MainController getMainController() {
        return this.mainController;
    }

    @Override
    public GamePrep getGameInstance() {
        return this.gamePrep;
    }

}
