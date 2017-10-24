package UI;

import Metier.*;
import Metier.Algorithmes.AlgoDiffusion;
import Metier.Algorithmes.DiffusionAtomique;
import Metier.Algorithmes.DiffusionEpoque;
import Metier.Algorithmes.DiffusionSequentielle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    @FXML
    private RadioButton radioBtnSequentielle;

    @FXML
    private RadioButton radioBtnDiffusion;

    @FXML
    private RadioButton radioBtnEpoque;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création du générateur
        GenerateurImpl generateur = new GenerateurImpl();

        // Création des canaux
        Canal canal1 = new Canal(generateur);
        Canal canal2 = new Canal(generateur);
        Canal canal3 = new Canal(generateur);
        Canal canal4 = new Canal(generateur);

        // Création des afficheurs
        Afficheur aff1 = new Afficheur();
        this.afficheur1.textProperty().bind(aff1.getProperty());

        Afficheur aff2 = new Afficheur();
        this.afficheur2.textProperty().bind(aff2.getProperty());

        Afficheur aff3 = new Afficheur();
        this.afficheur3.textProperty().bind(aff3.getProperty());

        Afficheur aff4 = new Afficheur();
        this.afficheur4.textProperty().bind(aff4.getProperty());

        canal1.attach(aff1);
        canal2.attach(aff2);
        canal3.attach(aff3);
        canal4.attach(aff4);

        generateur.attach(canal1);
        generateur.attach(canal2);
        generateur.attach(canal3);
        generateur.attach(canal4);



        // ------------------ STOP ------------------
        boutonArreter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Algorithme arrêté");
                generateur.stop();
            }
        });

        // ------------------ START ------------------
        boutonDemarrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                // Disabled radio button
                radioBtnSequentielle.setDisable(true);
                radioBtnDiffusion.setDisable(true);
                radioBtnEpoque.setDisable(true);

                RadioButton selectedRadioButton = (RadioButton) choixAlgorithme.getSelectedToggle();
                String selectedRadioButtonID = selectedRadioButton.getId();

                AlgoDiffusion algorithme;

                switch (selectedRadioButtonID){
                    case "radioBtnSequentielle" :
                        algorithme = new DiffusionSequentielle();
                        System.out.println("radioBtnSequentielle");
                        break;
                    case "radioBtnEpoque" :
                        algorithme = new DiffusionEpoque();
                        System.out.println("radioBtnEpoque");
                        break;
                    case "radioBtnDiffusion" :
                    default:
                        algorithme = new DiffusionAtomique();
                        System.out.println("radioBtnDiffusion");
                }

                generateur.setAlgo(algorithme);

                System.out.println("Lancement de l'algorithme");
                generateur.start();
            }
        });
    }
}
