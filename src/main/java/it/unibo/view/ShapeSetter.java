package it.unibo.view;

import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.commons.Region;
import it.unibo.controller.gamecontroller.api.MainController;
import javafx.scene.paint.Color;

public class ShapeSetter {
    private final MainController controller;

    public ShapeSetter(final MainController controller){
        this.controller = controller;
    }

    public Set<Shape> getShapes(final double paneWidth, final double paneHeight) {
        final Set<Region> regionSet = controller.getGameController().getRegions();
        final Set<Shape> shapeSet = new LinkedHashSet<>();

        for(var region: regionSet){
            final Shape shape = new Shape(region.getXCenter()*paneWidth, region.getYCenter()*paneHeight,
                 region.getWidth()*paneWidth, region.getLength()*paneHeight, region.getId());
            shape.setTilt(360.0 - Math.toDegrees(region.getAngle()));
            shape.setStrokeWidth(3.0);
            shape.setFill(
                    Color.rgb(region.getColor().getRed(), region.getColor().getGreen(), region.getColor().getBlue()));
            shape.setOnMouseClicked(event -> {
                java.awt.Color playerColor = controller.getTurnController().getCurrentPlayer().getColor();
                shape.setStroke(Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
            });
            shapeSet.add(shape);
        }

        return shapeSet;

    }

}
