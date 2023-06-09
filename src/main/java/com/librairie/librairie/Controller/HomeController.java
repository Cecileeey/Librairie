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

public class HomeController {
    @FXML
    private TextField titre;
    @FXML
    private TextField auteur;
    @FXML
    private DatePicker parution;
    @FXML
    private TextField presentation;
    @FXML
    private TextField colonne;
    @FXML
    private TextField rangee;
    @FXML
    private TextField image;

    //BUTTON
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMoins;
    @FXML
    private Button btnValider;
    @FXML
    private Button save;

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


    public ObservableList<Bibliotheque.Livre> getList() {
    ObservableList<Bibliotheque.Livre> listBook = FXCollections.observableArrayList();
    return listBook;
    }
    @FXML
    private void handleBookAction(ActionEvent event) {
    Bibliotheque.Livre.Auteur auteur1 = new Bibliotheque.Livre.Auteur(auteur.getText(), auteur.getText());
        String titreText = titre.getText();
        String presentationText = presentation.getText();
        String parutionText = String.valueOf(parution.getValue();
        Integer colonneText = Integer.parseInt(colonne.getText();
        Integer rangeeText = Integer.parseInt(rangee.getText();

        System.out.println(parutionText);

        // Affichage des données dans le tableau nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ObservableList<Bibliotheque.Livre> listD = getList();

        presentationColumn.setCellValueFactory(cellData -> cellData.getValue().getPresentation());
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        auteurColumn.setCellValueFactory(cellData -> cellData.getValue().getAuteur());
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
    }

}
