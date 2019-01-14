package studies.networking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("studies/networking/connect.fxml"));
        primaryStage.setTitle("Connection");
        primaryStage.setScene(new Scene(root, 300, 275));






        primaryStage.show();
    }

    protected Stage createClientStage(){
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("studies/networking/client.fxml"));



            stage.setTitle("Messaging");
            stage.setScene(new Scene(root, 300, 275));


            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }return stage;
        }

        protected Stage createHostStage(){
            Stage stage = new Stage();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("host.fxml"));



                stage.setTitle("Host");
                stage.setScene(new Scene(root, 300, 275));




                stage.show();

            }
            catch (IOException e) {
                e.printStackTrace();
            }return stage;


        }



/*
    protected Socket connectToServer(){
        String serverName = "localhost";
        int serverPort =6606;
        Socket client=null;

        try{

            client = new Socket(serverName, serverPort);

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;

    }*/

    protected void outputStreamToServer(Socket client){
        try {


        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);}catch (IOException e){
            //TODO
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
