package serverClientMessenger;

public class UpdateMessageLabel implements Runnable {
    boolean isMessageFromClient;

    UpdateMessageLabel(boolean isServer) {
        this.isMessageFromClient = isServer;

    }

    @Override
    public void run() {
        if (isMessageFromClient)
            Main.clientController.updateServerMessage();
        else {
            Main.serverController.updateClientMessage();
        }
    }
}
