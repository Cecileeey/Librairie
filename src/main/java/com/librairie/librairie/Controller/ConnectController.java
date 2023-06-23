package com.librairie.librairie.Controller;
import com.librairie.librairie.HelloApplication;
import com.librairie.librairie.Model.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Classe qui permet de faire une connection à la base de donnée avec un utilisateur
 * @author marine
 * @version 1.0
 */
public class ConnectController implements Initializable {
    /**
     * Déclarations des attributs utilisés pour la classe ConnectController
     */
    @FXML
    private TextField idIdentifiant;
    @FXML
    private PasswordField idMdp;
    @FXML
    private Integer role;

    /**
     * Méthode qui fait la connection à la base de données de phpmyadmin
     * Ensuite on fait une requête SQL pour vérifier si l'identifiant et le mdp sont bien dans la BDD
     * S'ils n'y sont pas, popUp d'alerte pour prévenir l'utilisateur
     * @param event
     * @throws SQLException
     */
    @FXML
    public void loginUtilisateur (ActionEvent event) throws SQLException {
        Connection con = new DbConnection().getConnexion();
        PreparedStatement stat = null;
        ResultSet rs = null;

        try{
            stat = con.prepareStatement("SELECT * FROM utilisateur WHERE identifiant = '"+idIdentifiant.getText().toString()+"' AND mdp = '"+ idMdp.getText().toString()+"'");
            rs = stat.executeQuery();

            if(rs.next()){
                System.out.println("Connecté");
                Stage stage = new Stage();
                //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("../../../../../resources/com/librairie/librairie/hello-view.fxml"));
                FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene;
                try {
                    scene = new Scene(root.load());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                stage.setScene(scene);
                stage.show();
            }else{
                System.out.println("Non connecté !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur");
                alert.setTitle("Alerte");
                alert.setContentText("Identifiant ou mot de passe incorrect !");
                alert.showAndWait();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Méthode pour utiliser url pour la connection à la BDD
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
