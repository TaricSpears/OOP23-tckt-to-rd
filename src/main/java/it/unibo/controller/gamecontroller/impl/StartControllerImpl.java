package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;
import it.unibo.commons.Pair;

import javafx.application.Application;

import java.awt.Color;
import java.util.Set;

public class StartControllerImpl implements StartController {

    @Override
    public void startView() {
        Application.launch(MainView.class);
    }

    @Override
    public void closeView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeView'");
    }

    @Override
    public void startGame(final Set<Pair<String, Color>> players) {
        final GamePrep gamePrep = new GamePrep();
        // gamePrep.prepGame(players, null);
    }

    @Override
    public MainController getMainController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMainController'");
    }

}
