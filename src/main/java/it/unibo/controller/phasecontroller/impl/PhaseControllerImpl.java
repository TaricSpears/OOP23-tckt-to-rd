package it.unibo.controller.phasecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.model.phasemanager.api.PhaseManager;
import it.unibo.model.phasemanager.impl.PhaseManagerImpl;

/**
 * Implementation of {@link PhaseController}.
 * Controller of the phases of the single turn.
 */
public class PhaseControllerImpl implements PhaseController {

    private PhaseManager phaseManager;

    /**
     * {@inheritDoc}
     */
    public PhaseControllerImpl() {
        this.phaseManager = new PhaseManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    public PhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        switch (this.phaseManager.getCurrentPhase()) {
            case START:
                return "Draw a new Train Card.";
            case MID:
                return "Choose whether to draw another Train Card, a new Objective Card or claim a route.";
            case END:
                return "Pass the turn to the next player";
            default:
                throw new IllegalStateException("Unknown phase");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhaseManager.Phase getCurrentPhase() {
        return this.phaseManager.getCurrentPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchPhase() {
        this.phaseManager.switchPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEndPhase(final MainController mainController) {
        if (mainController.getTurnController().getCurrentPlayer().getCarriageNum() <= 2) {
            mainController.getGameController().setLastTurn();
        }

        return this.phaseManager.getCurrentPhase() == PhaseManager.Phase.END;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMidPhase() {
        return this.phaseManager.getCurrentPhase() == PhaseManager.Phase.MID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStartPhase() {
        return this.phaseManager.getCurrentPhase() == PhaseManager.Phase.START;
    }
}
