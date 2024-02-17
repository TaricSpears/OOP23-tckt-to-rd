package it.unibo.start;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;

public class GameStart {

    public static MainController controller = new MainControllerImpl();

    public static void main(String[] args) {
        controller.startView();
    }
}
