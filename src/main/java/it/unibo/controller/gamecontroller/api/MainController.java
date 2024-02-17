package it.unibo.controller.gamecontroller.api;

import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

public interface MainController {
    void startView();

    void startGame();

    GameController getGameController();

    TurnController getTurnController();

    GamePrep getGameInstance();

    void setMainApp(MainView app);

    TrainCard handleDrawTrainCard();

    ObjectiveCard handleDrawObjectiveCard();
}
