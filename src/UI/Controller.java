package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button boutonDemarrer;

    @FXML
    private Button boutonArreter;

    @FXML
    private TextArea afficheur1;

    @FXML
    private TextArea afficheur2;

    @FXML
    private TextArea afficheur3;

    @FXML
    private TextArea afficheur4;

    @FXML
    private ToggleGroup choixAlgorithme;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boutonDemarrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Toggle toggle = choixAlgorithme.getSelectedToggle();
                System.out.println("Lancement de l'algorithme");
            }
        });
    }
}
