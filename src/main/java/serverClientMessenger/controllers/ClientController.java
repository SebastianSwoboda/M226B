package serverClientMessenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.Control;

public class ClientController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);
    private Control control;

    @FXML
    private Label clientMessageLabel;
    @FXML
    private TextField clientMessagingField;
    @FXML
    private Label clientErrorLabel;

    @FXML
    private void sendMessageAsClient() {
        try {
            control.sendMessageToServer(getTextFromClientTextField());
        } catch (Exception e) {
            LOGGER.error("when sending message to host: " + e, e);
            clientErrorLabel.setText("Error when sending message to server");

        }
    }

    public void updateServerMessage(String message) {
        LOGGER.info("trying to update message from server");
        clientMessageLabel.setText(message);
    }

    private String getTextFromClientTextField() {
        return clientMessagingField.getText();
    }

    @FXML
    public void setClientErrorMessage(String message) {
        clientErrorLabel.setText(message);
    }

    public void setControl(Control control) {
        this.control = control;
    }

}
