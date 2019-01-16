package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/connect.fxml"));

        primaryStage.setTitle("Connection");
        primaryStage.setScene(new Scene(root, 300, 275));


        primaryStage.show();
    }

    protected Stage createClientStage() {
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



    protected Stage createHostStage() throws Exception{
        Stage stage = new Stage();


        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/host.fxml"));


            stage.setTitle("Server");
            stage.setScene(new Scene(root, 300, 275));


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;


    }


}
