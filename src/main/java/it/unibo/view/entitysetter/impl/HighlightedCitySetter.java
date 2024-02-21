package it.unibo.view.entitysetter.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import javafx.scene.control.Button;
import it.unibo.view.entitysetter.api.EntitySetter;
import javafx.scene.shape.Circle;

/**
 * This class models a CitySetter, which returns the set of buttons
 * highlighting completed cities to draw on map.
 */
public class HighlightedCitySetter implements EntitySetter<Button> {
    private final MainController controller;

    /**
     * Constructor of the class.
     */
    public HighlightedCitySetter() {
        this.controller = GameStart.CONTROLLER;
    }

    /**
     * Highlight the cities of the current player.
     * 
     * @param paneWidth  the width of the pane.
     * @param paneHeight the height of the pane.
     * @return a set of buttons representing the cities in the map.
     */
    @Override
    public Set<Button> getEntities(final double paneWidth, final double paneHeight) {
        final Set<Pair<Double, Double>> citySet = this.controller.getGameController()
                .getPlayerCities(this.controller.getTurnController().getCurrentPlayer());
        final Set<Button> buttonSet = new LinkedHashSet<>();
        final Double buttonRadius = this.controller.getGameController().getCityRadius() * paneWidth;

        for (final var city : citySet) {
            final Button button = new Button();
            final Circle circle = new Circle(buttonRadius);
            button.setShape(circle);
            button.setMinSize(2 * buttonRadius, 2 * buttonRadius);
            button.setMaxSize(2 * buttonRadius, 2 * buttonRadius);
            button.relocate(city.first() * paneWidth, city.second() * paneHeight);
            buttonSet.add(button);
        }

        return buttonSet;
    }

}
