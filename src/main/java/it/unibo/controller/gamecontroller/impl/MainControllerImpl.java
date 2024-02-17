package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.drawcontroller.api.DrawController;
import it.unibo.controller.drawcontroller.impl.DrawControllerImpl;
import it.unibo.controller.gamecontroller.api.GameController;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.controller.turncontroller.impl.TurnControllerImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.view.MainView;

import javafx.application.Application;

public class MainControllerImpl implements MainController {

    final private GamePrep gamePrep = new GamePrep();
    private MainView view;
    final private GameController gameController = new GameControllerImpl(this);
    private TurnController turnController;

    private DrawController drawController = new DrawControllerImpl();

    @Override
    public TrainCard handleDrawTrainCard() {
        TrainCard card = drawController.drawTrainCard();
        this.turnController.getCurrentPlayer().addTrainCard(card);
        return card;
    }

    @Override
    public ObjectiveCard handleDrawObjectiveCard() {
        Boolean drawn = false;
        ObjectiveCard card;

        do {
            card = drawController.drawObjectiveCard();
            drawn = this.turnController.getCurrentPlayer().addObjectiveCard(card);
        } while (!drawn);

        return card;
    }

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
