package com.librairie.librairie.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    @FXML
    private Button infos;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        infos.setOnAction(actionEvent -> infos.getScene().getWindow().hide());
    }


}
