package it.unibo.view.FillRoute.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NotEnoughCardsAlert extends Alert {

    private AlertType alertType;
    private String contentText;
    private ButtonType ok;

    private Alert alert;

    public NotEnoughCardsAlert(AlertType alertType, String contentText, ButtonType ok) {
        super(alertType);
        this.alertType = alertType;
        this.contentText = contentText;
        this.ok = ok;
        this.alert = new Alert(alertType);
    }

    public void openAlert() {
        alert.setAlertType(alertType);
        alert.setContentText(contentText);
        alert.getButtonTypes().setAll(ok);
        alert.showAndWait();
    }

}
