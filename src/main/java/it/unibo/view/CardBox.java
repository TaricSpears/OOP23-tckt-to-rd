package it.unibo.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.awt.Color;
import javafx.scene.text.Text;

public class CardBox extends VBox {

    final private Map<Color, String> colorImageMap = new LinkedHashMap<>();

    public CardBox(final MainController controller, final PlayerInterface playerInterface) {

        colorImageMap.put(Color.BLACK, "/img/Cards/BlackCard.jpg");
        colorImageMap.put(Color.BLUE, "/img/Cards/BlueCard.jpg");
        colorImageMap.put(Color.GREEN, "/img/Cards/GreenCard.jpg");
        colorImageMap.put(Color.RED, "/img/Cards/RedCard.jpg");
        colorImageMap.put(Color.WHITE, "/img/Cards/WhiteCard.jpg");
        colorImageMap.put(Color.YELLOW, "/img/Cards/YellowCard.jpg");
        colorImageMap.put(Color.ORANGE, "/img/Cards/OrangeCard.jpg");
        colorImageMap.put(Color.PINK, "/img/Cards/PurpleCard.jpg");
        colorImageMap.put(Color.DARK_GRAY, "/img/Cards/JollyCard.jpg");

        for (final var image : this.colorImageMap.entrySet()) {
            final HBox cardBox = new HBox();
            final ImageView card = new ImageView(new Image(image.getValue()));
            card.setFitHeight(50);
            card.setFitWidth(50 * 1.56);
            cardBox.setSpacing(1);
            cardBox.setPadding(new Insets(1));
            cardBox.getChildren().addAll(card,
                    new Text(controller.getTurnController().getCurrentPlayer().getTrainCards().get(image.getKey())
                            .toString()));

            this.getChildren().add(cardBox);

        }

    }

}
