package it.unibo.view.FillRoute.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Implementation of the {@link CombatView} interface.
 * Represents the frame for the combat phase.
 */
public class FillRouteViewImpl extends Application {

    private static final long serialVersionUID = 1L;
    private FillRoute fillRoute;

    public FillRouteViewImpl(FillRoute fillRoute) {
        this.fillRoute = fillRoute;
    }

    @Override
    public void start(Stage secondaryStage) throws Exception {
        
    }

}