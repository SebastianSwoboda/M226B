package serverClientMessenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.Control;

public class ServerController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);
    private Control
            control;

    @FXML
    private Label serverMessageLabel;
    @FXML
    private TextField serverMessagingField;
    @FXML
    private Label serverErrorLabel;


    @FXML
    private void sendMessageAsServer() {
        try {
            control.sendMessageToClient(getTextFromServerTextField());

        } catch (Exception e) {
            LOGGER.error("when sending message to client: " + e, e);
            serverErrorLabel.setText("Error when sending message to client");
        }
    }

    @FXML
    public void updateClientMessage(String message) {
        serverMessageLabel.setText(message);
    }

    private String getTextFromServerTextField() {
        return serverMessagingField.getText();
    }

    @FXML
    public void setServerErrorMessage(String message) {
        serverErrorLabel.setText(message);
    }

    public void setControl(Control control) {

        this.control = control;
    }
}


