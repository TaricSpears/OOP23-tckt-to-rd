package it.unibo.objectivegeneration;

import org.junit.jupiter.api.Test;

import it.unibo.commons.Pair;
import it.unibo.commons.TestDataPreparation;
import it.unibo.model.city.api.City;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.objectivegeneration.api.ObjectiveGenerator;
import it.unibo.model.objectivegeneration.impl.ObjectiveGeneratorImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestObjectiveGeneration {

    private static final double MIN_SCORE = 5.0;
    private final GamePrep gamePrep = TestDataPreparation.gamePrep();

    @Test
    void testObjectiveGeneration() {
        final ObjectiveGenerator objectiveGenerator = new ObjectiveGeneratorImpl(gamePrep.getGraph());

        final Pair<City, City> obj1 = objectiveGenerator.generateObjective();
        assertNotEquals(obj1.first(), obj1.second());
        assertTrue(objectiveGenerator.calculateScore(obj1) >= MIN_SCORE);

        final Pair<City, City> obj2 = objectiveGenerator.generateObjective();
        assertNotEquals(obj2.first(), obj2.second());
        assertTrue(objectiveGenerator.calculateScore(obj2) >= MIN_SCORE);
    }
}
