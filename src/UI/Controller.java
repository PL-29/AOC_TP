package UI;

import Metier.*;
import Metier.Algorithmes.AlgoDiffusion;
import Metier.Algorithmes.DiffusionAtomique;
import Metier.Algorithmes.DiffusionEpoque;
import Metier.Algorithmes.DiffusionSequentielle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * La classe controller implémente l'interface Initializable de javafx.
 * Elle joue le rôle de controlleur dans modèle-vue-controlleur (MVC),
 * c'est-à-dire qu'elle connecte les élements de la vue avec ceux du modèle.
 * Les differentes actions de la vue sont gérées ici.
 *
 * @see Initializable
 */
public class Controller implements Initializable {

    @FXML
    private Button boutonDemarrer;

    @FXML
    private Button boutonArreter;

    @FXML
    private TextField afficheur1;

    @FXML
    private TextField afficheur2;

    @FXML
    private TextField afficheur3;

    @FXML
    private TextField afficheur4;

    @FXML
    private ToggleGroup choixAlgorithme;

    @FXML
    private RadioButton radioBtnSequentielle;

    @FXML
    private RadioButton radioBtnDiffusion;

    @FXML
    private RadioButton radioBtnEpoque;

    /**
     * Instanciation du générateur, des 4 canaux, 4 afficheurs.
     * Gestion des actions sur le boutons démarrer (définiton de l'algorithme) et arreter.
     *
     * @see Initializable#initialize(URL, ResourceBundle)
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(32);

        // Création du générateur
        GenerateurImpl generateur = new GenerateurImpl(scheduler);

        // Création des canaux
        Canal canal1 = new Canal(generateur, scheduler);
        Canal canal2 = new Canal(generateur, scheduler);
        Canal canal3 = new Canal(generateur, scheduler);
        Canal canal4 = new Canal(generateur, scheduler);

        // Création des afficheurs
        // Liaison entre la vue et le modèle
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
                generateur.stop();
                radioBtnSequentielle.setDisable(false);
                radioBtnDiffusion.setDisable(false);
                radioBtnEpoque.setDisable(false);
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
                        break;
                    case "radioBtnEpoque" :
                        algorithme = new DiffusionEpoque();
                        break;
                    case "radioBtnDiffusion" :
                    default:
                        algorithme = new DiffusionAtomique();
                }

                generateur.setAlgo(algorithme);
                generateur.lancement();
            }
        });
    }
}
