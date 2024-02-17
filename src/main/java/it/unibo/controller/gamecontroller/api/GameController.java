package it.unibo.controller.gamecontroller.api;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;
import it.unibo.view.MainView;

/**
 * It models the controller that allows to comunicate with the model
 */
public interface GameController {

    /**
     * Ends the turn of the current player switching to the next one
     */
    void endTurn();

    /**
     * Calculates the score of each player via {@link ScoreCalculatorImpl}
     * 
     * @return a list of the player name and his score
     */
    List<Pair<String, Integer>> getScore();

    /**
     * Starts a new game
     */
    void newGame();

    /**
     * 
     * @param pair a pair of the player name and his color
     * @return if the player can be added or not (if is already present, somone has
     *         the same color or name)
     */
    boolean addPlayer(Pair<String, java.awt.Color> pair);

    /**
     * 
     * @return if the main view can shown (player list and map graph are been
     *         created)
     */
    boolean canStart();

    /**
     * 
     * @return a list of temporary players (bofore the gamePrep creates the Player
     *         object)
     */
    List<Pair<String, java.awt.Color>> getTempPlayers();

    /**
     * ends the game, close the main view and launches the final score board
     */
    void endGame();

    /**
     * Adds a ref to the main view to the controller
     * 
     * @param view the main view that controls the other ones
     */
    void addView(MainView view);

    /**
     * @return the drawn train card
     */
    TrainCard handleDrawTrainCard();

    /**
     * @return the drawn objective card
     */
    ObjectiveCard handleDrawObjectiveCard();
}
