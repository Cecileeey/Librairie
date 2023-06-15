package com.librairie.librairie.Controller;
import com.librairie.librairie.Model.Bibliotheque;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class HelloController {
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
    public ImageView imageView;

    //BUTTON
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMoins;
    @FXML
    private Button btnValider;


    //TABLE COLUMN
    @FXML
    public TableColumn <Bibliotheque.Livre, String> titreColumn;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> auteurColumn;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> presentationColumn;
    @FXML
    public TableColumn <Bibliotheque.Livre, String> parutionColumn;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer> colonneColumn;
    @FXML
    public TableColumn <Bibliotheque.Livre, Integer> rangeeColumn;

    public TableView <Bibliotheque.Livre> tableView;

    public Bibliotheque bibliotheque = new Bibliotheque();

    public ObservableList<Bibliotheque.Livre> getList() {
    ObservableList<Bibliotheque.Livre> listBook = FXCollections.observableArrayList();
    return listBook;
    }
    @FXML
    private void handleBookAction(ActionEvent event) {
    Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur(auteur.getText(), auteur.getText());
        String titreText = titre.getText();
        String presentationText = presentation.getText();
        String parutionText = String.valueOf(parution.getValue());
        Integer colonneText = Integer.parseInt(colonne.getText());
        Integer rangeeText = Integer.parseInt(rangee.getText());

        System.out.println(parutionText);

        ObservableList<Bibliotheque.Livre> listD = getList();

        presentationColumn.setCellValueFactory(cellData -> cellData.getValue().getPresentation());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        //auteurColumn.setCellValueFactory(cellData -> cellData.getValue().getAuteur());
        rangeeColumn.setCellValueFactory(cellData -> {
            IntegerProperty rangee = cellData.getValue().rangeeProperty();
            ObservableValue<Integer> observableRangee = Bindings.createIntegerBinding(() -> rangee.get()).asObject();
            return observableRangee;
        });
        colonneColumn.setCellValueFactory(cellData -> {
            IntegerProperty colonne = cellData.getValue().getColonne();
            ObservableValue<Integer> observableColonne = Bindings.createIntegerBinding(() -> colonne.get()).asObject();
            return observableColonne;
        });
        parutionColumn.setCellValueFactory(cellData -> cellData.getValue().getParution());

        tableView.getColumns().setAll(titreColumn,auteurColumn,presentationColumn,parutionColumn,colonneColumn,rangeeColumn);

        Bibliotheque.Livre livre = new Bibliotheque.Livre(titreText,auteur1,presentationText,parutionText,colonneText,rangeeText);
        tableView.getItems().add(livre);

        String imageUrl = image.getText();
        System.out.println(imageUrl);
        Image image = new Image(imageUrl);
        imageView.setImage(image);
    }

    @FXML
    public void handleSaveAs(ActionEvent event) throws JAXBException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", ".xml"));
        File selectedFile = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
        }
    }

    public void handleOpen(ActionEvent event) throws JAXBException {
        // ouverture du fichier xml
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", ".xml"));
        File selectedFile = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        if (selectedFile != null){
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            bibliotheque= (Bibliotheque) jaxbunMarshaller.unmarshal(selectedFile);

            /* mise a jour du tableau d'affichage */

            bibliotheque.getLivre().forEach(l->tableView.getItems().add(l));

        }
    }



}
