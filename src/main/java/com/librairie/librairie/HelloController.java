package com.librairie.librairie;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox vboxFenetre;
    @FXML
    private MenuBar menu;
    @FXML
    private Menu fichier;
    @FXML
    private MenuItem ouvrir;
    @FXML
    private MenuItem quitter;
    @FXML
    private Menu edition;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveAs;
    @FXML
    private Menu about;
    @FXML
    private MenuItem infos;
    @FXML
    private HBox hboxGlobal;
    @FXML
    private VBox vboxTableau;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn titreColumn;
    @FXML
    private TableColumn auteurColumn;
    @FXML
    private TableColumn presentationColumn;
    @FXML
    private TableColumn parutionColumn;
    @FXML
    private TableColumn colonneColumn;
    @FXML
    private TableColumn rangeeColumn;
    @FXML
    private HBox hboxButton;
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMoins;
    @FXML
    private HBox hboxForm;
    @FXML
    private VBox vboxLabel;
    @FXML
    private VBox vboxTextfield;
    @FXML
    private TextField titre;
    @FXML
    private TextField auteur;
    @FXML
    private TextField presentation;
    @FXML
    private DatePicker parution;

    @FXML
    private TextField colonne;

    @FXML
    private TextField rangee;

    @FXML
    private Button valider;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void validerFormulaire() {
        String title = titre.getText();
        String author = auteur.getText();
        String present = presentation.getText();
        /**String parutionDate = parution.get.. new SimpleDateFormat("dd/MM/yyyy"); **/
        String col = titre.getText();
        String rang = titre.getText();

    }
}