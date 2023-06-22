package com.librairie.librairie.Controller;
import com.librairie.librairie.Model.Bibliotheque;
import com.librairie.librairie.Model.DbConnection;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.xml.sax.SAXException;

/**
 * @version 1.5
 * @author Marianne Marine Cécile
 */

/**
 * Classe principale avec une implementation.
 * @author Marianne Marine Cécile
 */
public class HelloController {
    /**
     * Déclarations des attributs de la classe HelloController.
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
    public ImageView imageView;
    @FXML
    public CheckBox etat;

    /**
     * Déclaration des différents boutons de l'application.
     */
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMoins;
    @FXML
    private Button buttonValider;
    @FXML
    private Button buttonConnexion;
    @FXML
    private Button buttonEnregistrer;

    /**
     * Déclaration des différents messages d'erreurs pour la vérification des champs.
     */
    @FXML
    public Text msgErrorTitre;
    @FXML
    public Text msgErrorAuteur ;
    @FXML
    public Text msgErrorColonne ;
    @FXML
    public Text msgErrorRangee ;
    @FXML
    public Text msgErrorUrl ;


    /**
     * Déclarations des différentes colonnes du tableau ainsi que le tableau lui-même.
     */
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
    @FXML
    public TableColumn <Bibliotheque.Livre, String> imageColumn;
    @FXML
    public TableView <Bibliotheque.Livre> tableView;

    /**
     * Création d'une bibliothèque.
     */
    public Bibliotheque bibliotheque = new Bibliotheque();

    /**
     * Méthode qui permet de détecter les changements.
     * Elle permet de notifier les changements survenant dans la liste.
     * Lorsque des éléments sont ajoutés, supprimés ou modifiés dans la liste, les observateurs enregistrés sont notifiés des modifications.
     * Cela facilite la mise à jour de l'interface utilisateur en fonction des changements de données.
     * Retourne une collection.
     *
     * @return listBook
     */
    public ObservableList<Bibliotheque.Livre> getList() {
    ObservableList<Bibliotheque.Livre> listBook = FXCollections.observableArrayList(bibliotheque.getLivre());
    return listBook;
    }

    Bibliotheque.Livre selectedBook = null ;
    File selectedFile = null;
    boolean fileSaved ;

    /**
     * Cette méthode est appelée lors de l'initialisation de HelloApplication.
     * Elle configure l'état initial des éléments graphiques tels que les boutons, les champs de texte, le calendrier.
     * Elle masque également les messages d'erreur.
     */
    @FXML
    public void initialize(){

        inittableau();
        buttonMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = true;
        parution.getEditor().setDisable(true);
        msgError();
    }

    /**
     * Hide all the error message below the textfields in the form
     * Set all Visible  attribute of textviewers to false
     */
    public void msgError(){
        msgErrorTitre.setVisible(false);
        msgErrorAuteur.setVisible(false);
        msgErrorColonne.setVisible(false);
        msgErrorRangee.setVisible(false);
        msgErrorUrl.setVisible(false);
    }

    /**
     * Méthode qui permet de lier une cellule de la vue tableau à un getter afin de récupérer un attribut de la classe Bibliotheque
     */
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

    /**
     * Set selectedbook to the Livre object binded to the row selected
     * Put attribute of the Livre object from the selected row into the textfield of the form
     * Unable btnMoins (minus button)
     * @param event
     */
    @FXML
    public void handleSelection(MouseEvent event){

        selectedBook = tableView.getSelectionModel().getSelectedItem();
        if(selectedBook != null){
            titre.setText(selectedBook.getTitre());
            auteur.setText(selectedBook.getStringAuteur());
            presentation.setText(selectedBook.getPresentation());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse("01-01-" + selectedBook.getParution(), formatter);
            parution.setValue(localDate);
            colonne.setText(Integer.toString(selectedBook.getColonne()));
            rangee.setText(Integer.toString(selectedBook.getRangee()));
            image.setText(selectedBook.getImage());
            etat.setSelected(selectedBook.getEtat());

            buttonMoins.setDisable(false);
        }
    }

    /**
     * Méthode qui permet de récupérer les données entrées par l'user.
     *
     * @param event
     */
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
            if (selectedBook == null) {
                bibliotheque.addLivre(titreText, auteur1, presentationText, datapickerText, colonneText, rangeeText, imageUrl);
                // Mise a jour du tableau

                ObservableList<Bibliotheque.Livre> listD = getList();
                tableView.setItems(listD);
                tableView.refresh();
                fileSaved = false;
                AlerteAddModifyBookDone();

            } else {

                selectedBook.setTitre(titreText);
                selectedBook.setPresentation(presentationText);
                selectedBook.setParution(datapickerText);
                selectedBook.setRangee(rangeeText);
                selectedBook.setColonne(colonneText);
                selectedBook.setImage(imageUrl);


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

    /**
     * Méthode qui permet de vérifier le respect des REGEX pour chaque champs au moment de l'insertion des données.
     * @return
     */
    public boolean checkData(){
        boolean checkTitre , checkAuteur, checkColonne , checkRangee , checkImg;
        if(titre.getText().matches("[A-Za-z0-9 _]*")){
            checkTitre = true;
        }
        else{
            checkTitre = false;
            System.out.println("erreur titre");
        }
        if(auteur.getText().matches("[A-za-z]*\s[A-za-z]*")){
            checkAuteur = true;
        }
        else{

            checkAuteur = false;
            System.out.println("erreur auteur");
        }
        if(colonne.getText().matches("[0-9]*") && Integer.parseInt(rangee.getText()) <= 12 && Integer.parseInt(rangee.getText()) >= 1){
            checkColonne = true;
        }
        else{

            checkColonne = false;
            System.out.println("erreur colonne");
        }
        if(rangee.getText().matches("[1-7]")){
            checkRangee = true;
        }
        else{
            checkRangee = false;
            System.out.println("erreur rangee");

        }
        checkImg = true;
        try{
            new Image(image.getText());
        }
        catch(Exception e){
            checkImg = false ;
            System.out.println( e.getMessage());
        }

        return checkTitre && checkAuteur && checkColonne && checkRangee && checkImg ;
    }

    /**
     *
     * @param event
     * @throws JAXBException
     */
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

    /**
     *
     * @param event
     * @throws JAXBException
     */
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

    /**
     *
     * @param event
     * @throws JAXBException
     * @throws SAXException
     */
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


    /**
     *
     */
    public void handleOutsideCLick(){

        tableView.getSelectionModel().clearSelection();
        setDefaultTextField();
        selectedBook = null;
        buttonMoins.setDisable(true);

    }

    /**
     * Méthode qui permet de pré-remplir les textfields
     */
    public void setDefaultTextField(){

        titre.setText("Titre");
        auteur.setText("Prénom Nom de l'auteur");
        presentation.setText("Présentation du livre");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("01-01-2022", formatter);
        parution.setValue(localDate);
        colonne.setText("1");
        rangee.setText("1");
        image.setText("https://birkhauser.com/product-not-found.png");
    }

    /**
     * Méthode des différents boutons.
     */
    public void handlePlusBouton(){

        tableView.getSelectionModel().clearSelection();
        setDefaultTextField();
        buttonMoins.setDisable(true);
        selectedBook = null;
        titre.requestFocus();
    }

    /**
     *
     */
    public void handleMoinsBouton(){

        if(selectedBook != null){
            if (AlerteSuppBook()) {
                bibliotheque.getLivre().remove(selectedBook);
                ObservableList<Bibliotheque.Livre> listD = getList();
                tableView.setItems(listD);
                tableView.refresh();
                fileSaved = false;
                handleOutsideCLick();
            }
        }

    }
/*public void start(Stage primaryStage) {
        Button btnEnregistrer = new Button("Sauvegarder");
        btnEnregistrer.setOnAction(e -> {
            // Logique de sauvegarde à implémenter ici
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier");
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                System.out.println("Fichier enregistré : " + file.getAbsolutePath());
            }
        });
    }*/

    /**
     * ???
     */
    public ObservableList<Bibliotheque.Livre> data = FXCollections.observableArrayList();

    /**
     * Méthode pour la connexion à la BDD.
     * On fait appel à la méthode qui se trouve dans dbConnection, une fois connecté on fait une requête SQL pour récupérer les données
     * dans phpmyadmin.
     * Affichage des données dans le tableau.
     *
     * @param event
     * @throws SQLException
     */
    public void login(ActionEvent event) throws SQLException {
        try{
            DbConnection myBDD = new DbConnection();
            Connection con = myBDD.getConnexion();

            System.out.println("Vous êtes connecté à la bdd");
            String sql = "SELECT * FROM livre";
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();

            while(rs.next()){
                data.add(new Bibliotheque.Livre(rs.getString(2),
                        new Bibliotheque.Livre.Auteur(rs.getString(3).toString().split(" ")[0], rs.getString(3).toString().split(" ")[1]),rs.getString(4),
                        rs.getString(7),rs.getInt(5),rs.getInt(6),
                        rs.getString(8)));
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        titre.getCharacters();
        auteur.getCharacters();
        presentation.getCharacters();
        colonne.getCharacters();
        rangee.getCharacters();
        parution.getConverter();
        image.getCharacters();
        tableView.setItems(data);
    }

    /**
     *
     * @throws JAXBException
     */
    public void handleExit() throws JAXBException {

        if(!fileSaved){
            if(AlerteSauvegarde()){
                handleSave(new ActionEvent());
            }

        }
        Platform.exit();
    }

    /**
     *
     * @return
     */
    public boolean AlerteSauvegarde(){
        String name = "no file";
        if(selectedFile != null){name = selectedFile.getName(); }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Êtes-vous sur de quitter sans sauvegarder?");
        alert.setContentText("Toute les modifications apportées au fichier " + name + "seront perdu. Cliquez sur OK pour sauvegarder votre fichier");

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
        alert.setContentText("La bibliothèque a été mise a jour !");

        alert.showAndWait();
    }

    public boolean AlerteModifyBook(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Livre");
        alert.setHeaderText("Voulez-vous modifier ce livre" + selectedBook.getTitre());
        alert.setContentText("Le livre suivant " + selectedBook.getTitre() + "a été modifié. Veuillez valider pour continuer.");

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
        alert.setHeaderText("Voulez-vous le supprimer " + selectedBook.getTitre());
        alert.setContentText("Voulez-vous supprimer " + selectedBook.getTitre() + " de la liste?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false ;
        }

    }
}

