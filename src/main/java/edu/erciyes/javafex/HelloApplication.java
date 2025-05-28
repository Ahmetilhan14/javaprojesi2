package edu.erciyes.javafex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;


import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.concurrent.Callable;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 650);

        stage.setTitle("Trafik Işıkları");
        stage.setScene(scene);
        stage.show();

    }
    // Batı Işıklarını üreten fonksiyon
    private TrafficLightsDemo createWestLight() {
        return new TrafficLightsDemo();
    }
    // Güney Işıklarını üreten fonksiyon
    private TrafficLightsDemo createSouthtLight() {
        return new TrafficLightsDemo();
    }
    // Doğu Işıklarını üreten fonksiyon
    private TrafficLightsDemo createEastLight() {
        return new TrafficLightsDemo();
    }
    // Kuzey Işıklarını üreten fonksiyon
    private TrafficLightsDemo createNorthLight() {
        return new TrafficLightsDemo();
    }

    public static void main(String[] args) {
        launch();
    }
}