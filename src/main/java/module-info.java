module com.librairie.librairie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;

    requires org.controlsfx.controls;
    requires mysql.connector.java;
    requires java.sql;
    requires lombok;


    opens com.librairie.librairie to javafx.fxml;
    exports com.librairie.librairie;

    exports com.librairie.librairie.Controller;
    opens com.librairie.librairie.Controller to javafx.fxml;

    exports com.librairie.librairie.Model;
    opens com.librairie.librairie.Model to javafx.fxml;
}