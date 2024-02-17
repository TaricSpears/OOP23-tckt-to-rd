package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObjectiveBox extends VBox {

    public ObjectiveBox(final MainController controller) {
        // player.getObjectiveCards().stream()
        // .map(x -> this.getChildren()
        // .add(new Text(x.getCities().first() + " - " + x.getCities().second() + " ("
        // + player.getCarriageNum() + ")")));

        this.getChildren().add(new Text(controller.getTurnController().getCurrentPlayer().getName()));
    }

}
