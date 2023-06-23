package com.librairie.librairie.Controller;
import com.librairie.librairie.Model.Bibliotheque;
import com.librairie.librairie.Model.DbConnection;
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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public TextField etat;

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
    public TableColumn <Bibliotheque.Livre, String> etatColumn;
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
     * Méthode qui initilise différent attributs.
     */
    @FXML
    public void initialize(){
        inittableau();
        buttonMoins.setDisable(true);
        setDefaultTextField();
        fileSaved = true;
        parution.getEditor().setDisable(true);
    }

    /**
     * Méthode qui permet de récupérer les données dans les champs et de les relier aux colonnes du tableau.
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
        //imageColumn.setCellValueFactory(cellData -> cellData.getValue().getImage());
        etatColumn.setCellValueFactory(cellData -> {
            String etat = String.valueOf(cellData.getValue().getEtat());
            ObservableValue<String> observableEtat = Bindings.createStringBinding(() -> etat);
            return observableEtat;
        });
        tableView.getColumns().setAll(titreColumn,auteurColumn,presentationColumn,parutionColumn,colonneColumn,rangeeColumn,imageColumn, etatColumn);
    }

    /**
     * Méthode qui permet de récupérer les données de la ligne selectionné et de remplir les champs
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
            etat.setText(selectedBook.getEtat());

            buttonMoins.setDisable(false);
        }
    }

    /**
     * Méthode qui permet d'insérer des données dans le tableau.
     * On récupère d'abord les données.
     * Ensuite ???
     * Puis, on ajoute le tout dans le tableView.
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
            String etatText = etat.getText();

            //Affichage de l'image
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            if (selectedBook == null) {
                bibliotheque.addLivre(titreText, auteur1, presentationText, colonneText, rangeeText,datapickerText, imageUrl, etatText);
                // Mise a jour du tableau
                ObservableList<Bibliotheque.Livre> listD = getList();
                tableView.setItems(listD);
                tableView.refresh();
                fileSaved = false;
                AlerteAddModifyBookDone();

            } else {
                selectedBook.setTitre(titreText);
                selectedBook.setPresentation(presentationText);
                selectedBook.setRangee(rangeeText);
                selectedBook.setColonne(colonneText);
                selectedBook.setParution(datapickerText);
                selectedBook.setImage(imageUrl);
                selectedBook.setEtat(etatText);

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
     * Méthode qui permet de vérifier la syntaxe des données spécifiques rentrés.
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
     * Méthode qui permet de sauvegarder un nouveau fichier XML dans l'ordinateur de l'utilisateur.
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
     * Méthode qui permet de sauvegarder le fichier XML qui est en modification.
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
     * Méthode qui permet d'ouvrir un fichier XML dans le tableau de l'application.
     * @param event
     * @throws JAXBException
     * @throws SAXException
     */
    public void handleOpen(ActionEvent event) throws JAXBException, SAXException {
        File xsdf = new File("src/main/ressources/Biblio.xsd");

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

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
     * Méthode qui met des données dans les différents champs.
     * Permet de ne pas avoir de champ vide.
     */
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
        etat.setText("Disponible");
    }

    /**
     *  Méthode qui fait fonctionner le bouton +
     */
    public void handlePlusBouton(){
        tableView.getSelectionModel().clearSelection();
        setDefaultTextField();
        buttonMoins.setDisable(true);
        selectedBook = null;
        titre.requestFocus();
    }

    /**
     *  Méthode qui fait fonctionner le bouton -.
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

    public ObservableList<Bibliotheque.Livre> data = FXCollections.observableArrayList();

    /**
     * Méthode pour la connexion à la BDD.
     * On fait appel à la méthode qui se trouve dans dbConnection, une fois connecté on fait une requête SQL pour récupérer les données
     * dans phpmyadmin.
     * Affichage des données dans le tableau.
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
                        new Bibliotheque.Livre.Auteur(rs.getString(3).toString().split(" ")[0], rs.getString(3).toString().split(" ")[1]),
                        rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getString(7),
                        rs.getString(8), rs.getString(9)));
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
        etat.getCharacters();
        tableView.setItems(data);
    }

    /**
     * Méthode pour faire fonctionner le bouton exit.
     * Met une alerte si le fichier n'a pas été sauvegardé avant de quitter.
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
     * Méthode qui alerte si l'utilisateur veut bien quitter sans avoir sauvegardé son fichier.
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

    /**
     * Méthode qui alerte de que la bibliothèque a bien été mise à jour.
     */
    public void AlerteAddModifyBookDone(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText(null);
        alert.setContentText("La bibliothèque a été mise a jour !");
        alert.showAndWait();
    }

    /**
     * Méthode qui permet de modifier un livre.
     * Avec une alerte pour prévenir l'utilisateur s'il veut bien modifier le livre.
     * Alerte qui indique que le livre a bien été modifié.
     * @return
     */
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

    /**
     * Méthode qui permet de supprimer un livre.
     * Avec une alerte pour prévenir l'utilisateur s'il veut bien supprimer le livre.
     * Alerte qui indique que le livre a bien été supprimé.
     * @return
     */
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

     /**
     * Méthode pour inserer des données dans la BDD
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
            alert.setContentText("L'ajout n'a pas marché !");
            alert.showAndWait();
        }
    }
}

