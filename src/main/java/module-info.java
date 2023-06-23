module com.librairie.librairie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;

    requires org.controlsfx.controls;
    requires mysql.connector.java;
    requires java.sql;
    requires lombok;

    requires org.apache.pdfbox;
    requires java.desktop;

    opens com.librairie.librairie to javafx.fxml, java.xml.bind;
    exports com.librairie.librairie;

    exports com.librairie.librairie.Controller;
    opens com.librairie.librairie.Controller to javafx.fxml, java.xml.bind;

    exports com.librairie.librairie.Model;
    opens com.librairie.librairie.Model to javafx.fxml, java.xml.bind;
}