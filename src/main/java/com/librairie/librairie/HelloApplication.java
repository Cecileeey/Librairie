package com.librairie.librairie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe qui permet de lancer l'application Bibliotheque
 */
public class HelloApplication extends Application {
    /**
     * Méthode qui permet de lancer la fenêtre de login
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode qui lance l'application
     * @param args
     */
    public static void main(String[] args) {

        launch();
    }
}