package com.librairie.librairie.Controller;
import com.librairie.librairie.Model.Bibliotheque;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    Bibliotheque.Livre selectedbook = null ;
    File selectedFile = null;
    boolean fileSaved ;

    @FXML
    public void initialize(){

        inittableau();
        buttonMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = true;
        parution.getEditor().setDisable(true);
    }
    public void inittableau(){

        titreColumn.setCellValueFactory(cellData -> {
            String titre = String.valueOf(cellData.getValue().titreProperty());
            ObservableValue<String> observableTitre = Bindings.createStringBinding(() -> titre);
            return observableTitre;
        });
        presentationColumn.setCellValueFactory(cellData -> {
            String presentation = String.valueOf(cellData.getValue().getPresentation());
            ObservableValue<String> observablePresentation = Bindings.createStringBinding(() -> presentation);
            return observablePresentation;
        });
        auteurColumn.setCellValueFactory(cellData -> {
            String auteur = cellData.getValue().getStringAuteur();
            ObservableValue<String> observableAuteur = Bindings.createStringBinding(() -> auteur);
            return observableAuteur;
        });
        rangeeColumn.setCellValueFactory(cellData -> {
            int rangee = cellData.getValue().rangeeProperty();
            ObservableValue<Integer> observableRangee = Bindings.createIntegerBinding(() -> rangee).asObject();
            return observableRangee;
        });
        colonneColumn.setCellValueFactory(cellData -> {
            int colonne = cellData.getValue().getColonne();
            ObservableValue<Integer> observableColonne = Bindings.createIntegerBinding(() -> colonne).asObject();
            return observableColonne;
        });
        parutionColumn.setCellValueFactory(cellData -> {
            String parution = String.valueOf(cellData.getValue().getParution());
            ObservableValue<String> observableParution = Bindings.createStringBinding(() -> parution);
            return observableParution;
        });
        tableView.getColumns().setAll(titreColumn,auteurColumn,presentationColumn,parutionColumn,colonneColumn,rangeeColumn);

    }

    @FXML
    public void handleSelection(MouseEvent event){

        selectedbook = tableView.getSelectionModel().getSelectedItem();
        if(selectedbook != null){
            titre.setText(selectedbook.getTitre());
            auteur.setText(selectedbook.getStringAuteur());
            presentation.setText(selectedbook.getPresentation());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse("01-01-" + selectedbook.getParution(), formatter);
            parution.setValue(localDate);
            colonne.setText(Integer.toString(selectedbook.getColonne()));
            rangee.setText(Integer.toString(selectedbook.getRangee()));
            image.setText(selectedbook.getImage());

            //btn moins active

            buttonMoins.setDisable(false);
        }
    }
    @FXML
    public void handleBookAction(ActionEvent event){
        if(checkData()) {
            //Recuperer les donnÃ©es entrees dans le texte fields.
            Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur() ;
            String auteurTexte = auteur.getText();
            String[] PrenomNom = auteurTexte.split(" ", 2);
            auteur1.setPrenom(PrenomNom[0].substring(0,1).toUpperCase() + PrenomNom[0].substring(1));
            auteur1.setNom(PrenomNom[1].substring(0,1).toUpperCase() + PrenomNom[1].substring(1));
            String presentationText = presentation.getText();
            String titreText = titre.getText();
            int colonneText = Integer.parseInt(colonne.getText());
            int rangeeText = Integer.parseInt(rangee.getText());
            String datapickerText = String.valueOf(parution.getValue().getYear());
            String imageUrl = image.getText();

            //Affichage de l'image
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            if (selectedbook == null) {
                bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText, imageUrl);
                // Mise a jour du tableau

                ObservableList<Bibliotheque.Livre> listD = getList();
                tableView.setItems(listD);
                tableView.refresh();
                fileSaved = false;
                AlerteAddModifyBookDone();

            } else {

                selectedbook.setTitre(titreText);
                selectedbook.setPresentation(presentationText);
                selectedbook.setParution(datapickerText);
                selectedbook.setRangee(rangeeText);
                selectedbook.setColonne(colonneText);
                selectedbook.setImage(imageUrl);


                // Mise a jour du tableau
                if (AlerteModifyBook()) {
                    ObservableList<Bibliotheque.Livre> listD = getList();
                    tableView.setItems(listD);
                    tableView.refresh();
                    fileSaved = false;
                    AlerteAddModifyBookDone();
                }
            }
        }
    }

    public boolean checkData(){
        boolean ti , aut, col , rg , img;
        if(titre.getText().matches("[A-Za-z0-9 _]*")){
            ti = true;
        }
        else{
            ti = false;
            System.out.println("pb titre");
        }
        if(auteur.getText().matches("[A-za-z]*\s[A-za-z]*")){
            aut = true;
        }
        else{

            aut = false;
            System.out.println("pb auteur");
        }
        if(colonne.getText().matches("[0-9]*") && Integer.parseInt(rangee.getText()) <= 12 && Integer.parseInt(rangee.getText()) >= 1){
            col = true;
        }
        else{

            col = false;
            System.out.println("pb colonne");
        }
        if(rangee.getText().matches("[1-7]")){
            rg = true;
        }
        else{
            rg = false;
            System.out.println("pb rangee");

        }
        img = true;
        try{
            new Image(image.getText());
        }
        catch(Exception e){
            img = false ;
            System.out.println( e.getMessage());
        }

        return ti && aut && col && rg && img ;
    }

    @FXML
    public void handleSaveAs(ActionEvent event) throws JAXBException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
            fileSaved = true;
        }
    }
    public void handleSave(ActionEvent event) throws JAXBException {

        if (selectedFile != null) {
            if (selectedFile != null) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                System.out.println("ok");
                jaxbMarshaller.marshal(bibliotheque, selectedFile);
                fileSaved = true;
            } else {
                handleSaveAs(event);
            }
        }
    }

    public void handleOpen(ActionEvent event) throws JAXBException, SAXException {
        File xsdf = new File("src/main/xsd/Biblio.xsd");

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        if (selectedFile != null){
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            //try
            Schema sch  = schemafactory.newSchema(xsdf);
            jaxbunMarshaller.setSchema(sch);
            bibliotheque= (Bibliotheque) jaxbunMarshaller.unmarshal(selectedFile);
            bibliotheque.print();

            /* mise a jour du tableau d'affichage */

            ObservableList<Bibliotheque.Livre> listD = getList();
            tableView.setItems(listD);
            fileSaved = true;

        }
    }



    public void handleOutsideCLick(){

        tableView.getSelectionModel().clearSelection();
        setDefaultTextField();
        selectedbook = null;
        buttonMoins.setDisable(true);

    }

    public void setDefaultTextField(){

        titre.setText("Titre");
        auteur.setText("Prenom Nom");
        presentation.setText("Un court resume");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("01-01-2000", formatter);
        parution.setValue(localDate);
        colonne.setText("1");
        rangee.setText("1");
        image.setText("https://birkhauser.com/product-not-found.png");
    }

    public void handlePlusBouton(){

        tableView.getSelectionModel().clearSelection();
        setDefaultTextField();
        buttonMoins.setDisable(true);
        selectedbook = null;
        titre.requestFocus();
    }

    public void handleMoinsBouton(){

        if(selectedbook != null){
            if (AlerteSuppBook()) {
                bibliotheque.getLivre().remove(selectedbook);
                ObservableList<Bibliotheque.Livre> listD = getList();
                tableView.setItems(listD);
                tableView.refresh();
                fileSaved = false;
            }
        }

    }

    public void handleExit() throws JAXBException {

        if(!fileSaved){
            if(AlerteSauvegarde()){
                handleSave(new ActionEvent());
            }

        }
        Platform.exit();
    }

    //ALERTES
    public boolean AlerteSauvegarde(){
        String name = "no file";
        if(selectedFile != null){name = selectedFile.getName(); }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're going to exist without saving");
        alert.setContentText("Toute les modifications apportées au fichier " + name + "seront perdu. Cliquez sur" +
                " OK pour sauvegarder votre fichier" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }
    }

    public void AlerteAddModifyBookDone(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText(null);
        alert.setContentText("Bibliotheque mise a jour");

        alert.showAndWait();
    }

    public boolean AlerteModifyBook(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Livre");
        alert.setHeaderText("Voulez vous modifier " + selectedbook.getTitre());
        alert.setContentText("Les modifications apportées au livre " + selectedbook.getTitre() + "vont etre validée. Cliquez sur" +
                " OK pour continuer" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }
    public boolean AlerteSuppBook(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Livre");
        alert.setHeaderText("Voulez vous supprimer " + selectedbook.getTitre());
        alert.setContentText("Voulez vous supprimer " + selectedbook.getTitre() + "  de la liste? Cliquez sur" +
                " OK pour supprimer" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }
}

