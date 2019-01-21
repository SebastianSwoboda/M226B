package serverClientMessenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    static Controller clientController;
    static Controller serverController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/start.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Connection");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    Stage createClientConfigStage() {
        Stage stage = new Stage();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/clientConfig.fxml"));
            Parent root = loader.load();
            stage.setTitle("ClientConfig");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    Stage createServerConfigStage() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/serverConfig.fxml"));
            stage.setTitle("ServerConfig");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    void createClientStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/client.fxml"));
            Parent root = loader.load();
            clientController = loader.getController();
            stage.setTitle("Client");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void createServerStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/server.fxml"));
            Parent root = loader.load();
            serverController = loader.getController();
            stage.setTitle("Server");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
