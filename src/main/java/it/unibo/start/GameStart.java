package it.unibo.start;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;

/**
 * The class that starts the game.
 */
public final class GameStart {

    /**
     * The main controller of the game.
     */
    public static final MainController CONTROLLER = new MainControllerImpl();

    /**
     * Empty constructor of the class.
     */
    private GameStart() {

    }

    /**
     * The main method that starts the game.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        CONTROLLER.startView();
    }
}
