package it.unibo.view;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Region;
import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import javafx.scene.paint.Color;

public class ShapeSetter {
    private final MainController controller;

    public ShapeSetter(final MainController controller) {
        this.controller = controller;

    }

    public Set<Shape> getShapes(final double paneWidth, final double paneHeight) {
        final Set<Region> regionSet = controller.getGameController().getRegions();
        final Set<Shape> shapeSet = new LinkedHashSet<>();
        final boolean disabled = !(this.controller.getPhaseController().isMidPhase());

        for (var region : regionSet) {
            final Shape shape = new Shape(region.getXCenter() * paneWidth, region.getYCenter() * paneHeight,
                    region.getWidth() * paneWidth, region.getLength() * paneHeight, region.getId());
            shape.setTilt(360.0 - Math.toDegrees(region.getAngle()));
            if (region.getPlayerColor().isPresent()) {
                final java.awt.Color strokeColor = region.getPlayerColor().get();
                shape.setStroke(Color.rgb(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue()));
            }
            shape.setStrokeWidth(3.0);
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
            if (region.getPlayerColor().isPresent())
                shape.setDisable(true);
            shapeSet.add(shape);
        }

        return shapeSet;

    }

}
