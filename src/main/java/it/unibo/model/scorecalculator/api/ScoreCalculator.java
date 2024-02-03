package it.unibo.model.scorecalculator.api;

import java.util.List;
import java.util.Set;

import it.unibo.model.player.api.Player;
import it.unibo.commons.Pair;

public interface ScoreCalculator {

    List<Pair<Player, Integer>> getScoreBoard(Set<Player> players);
}
