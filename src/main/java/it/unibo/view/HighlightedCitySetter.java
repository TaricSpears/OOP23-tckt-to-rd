package it.unibo.view;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

/*
 * This class models a CitySetter, which returns the set of buttons 
 * highlighting completed cities to draw on map
 */
public class HighlightedCitySetter {
    private final MainController controller;

    public HighlightedCitySetter(final MainController controller) {
        this.controller = controller;
    }

    public Set<Button> getCities(final double paneWidth, final double paneHeight) {
        final Set<Pair<Double, Double>> citySet = controller.getGameController()
                .getPlayerCities(controller.getTurnController().getCurrentPlayer());
        final Set<Button> buttonSet = new LinkedHashSet<>();
        final Double buttonRadius = controller.getGameController().getCityRadius() * paneWidth;

        for (var city : citySet) {
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
