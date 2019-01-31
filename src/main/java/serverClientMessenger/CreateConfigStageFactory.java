package serverClientMessenger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import serverClientMessenger.controllers.ClientConfigController;
import serverClientMessenger.controllers.ServerConfigController;

import java.io.IOException;

/**
 * Will create a server config window or a client config window depending what the user selects.
 * @author Silvio Merz and Sebastian Swoboda
 * @since 1.0
 *
 */
public class CreateConfigStageFactory {


    public void createClientConfigStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/clientConfig.fxml"));
            Parent root = loader.load();
            ClientConfigController clientConfigController = loader.getController();
            clientConfigController.setClientConfigStage(stage);
            stage.setTitle("ClientConfig");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createServerConfigStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/serverConfig.fxml"));
            Parent root = loader.load();
            ServerConfigController serverConfigController = loader.getController();
            serverConfigController.setStage(stage);
            stage.setTitle("ServerConfig");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


