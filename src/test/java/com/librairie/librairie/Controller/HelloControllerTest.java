package com.librairie.librairie.Controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class HelloControllerTest {
    private HelloController helloController;
    private JFXPanel panel = new JFXPanel();
    @BeforeAll
    public static void initializeJavaFX() {
        // Initialiser JavaFX en créant une instance de JFXPanel
        JFXPanel panel = new JFXPanel();
    }
    @BeforeEach
    public void setup() {
        helloController = new HelloController();
        helloController.titre = new TextField();
        helloController.auteur = new TextField();
        helloController.presentation = new TextField();
        helloController.colonne = new TextField();
        helloController.rangee = new TextField();
        helloController.parution = new DatePicker();
        helloController.image = new TextField();
        helloController.imageView = new ImageView();
        helloController.tableView = new TableView<>();
    }

    @Test
    void handleNewBook() {
        helloController.titre.setText("Livre 1");
        helloController.auteur.setText("Auteur 1");
        helloController.presentation.setText("Présentation 1");
        helloController.colonne.setText("1");
        helloController.rangee.setText("1");
        helloController.parution.setValue(LocalDate.now());
        helloController.image.setText("image.png");

        // Méthode handleBookAction à tester
        Platform.runLater(() -> helloController.handleBookAction(null));

        // Vérification des résultats
        assertNotNull(helloController.tableView.getItems());
        assertEquals(1, helloController.tableView.getItems().size());
        assertEquals("Livre 1", helloController.tableView.getItems().get(0).getTitre());

    }
}