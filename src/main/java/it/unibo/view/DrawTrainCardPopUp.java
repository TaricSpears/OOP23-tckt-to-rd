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

    /**
     * Constructor for the class.
     * 
     * @param color the color of the card drawn.
     */
    public DrawTrainCardPopUp(Color color) {

        Pane pane = new Pane();
        Image image;

        Map<Color, String> colorImageMap = new HashMap<>();

        colorImageMap.put(Color.BLACK, "/img/Cards/BlackCard.jpg");
        colorImageMap.put(Color.BLUE, "/img/Cards/BlueCard.jpg");
        colorImageMap.put(Color.GREEN, "/img/Cards/GreenCard.jpg");
        colorImageMap.put(Color.RED, "/img/Cards/RedCard.jpg");
        colorImageMap.put(Color.WHITE, "/img/Cards/WhiteCard.jpg");
        colorImageMap.put(Color.YELLOW, "/img/Cards/YellowCard.jpg");
        colorImageMap.put(Color.ORANGE, "/img/Cards/OrangeCard.jpg");
        colorImageMap.put(Color.MAGENTA, "/img/Cards/PurpleCard.jpg");
        colorImageMap.put(Color.DARK_GRAY, "/img/Cards/JollyCard.jpg");

        image = new Image(colorImageMap.get(color));
        pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));

        Stage popupStage = new Stage();
        popupStage.initOwner(null);
        popupStage.setScene(new Scene(pane, 300, 200));
        popupStage.setResizable(false);
        popupStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> popupStage.close());
        delay.play();
    }

}
