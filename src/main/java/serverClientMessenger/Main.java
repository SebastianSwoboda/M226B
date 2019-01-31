package serverClientMessenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the first class the get executed by the jvm and will create a window, where you can either choose to start a
 * server or a client.
 * @author Sebastian Swoboda and Silvio Merz
 * @since 1.0
 *
 */
public class Main extends Application {

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
}
