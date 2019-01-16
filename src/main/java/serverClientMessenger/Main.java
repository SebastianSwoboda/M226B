package serverClientMessenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/start.fxml"));

        primaryStage.setTitle("Connection");
        primaryStage.setScene(new Scene(root, 300, 275));


        primaryStage.show();
    }


    Stage createClientConfigStage() {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/clientConfig.fxml"));


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

    Stage createClientStage() {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/client.fxml"));


            stage.setTitle("Client");
            stage.setScene(new Scene(root, 300, 275));


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }


    Stage createServerStage() {
        Stage stage = new Stage();


        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/server.fxml"));


            stage.setTitle("Server");
            stage.setScene(new Scene(root, 300, 275));


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;


    }


}
