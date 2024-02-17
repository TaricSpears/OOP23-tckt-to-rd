package it.unibo.model.scorecalculator.api;

import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.commons.Pair;

public interface ScoreCalculator {

    List<Pair<String, Integer>> getScoreBoard(List<Player> players);
}
