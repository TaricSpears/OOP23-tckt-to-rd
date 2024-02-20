package it.unibo.view;

import java.util.ArrayList;
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

/**
 * The class that represents the box of the cards of the player, extends
 * {@link VBox}.
 * 
 * It contains all the cards of the player.
 */
public class CardBox extends VBox {

    private static final double FITHEIGHT = 50;
    private static final double FITWIDTH = 50 * 1.56;

    private final Map<Color, String> colorImageMap = new LinkedHashMap<>();

    /**
     * Creates the box of player's card.
     * 
     * @param controller      the mainController of the game.
     * @param playerInterface the interface where this box will be fit.
     */
    public CardBox(final MainController controller, final PlayerInterface playerInterface) {

        colorImageMap.put(Color.BLACK, "/img/Cards/BlackCard.jpg");
        colorImageMap.put(Color.BLUE, "/img/Cards/BlueCard.jpg");
        colorImageMap.put(Color.GREEN, "/img/Cards/GreenCard.jpg");
        colorImageMap.put(Color.RED, "/img/Cards/RedCard.jpg");
        colorImageMap.put(Color.WHITE, "/img/Cards/WhiteCard.jpg");
        colorImageMap.put(Color.YELLOW, "/img/Cards/YellowCard.jpg");
        colorImageMap.put(Color.ORANGE, "/img/Cards/OrangeCard.jpg");
        colorImageMap.put(Color.MAGENTA, "/img/Cards/PurpleCard.jpg");
        colorImageMap.put(Color.DARK_GRAY, "/img/Cards/JollyCard.jpg");

        var temp = new ArrayList<>(this.colorImageMap.entrySet());
        for (int i = 0; i < temp.size(); i += 2) {
            final HBox cardBox = new HBox();

            final ImageView card1 = new ImageView(new Image(temp.get(i).getValue()));

            final VBox cardBox1 = new VBox(card1,
                    new Text(controller.getTurnController().getCurrentPlayer().getTrainCards()
                            .get(temp.get(i).getKey())
                            .toString()));

            card1.setFitHeight(FITHEIGHT);
            card1.setFitWidth(FITWIDTH);
            cardBox.getChildren().add(cardBox1);

            if (i != temp.size() - 1) {
                final ImageView card2 = new ImageView(new Image(temp.get(i + 1).getValue()));
                final VBox cardBox2 = new VBox(card2,
                        new Text(controller.getTurnController().getCurrentPlayer().getTrainCards()
                                .get(temp.get(i + 1).getKey())
                                .toString()));
                card2.setFitHeight(FITHEIGHT);
                card2.setFitWidth(FITWIDTH);
                cardBox.getChildren().add(cardBox2);
            }
            cardBox.setSpacing(1);
            cardBox.setPadding(new Insets(1));

            this.getChildren().add(cardBox);
        }

    }

}
