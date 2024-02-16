package it.unibo.view;

import java.util.List;
import java.util.Set;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.route.api.Route;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainStage extends Stage {

    public MainStage(final StartController controller) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        System.out.println(bounds.getWidth() + " " + bounds.getHeight());
        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root, bounds.getWidth() * 0.9, bounds.getHeight() * 0.9);
        final Pane pane = new Pane();
        final Button endGame = new Button("End Game");
        VBox vBox = new VBox(endGame);

        final Image image = new Image("/img/Maps/europeMapLabeled.jpg");

        root.setCenter(pane);
        // BorderPane.setMargin(pane, new Insets(10));

        endGame.setOnAction(event -> {
            controller.getMainController().endGame();
        });
        pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));

        root.setRight(vBox);

        pane.setMaxWidth(scene.getWidth() * 0.8);
        pane.setMaxHeight(scene.getWidth() * 0.8 * (image.getHeight() / image.getWidth()));

        final List<Route> routeList = new RouteReaderController().read();
        Carriage carriage;

        for (var route : routeList) {
            var iterator = route.getRailUnits().iterator();
            while (iterator.hasNext()) {
                carriage = iterator.next();
                final Shape shape = new Shape(
                        carriage.xCoord() * pane.getMaxWidth() - (27 * bounds.getWidth() / 2560),
                        carriage.yCoord() * pane.getMaxHeight() - (8 * bounds.getHeight() / 1440),
                        carriage.width() * pane.getMaxWidth(),
                        carriage.length() * pane.getMaxWidth());
                shape.setTilt(360.0 - Math.toDegrees(carriage.angle()));
                shape.setFill("red");
                pane.getChildren().add(shape);
            }
        }

        vBox.setMinSize(scene.getWidth() - pane.getMaxWidth(), scene.getHeight() - pane.getMaxWidth());

        this.setScene(scene);
        this.setResizable(false);
    }
}
