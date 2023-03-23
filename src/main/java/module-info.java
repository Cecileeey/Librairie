module com.librairie.librairie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.librairie.librairie to javafx.fxml;
    exports com.librairie.librairie;
}