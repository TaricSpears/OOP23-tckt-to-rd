package it.unibo.view.FillRoute.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Class to create an alert for the case in which the player does not have
 * enough cards to fill a route, extends {@link Alert}.
 */
public class NotEnoughCardsAlert extends Alert {

    private AlertType alertType;
    private String contentText;
    private ButtonType ok;

    private Alert alert;

    /**
     * Constructor for the alert.
     * 
     * @param alertType   the type of the alert.
     * @param contentText the text of the alert.
     * @param ok          the button type of the alert.
     */
    public NotEnoughCardsAlert(AlertType alertType, String contentText, ButtonType ok) {
        super(alertType);
        this.alertType = alertType;
        this.contentText = contentText;
        this.ok = ok;
        this.alert = new Alert(alertType);
    }

    /*
     * Method to open the alert.
     */
    public void openAlert() {
        alert.setAlertType(alertType);
        alert.setContentText(contentText);
        alert.getButtonTypes().setAll(ok);
        alert.showAndWait();
    }

}
