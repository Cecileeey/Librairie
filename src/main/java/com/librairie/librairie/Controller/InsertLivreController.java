package com.librairie.librairie.Controller;

import com.librairie.librairie.Model.Bibliotheque;
import com.librairie.librairie.Model.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class InsertLivreController implements Initializable {
    /**
     * Déclarations des attributs de la classe InsertLivreController.
     */
    @FXML
    public TextField titre;
    @FXML
    public TextField auteur;
    @FXML
    public TextField presentation;
    @FXML
    public TextField colonne;
    @FXML
    public TextField rangee;
    @FXML
    public DatePicker parution;
    @FXML
    public TextField image;
    @FXML
    public TextField etat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Méthode qui permet de récupérer les données rentrées dans le tableau.
     * Alerte l'utilisateur si l'ajout à la BDD a bien fonctionné.
     * @param event
     */
    public void insertData(ActionEvent event){
        String titreText = titre.getText();
        Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
        String auteurTexte = auteur.getText();
        String[] PrenomNom = auteurTexte.split(" ", 2);
        auteur1.setPrenom(PrenomNom[0].substring(0,1).toUpperCase() + PrenomNom[0].substring(1));
        auteur1.setNom(PrenomNom[1].substring(0,1).toUpperCase() + PrenomNom[1].substring(1));
        String presentationText = presentation.getText();
        int colonneText = Integer.parseInt(colonne.getText());
        int rangeeText = Integer.parseInt(rangee.getText());
        String datapickerText = String.valueOf(parution.getValue().getYear());
        String imageUrl = image.getText();
        String etatText = etat.getText();

        Bibliotheque.Livre biblio = new Bibliotheque.Livre();
        biblio.setTitre(titreText);
        biblio.setAuteur(auteur1);
        biblio.setPresentation(presentationText);
        biblio.setColonne(colonneText);
        biblio.setRangee(rangeeText);
        biblio.setParution(datapickerText);
        biblio.setImage(imageUrl);
        biblio.setEtat(etatText);

        int status = DbConnection.save(biblio);
        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Livre bien ajouté à la BDD !");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Insertion nulle !");
            alert.showAndWait();
        }
    }
}