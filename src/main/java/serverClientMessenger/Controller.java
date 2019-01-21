package serverClientMessenger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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




    @FXML
    private TextField serverPort;

    @FXML
    private TextField clientPort;

    @FXML
    private TextField serverAddress;

    @FXML
    private TextField messagingField;
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
    protected void updateClientMessage() {
        serverMessageLabel.setText(ServerThread.messageFromClient);

    }

    @FXML
    protected void updateServerMessage() {
        LOGGER.info("trying to update message from server");
        //if (ServerThread.messageForClient != null) {

            //control.receiveMessage();
            clientMessageLabel.setText(Client.messageFromServer);

        //}
    }


}



