module edu.erciyes.javafex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;


    opens edu.erciyes.javafex to javafx.fxml;
    exports edu.erciyes.javafex;
}