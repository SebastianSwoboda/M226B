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

    private String getTextFromClientTextField() {
        return clientMessagingField.getText();
    }

    private String getTextFromServerTextField() {
        return serverMessagingField.getText();
    }


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
    private void startServerConfig() {
        currentStage = main.createServerConfigStage();
    }

    @FXML
    private void startClientConfig() {
        currentStage = main.createClientConfigStage();
    }

    @FXML
    private void startServer() {
        currentStage.close();
        main.createServerStage();
        int portServer = Integer.parseInt(serverPort.getText());
        control.startServer(portServer);
    }

    @FXML
    private void startClient() {
        try {

            currentStage.close();
            int portClient = Integer.parseInt(clientPort.getText());
            String addressForServer = serverAddress.getText();
            control.startClient(addressForServer, portClient);
            main.createClientStage();
        } catch (Exception e) {
            LOGGER.error("when starting client" + e);
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

        }catch (Exception e){
            LOGGER.error("when sending message to client: "+e,e);
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



