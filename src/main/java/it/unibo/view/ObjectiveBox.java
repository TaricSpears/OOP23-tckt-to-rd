package it.unibo.view;

import it.unibo.model.player.api.Player;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObjectiveBox extends VBox {

    public ObjectiveBox(final Player player) {
        // player.getObjectiveCards().stream()
        // .map(x -> this.getChildren()
        // .add(new Text(x.getCities().first() + " - " + x.getCities().second() + " ("
        // + player.getCarriageNum() + ")")));

        for (int i = 0; i < 5; i++) {
            this.getChildren().add(new Text("Prova " + i));
        }
    }

}
