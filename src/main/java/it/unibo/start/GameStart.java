package it.unibo.start;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.controller.gamecontroller.impl.StartControllerImpl;

public class GameStart {

    public static StartController controller = new StartControllerImpl();

    public static void main(String[] args) {
        controller.startView();
    }
}
