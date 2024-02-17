package it.unibo.controller;

import it.unibo.controller.gamecontroller.impl.MainControllerImpl;

public class StartGame {
    public static void main(String[] args) {
        new MainControllerImpl().startView();
    }
}
