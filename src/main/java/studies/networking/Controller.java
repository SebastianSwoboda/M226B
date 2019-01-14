package studies.networking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {
    Control control=new Control();

    Client client = new Client();
    Main main = new Main();

    //Callable server = new Server();
   // Future<String> future;

  //  Socket user = new Socket();

    @FXML
    private TextField messagingField;
    @FXML
    private Label hostMessageLabel;
    @FXML
    private void connect() {
        try {


            //client.connectToServer();
            main.createClientStage();

        } catch (Exception e) {
            //TODO
        }


    }

    @FXML
    private void sendMessageAsClient() {

        try {
            /*
            OutputStream outToServer = user.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(messagingField.getText());*/
            hostMessageLabel.setText("hello dude");
            client.sendDataToServer(messagingField.getText());
            hostMessageLabel.setText(control.future.get());






        } catch (Exception e) {
            System.err.println("Error when sending message to host" + e);

        }


    }



    @FXML
    private void startHost() {



        try {



            //ExecutorService executor = Executors.newSingleThreadExecutor();

            main.createHostStage();
            control.startHost();

           // future = executor.submit(server);

        } catch (Exception e) {
            //TODO}


        }



    }

    @FXML
    private void startServer() {






    }


    private void waitForServerMessage(){


    }

    @FXML
    private void updateServerMessage(){





    }



}



