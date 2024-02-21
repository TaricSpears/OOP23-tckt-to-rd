package it.unibo.view.entitysetter.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import it.unibo.view.Shape;
import it.unibo.view.entitysetter.api.EntitySetter;
import javafx.scene.paint.Color;

/**
 * This class models a ShapeSetter, which returns the set of shapes to draw on
 * map.
 */
public class ShapeSetter implements EntitySetter<Shape> {
    private static final int WIDTH = 3;
    private final MainController controller;

    /**
     * Constructor of the class.
     */
    public ShapeSetter() {
        this.controller = GameStart.CONTROLLER;
    }

    /**
     * Get the shapes to draw.
     * 
     * @param width  the width of the pane.
     * @param height the height of the pane.
     * @return a set of shapes representing the regions in the map.
     */
    @Override
    public Set<Shape> getEntities(final double width, final double height) {

        final Set<Region> regionSet = this.controller.getGameController().getRegions();
        final Set<Shape> shapeSet = new LinkedHashSet<>();
        final boolean disabled = !(this.controller.getPhaseController().isMidPhase());

        for (final var region : regionSet) {
            final Shape shape = new Shape(region.getXCenter() * width, region.getYCenter() * height,
                    region.getWidth() * width, region.getLength() * height, region.getId());
            shape.setTilt(360.0 - Math.toDegrees(region.getAngle()));
            if (region.getPlayerColor().isPresent()) {
                final java.awt.Color strokeColor = region.getPlayerColor().get();
                shape.setStroke(Color.rgb(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue()));
            }
            shape.setStrokeWidth(WIDTH);
            shape.setFill(
                    Color.rgb(region.getDefaultColor().getRed(), region.getDefaultColor().getGreen(),
                            region.getDefaultColor().getBlue()));
            shape.setOnMouseClicked(event -> {
                final FillRoute fillRoute = new FillRouteImpl(
                        this.controller.getTurnController().getCurrentPlayer(), region,
                        this.controller);
                if (fillRoute.clickRoute()) {
                    this.controller.getPhaseController().switchPhase();
                    this.controller.getGameController().refreshView();
                }
            });
            shape.setDisable(disabled);
            if (region.getPlayerColor().isPresent()) {
                shape.setDisable(true);
            }
            shapeSet.add(shape);
        }

        return shapeSet;

    }

}
