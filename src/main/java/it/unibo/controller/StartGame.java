package it.unibo.controller;

import it.unibo.controller.gamecontroller.impl.StartControllerImpl;

public class StartGame {
    public static void main(String[] args) {
        new StartControllerImpl().startView();
    }
}
