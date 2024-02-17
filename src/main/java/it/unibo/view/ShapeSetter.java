package it.unibo.view;

import java.util.Set;

public class ShapeSetter {

    public Set<Shape> getShapes() {
        final List<Route> routeList = new RouteReaderController().read();
        Carriage carriage;

        for (var route : routeList) {
            var iterator = route.getRailUnits().iterator();
            while (iterator.hasNext()) {
                carriage = iterator.next();
                final Shape shape = new Shape(
                        carriage.xCoord() * pane.getMaxWidth(),
                        carriage.yCoord() * pane.getMaxHeight(),
                        carriage.width() * pane.getMaxWidth(),
                        carriage.length() * pane.getMaxWidth());
                shape.setTilt(360.0 - Math.toDegrees(carriage.angle()));
                shape.setStrokeWidth(3.0);
                shape.setFill(
                        Color.rgb(route.getColor().getRed(), route.getColor().getGreen(), route.getColor().getBlue()));

                shape.setOnMouseClicked(event -> {
                    java.awt.Color playerColor = controller.getGameController().getCurrentPlayer().getColor();
                    shape.setStroke(Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
                });
                pane.getChildren().add(shape);
            }
        }
    }

}
