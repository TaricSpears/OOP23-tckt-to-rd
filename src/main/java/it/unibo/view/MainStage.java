package it.unibo.view;

import java.util.List;

import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.route.api.Route;
import it.unibo.view.FillRoute.impl.FillRouteViewImpl;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainStage extends Stage {

    private PlayerInterface playerInterface;
    final private Screen screen = Screen.getPrimary();
    final private Rectangle2D bounds = screen.getVisualBounds();
    final private BorderPane root;
    final private Scene scene;
    final private Pane pane;

    public MainStage(final MainController controller) {
        this.root = new BorderPane();
        this.scene = new Scene(this.root, this.bounds.getWidth() * 0.9, this.bounds.getHeight() * 0.9);
        this.pane = new Pane();
        this.playerInterface = new PlayerInterface(controller,
                this.scene.getWidth() - this.pane.getMaxWidth(),
                this.scene.getHeight() - this.pane.getMaxWidth());

        final Image image = new Image("/img/Maps/europeMapLabeled.jpg");

        this.root.setCenter(this.pane);
        // BorderPane.setMargin(pane, new Insets(10));

        this.root.setRight(this.playerInterface);

        this.pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));

        this.pane.setMaxWidth(this.scene.getWidth() * 0.8);
        this.pane.setMaxHeight(this.scene.getWidth() * 0.8 * (image.getHeight() / image.getWidth()));
        this.pane.setMinWidth(this.scene.getWidth() * 0.8);
        this.pane.setMinHeight(this.scene.getWidth() * 0.8 * (image.getHeight() / image.getWidth()));

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
                    FillRouteImpl fillRoute = new FillRouteImpl(controller.getTurnController().getCurrentPlayer(),
                            route);
                    fillRoute.clickRoute();
                });
                pane.getChildren().add(shape);
            }
        }

        this.setScene(this.scene);
        this.setResizable(false);
    }

    public void refreshPlayerInterface(final MainController controller) {
        this.playerInterface = new PlayerInterface(controller,
                this.scene.getWidth() - this.pane.getMaxWidth(),
                this.scene.getHeight() - this.pane.getMaxWidth());
        this.root.setRight(this.playerInterface);
    }
}
