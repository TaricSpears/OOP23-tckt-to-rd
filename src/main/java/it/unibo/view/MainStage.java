package it.unibo.view;

import java.util.Set;
import java.util.HashSet;

import it.unibo.controller.fillroutecontroller.impl.FillRouteImpl;
import it.unibo.controller.gamecontroller.api.MainController;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainStage extends Stage {

    private PlayerInterface playerInterface;
    final private Screen screen = Screen.getPrimary();
    final private Rectangle2D bounds = screen.getVisualBounds();
    final private BorderPane root;
    final private Scene scene;
    final private Pane pane;
    private Set<Shape> shapeSet = new HashSet<>();

    public MainStage(final MainController controller) {
        this.root = new BorderPane();
        this.scene = new Scene(this.root, this.bounds.getWidth() * 0.9, this.bounds.getHeight() * 0.9);
        this.pane = new Pane();
        this.playerInterface = new PlayerInterface(controller,
                this.scene.getWidth() - this.pane.getMaxWidth(),
                this.scene.getHeight() - this.pane.getMaxWidth());

        final Image image = new Image("/img/Maps/europeMapLabeled.jpg");

        this.root.setCenter(this.pane);

        this.root.setRight(this.playerInterface);

        this.pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));

        this.pane.setMaxWidth(this.scene.getWidth() * 0.8);
        this.pane.setMaxHeight(this.scene.getWidth() * 0.8 * (image.getHeight() / image.getWidth()));
        this.pane.setMinWidth(this.scene.getWidth() * 0.8);
        this.pane.setMinHeight(this.scene.getWidth() * 0.8 * (image.getHeight() / image.getWidth()));

        this.shapeSet = new ShapeSetter(controller)
                .getShapes(this.pane.getMaxWidth(), this.pane.getMaxHeight());
        for (var shape : shapeSet) {
            pane.getChildren().add(shape);
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

    public void refreshShapes(final MainController controller) {
        this.pane.getChildren().clear();
        this.shapeSet = new ShapeSetter(controller)
                .getShapes(this.pane.getMaxWidth(), this.pane.getMaxHeight());
        for (var shape : this.shapeSet) {
            pane.getChildren().add(shape);
        }

        this.root.setCenter(this.pane);
    }
}
