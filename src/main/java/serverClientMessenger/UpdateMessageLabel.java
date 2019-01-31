package serverClientMessenger;


import serverClientMessenger.controllers.ClientController;
import serverClientMessenger.controllers.ServerController;

/**
 * This class is used as a bridge between the UI and the business Logic. It is there to update the UI with the newest
 * messages on the Main UI thread.
 * @author Sebastian Swoboda
 * @since 1.0
 *
 *
 */
public class UpdateMessageLabel implements Runnable {

    private String functionSelector;
    private String message;
    private ClientController clientController;
    private ServerController serverController;

    UpdateMessageLabel(String functionSelector, String message, ClientController clientController) {
        this.functionSelector = functionSelector;
        this.message = message;
        this.clientController = clientController;

    }

    UpdateMessageLabel(String isServer, String message, ServerController serverController) {
        this.functionSelector = isServer;
        this.message = message;
        this.serverController = serverController;
    }

    @Override
    public void run() {
    try {
        switch (functionSelector) {
            case "server":
                serverController.updateClientMessage(message);
            case "client":
                clientController.updateServerMessage(message);
            case "serverError":
                serverController.setServerErrorMessage(message);
            case "clientError":
                clientController.setClientErrorMessage(message);
        }}catch (NullPointerException e){

}
    }
}
