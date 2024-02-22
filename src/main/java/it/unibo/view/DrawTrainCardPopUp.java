package it.unibo.view;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class opens a pop-up that shows the card drawn.
 */
public class DrawTrainCardPopUp {

    private static final double WIDTH = 300;
    private static final double HEIGHT = 200;
    private static final double DURATION = 2;

    /**
     * Constructor for the class.
     * 
     * @param color the color of the card drawn.
     */
    public DrawTrainCardPopUp(final Color color) {

        final Pane pane = new Pane();
        final Image image;

        final Map<Color, String> colorImageMap = new HashMap<>();

        colorImageMap.put(Color.BLACK, "img/Cards/BlackCard.jpeg");
        colorImageMap.put(Color.BLUE, "img/Cards/BlueCard.jpeg");
        colorImageMap.put(Color.GREEN, "img/Cards/GreenCard.jpeg");
        colorImageMap.put(Color.RED, "img/Cards/RedCard.jpeg");
        colorImageMap.put(Color.WHITE, "img/Cards/WhiteCard.jpeg");
        colorImageMap.put(Color.YELLOW, "img/Cards/YellowCard.jpeg");
        colorImageMap.put(Color.ORANGE, "img/Cards/OrangeCard.jpeg");
        colorImageMap.put(Color.MAGENTA, "img/Cards/PurpleCard.jpeg");
        colorImageMap.put(Color.DARK_GRAY, "img/Cards/JollyCard.jpeg");

        image = new Image(ClassLoader.getSystemResourceAsStream(colorImageMap.get(color)));
        pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        final Stage popupStage = new Stage();
        popupStage.initOwner(null);
        popupStage.setScene(new Scene(pane, WIDTH, HEIGHT));
        popupStage.setResizable(false);
        popupStage.show();

        final PauseTransition delay = new PauseTransition(Duration.seconds(DURATION));
        delay.setOnFinished(event -> popupStage.close());
        delay.play();
    }

}
