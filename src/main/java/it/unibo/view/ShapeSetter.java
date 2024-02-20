package it.unibo.view;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.start.GameStart;
import javafx.scene.paint.Color;

/**
 * This class models a ShapeSetter, which returns the set of shapes to draw on
 * map.
 */
public class ShapeSetter {
    private static final int WIDTH = 3;
    private final MainController controller;

    /**
     * Constructor of the class.
     */
    public ShapeSetter() {
        this.controller = GameStart.CONTROLLER;

    }

    /**
     * @param paneWidth  the width of the pane.
     * @param paneHeight the height of the pane.
     * @return a set of shapes representing the regions in the map.
     */
    public Set<Shape> getShapes(final double paneWidth, final double paneHeight) {

        final Set<Region> regionSet = controller.getGameController().getRegions();
        final Set<Shape> shapeSet = new LinkedHashSet<>();
        final boolean disabled = !(this.controller.getPhaseController().isMidPhase());

        for (final var region : regionSet) {
            final Shape shape = new Shape(region.getXCenter() * paneWidth, region.getYCenter() * paneHeight,
                    region.getWidth() * paneWidth, region.getLength() * paneHeight, region.getId());
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
                final FillRoute fillRoute = new FillRouteImpl(controller.getTurnController().getCurrentPlayer(), region,
                        controller);
                if (fillRoute.clickRoute()) {
                    controller.getPhaseController().switchPhase();
                    controller.getGameController().refreshView();
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
