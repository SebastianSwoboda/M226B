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

public class ServerConfigController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);
    private ServerController serverController;
    private Control control = new Control();
    private Stage stage;


    @FXML
    private TextField serverPort;
    @FXML
    private Label serverConfigErrorLabel;

    @FXML
    private void startServer() {
        try {

            int portServer = Integer.parseInt(serverPort.getText());
            control.startServer(portServer, createServerStage(control));
        } catch (NumberFormatException e) {
            LOGGER.error("when starting server" + e, e);
            serverConfigErrorLabel.setText("This port does not exist");
        } catch (RuntimeException e) {
            serverController.setServerErrorMessage("error when trying to receive a message");
        }
    }

    private ServerController createServerStage(Control control) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/server.fxml"));
            Parent root = loader.load();
            serverController = loader.getController();
            serverController.setControl(control);
            stage.setTitle("Server");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();

            stage.setOnCloseRequest(event -> {
                try {
                    control.stopServer();
                } catch (IOException e) {
                    serverController.setServerErrorMessage("could not close the server");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverController;
    }

    public void setStage(Stage stage){
        this.stage =stage;
    }
}
