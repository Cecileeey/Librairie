package com.librairie.librairie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField titre;
    @FXML
    private TextField auteur;
    @FXML
    private TextField presentation;
    @FXML
    private TextField parution;

    @FXML
    private TextField colonne;

    @FXML
    private TextField rangee;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}