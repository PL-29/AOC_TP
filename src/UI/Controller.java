package UI;

import Metier.*;
import Metier.Algorithmes.AlgoDiffusion;
import Metier.Algorithmes.DiffusionAtomique;
import Metier.Algorithmes.DiffusionSequentielle;
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

        // Création des canaux
        Canal canal1 = new Canal();
        Canal canal2 = new Canal();

        // Création des afficheurs
        Afficheur afficheur1 = new Afficheur();
        Afficheur afficheur2 = new Afficheur();

        canal1.attach(afficheur1);
        canal2.attach(afficheur2);

        // ------------------ START ------------------
        boutonDemarrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Toggle toggle = choixAlgorithme.getSelectedToggle();
                // Choisi l'algo - TODO get the algo selected in the ihm
                //AlgoDiffusion algo = new DiffusionAtomique();
                AlgoDiffusion algo = new DiffusionSequentielle();

                GenerateurImpl g = new GenerateurImpl(algo);
                g.attach(canal1);
                g.attach(canal2);

                System.out.println("Lancement de l'algorithme");
                g.start();
            }
        });
    }
}
