package serverClientMessenger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private static Stage currentStage;

    private static Control control = new Control();

    private Main main = new Main();
    @FXML
    private TextField serverPort;
    @FXML
    private TextField clientPort;
    @FXML
    private TextField serverAddress;
    @FXML
    private TextField clientMessagingField;
    @FXML
    private TextField serverMessagingField;
    @FXML
    private Label serverMessageLabel;
    @FXML
    private Label clientMessageLabel;
    @FXML
    private Label clientConfigErrorLabel;
    @FXML
    private Label serverConfigErrorLabel;

    private String getTextFromClientTextField() {
        return clientMessagingField.getText();
    }

    private String getTextFromServerTextField() {
        return serverMessagingField.getText();
    }

    @FXML
    private void startServerConfig() {
        currentStage = main.createServerConfigStage();
    }

    @FXML
    private void startClientConfig() {
        currentStage = main.createClientConfigStage();
    }

    @FXML
    private void startServer() {
        try {
            int portServer = Integer.parseInt(serverPort.getText());
            control.startServer(portServer);
            currentStage.close();
            main.createServerStage();
        } catch (NumberFormatException e) {
            LOGGER.error("when starting server" + e, e);
            serverConfigErrorLabel.setText("This port does not exist");
        }
    }

    @FXML
    private void startClient() {
        try {
            int portClient = Integer.parseInt(clientPort.getText());
            String addressForServer = serverAddress.getText();
            control.startClient(addressForServer, portClient);
            currentStage.close();
            main.createClientStage();
        } catch (NumberFormatException e) {
            LOGGER.error("when starting client" + e, e);
            clientConfigErrorLabel.setText("This port does not exist");
        } catch (Exception e) {
            LOGGER.error("when starting client" + e, e);
            clientConfigErrorLabel.setText("Problem occurred when connecting to server");
        }
    }


    @FXML
    private void sendMessageAsClient() {
        try {
            control.sendMessageToServer(getTextFromClientTextField());
        } catch (Exception e) {
            LOGGER.error("when sending message to host: " + e, e);
        }
    }

    @FXML
    private void sendMessageAsServer() {
        try {
            control.sendMessageToClient(getTextFromServerTextField());

        } catch (Exception e) {
            LOGGER.error("when sending message to client: " + e, e);
        }
    }

    @FXML
    void updateClientMessage() {
        serverMessageLabel.setText(ServerThread.messageFromClient);
    }

    @FXML
    void updateServerMessage() {
        LOGGER.info("trying to update message from server");
        clientMessageLabel.setText(Client.messageFromServer);
    }
}



