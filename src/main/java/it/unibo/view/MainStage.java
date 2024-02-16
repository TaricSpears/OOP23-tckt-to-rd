package it.unibo.view;

import it.unibo.controller.gamecontroller.api.StartController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainStage extends Stage {

    public MainStage(final StartController controller) {

        final BorderPane root = new BorderPane();

        final Image image = new Image("/img/Maps/europeMapLabeled.jpg");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        final Pane pane = new Pane();
        root.setCenter(pane);
        BorderPane.setMargin(pane, new Insets(10));

        pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));

        final Button endGame = new Button("End Game");
        endGame.setOnAction(event -> {
            controller.getMainController().endGame();
        });

        HBox hBox = new HBox(endGame);
        root.setRight(hBox);

        pane.setMinWidth(bounds.getWidth() * 0.8);
        pane.setMinHeight(bounds.getHeight() * 0.8);

        pane.setBorder(
                new Border(
                        new BorderStroke(Paint.valueOf("red"), BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));

        final Button cityButton = new Button("City");
        pane.getChildren().add(cityButton);

        cityButton.relocate(pane.getWidth() / 2, pane.getHeight() / 2);
        cityButton.setDisable(false);

        final GridPane gridPane = new GridPane();
        hBox.getChildren().add(gridPane);

        hBox.setMinSize(bounds.getWidth() * 0.3, bounds.getHeight() * 0.3);

        this.setScene(new Scene(root, bounds.getWidth() * 0.9, bounds.getHeight() * 0.9));
        this.setResizable(false);
    }
}
