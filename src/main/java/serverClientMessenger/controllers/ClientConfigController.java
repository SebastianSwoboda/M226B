package serverClientMessenger.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverClientMessenger.Control;

import java.io.IOException;

public class ClientConfigController {

    private static final Logger LOGGER = LogManager.getLogger(ClientConfigController.class);
    private Control control = new Control();
    private ClientController clientController;
    private Stage stage;

    @FXML
    private TextField clientPort;
    @FXML
    private TextField serverAddress;
    @FXML
    private Label clientConfigErrorLabel;

    @FXML
    private void startClient() {
        try {

            int portClient = Integer.parseInt(clientPort.getText());
            String addressForServer = serverAddress.getText();
            control.startClient(addressForServer, portClient, createClientStage());
        } catch (NumberFormatException e) {
            LOGGER.error("when starting client" + e, e);
            clientConfigErrorLabel.setText("This port does not exist");
        } catch (RuntimeException e) {
            control.stopClient();
            clientController.setClientErrorMessage("Server disconnected");
        } catch (Exception e) {
            LOGGER.error("when starting client" + e, e);
            clientController.setClientErrorMessage("Problem occurred when connecting to server");
        }
    }

    private ClientController createClientStage() {

        try {
           // Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/client.fxml"));
            Parent root = loader.load();
            clientController = loader.getController();
            clientController.setControl(control);
            stage.setTitle("Client");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
            stage.setOnCloseRequest(event -> control.stopClient());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientController;
    }

    public void setClientConfigStage(Stage stage) {
        this.stage = stage;
    }
}
