package it.unibo.start;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;

/**
 * The class that starts the game.
 */
public class GameStart {

    public static MainController controller = new MainControllerImpl();

    /**
     * The main method that starts the game.
     * 
     * @param args
     */
    public static void main(String[] args) {
        controller.startView();
    }
}
