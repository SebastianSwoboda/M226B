package serverClientMessenger.controllers;

import javafx.fxml.FXML;
import serverClientMessenger.CreateConfigStageFactory;

public class MainController {

    private CreateConfigStageFactory createStage = new CreateConfigStageFactory();

    @FXML
    private void startServerConfig() {
        createStage.createServerConfigStage();
    }

    @FXML
    private void startClientConfig() {
        createStage.createClientConfigStage();
    }
}
