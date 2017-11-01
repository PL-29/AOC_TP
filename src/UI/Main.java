package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe Main permet la définition du fichier fmxl, la défintion de la taille de la fenêtre
 * ainsi que le lancement de l'application. Elle étend de la classe Application de javafx.
 *
 * @see Application
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public class Main extends Application {

    /**
     * @see Application#start(Stage)
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setScene(new Scene(root, 500, 320));
        primaryStage.show();
    }

    /**
     * Lancement de l'application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
