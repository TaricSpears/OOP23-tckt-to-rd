package it.unibo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
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

        var temp = new ArrayList<>(this.colorImageMap.entrySet());
        for (int i = 0; i < temp.size(); i += 2) {
            final HBox cardBox = new HBox();

            final ImageView card1 = new ImageView(new Image(temp.get(i).getValue()));

            final VBox cardBox1 = new VBox(card1,
                    new Text(controller.getTurnController().getCurrentPlayer().getTrainCards()
                            .get(temp.get(i).getKey())
                            .toString()));

            card1.setFitHeight(50);
            card1.setFitWidth(50 * 1.56);
            cardBox.getChildren().add(cardBox1);

            if (i != temp.size() - 1) {
                final ImageView card2 = new ImageView(new Image(temp.get(i + 1).getValue()));
                final VBox cardBox2 = new VBox(card2,
                        new Text(controller.getTurnController().getCurrentPlayer().getTrainCards()
                                .get(temp.get(i + 1).getKey())
                                .toString()));
                card2.setFitHeight(50);
                card2.setFitWidth(50 * 1.56);
                cardBox.getChildren().add(cardBox2);
            }
            cardBox.setSpacing(1);
            cardBox.setPadding(new Insets(1));

            this.getChildren().add(cardBox);
        }

        this.setBorder(new Border(new BorderStroke(
            javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
    }

}
