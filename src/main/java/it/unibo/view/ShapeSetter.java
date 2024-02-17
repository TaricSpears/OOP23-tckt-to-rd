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
        final boolean disabled = !(this.controller.getGameController().getPhaseController().isMidPhase());

        for (var region : regionSet) {
            final Shape shape = new Shape(region.getXCenter() * paneWidth, region.getYCenter() * paneHeight,
                    region.getWidth() * paneWidth, region.getLength() * paneHeight, region.getId());
            final FillRoute fillRoute = new FillRouteImpl(controller.getTurnController().getCurrentPlayer(), region,
                    controller);
            shape.setTilt(360.0 - Math.toDegrees(region.getAngle()));
            shape.setStrokeWidth(3.0);
            shape.setFill(
                    Color.rgb(region.getColor().getRed(), region.getColor().getGreen(), region.getColor().getBlue()));
            shape.setOnMouseClicked(event -> {
                fillRoute.clickRoute();
                java.awt.Color playerColor = controller.getTurnController().getCurrentPlayer().getColor();
                shape.setStroke(Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
            });
            shape.setDisable(disabled);
            shapeSet.add(shape);
        }

        return shapeSet;

    }

}
