package serverClientMessenger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    static Control control = new Control();

    Main main = new Main();


    //Callable server = new ServerThread();
    // Future<String> future;

    //  Socket user = new Socket();

    @FXML
    private TextField messagingField;
    @FXML
    private TextField serverMessagingField;

    @FXML
    private Label serverMessageLabel;

    @FXML
    private Label clientMessageLabel;

    @FXML
    private void startServer() {


        try {

            main.createHostStage();
            control.startServer();


        } catch (Exception e) {
            //TODO}


        }


    }

    @FXML
    private void connectToServer() {
        try {


            main.createClientStage();
            control.startClient();

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
            //hostMessageLabel.setText("hello dude");
            Client.messageForServer = messagingField.getText();
            control.sendMessage();


        } catch (Exception e) {
            System.err.println("Error when sending message to host" + e);

        }


    }

    @FXML
    private void sendMessageAsServer() {

        ServerThread.messageForClient = serverMessagingField.getText();
        ServerThread.sendMessageToClient();
    }


    @FXML
    private void updateClientMessage() {
            serverMessageLabel.setText(ServerThread.messageFromClient);

    }

    @FXML
    private void updateServerMessage() {
        if(ServerThread.messageForClient!=null) {

            control.receiveMessage();
            clientMessageLabel.setText(Client.messageFromServer);
        }
    }


}



