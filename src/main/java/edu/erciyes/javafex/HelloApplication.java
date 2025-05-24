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


import java.io.IOException;
import java.util.concurrent.Callable;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root=new Pane();
        Scene scene = new Scene(root, 1000, 800);
        Araba a1=new Araba();
        Kamyon k1=new Kamyon();
        Scooter s1=new Scooter();
        Bus b1=new Bus();



       /* stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/


        // Her yön için oluşturulacak nesnelerin tanımlanması
        TrafficLightsDemo northLights=createNorthLight();
        TrafficLightsDemo eastLights=createEastLight();
        TrafficLightsDemo southLights=createSouthtLight();
        TrafficLightsDemo westLights=createWestLight();

        // ışıkların pozisyonlarının ayarlanması
        northLights.setLayoutX(475); northLights.setLayoutY(100);
        southLights.setLayoutX(475); southLights.setLayoutY(600);
        eastLights.setLayoutX(775);  eastLights.setLayoutY(375);
        westLights.setLayoutX(175);   westLights.setLayoutY(375);


        ((Pane) root).getChildren().addAll(northLights, eastLights, southLights, westLights);
        a1.ciz((Pane) root, 500, 300, "north");
        k1.ciz((Pane) root, 500, 300, "east");
        s1.ciz((Pane) root, 500, 300, "south");
        b1.ciz((Pane) root, 500, 300, "west");

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