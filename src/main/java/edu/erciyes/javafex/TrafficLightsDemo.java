package edu.erciyes.javafex;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;




public class TrafficLightsDemo extends StackPane {
   // Kırmızı sarı ve yeşil ışıklarla arka plandaki çerçevenin tanımlanması
    private Circle kirmizi,yesil,sari;
    private Rectangle arkaPlan;


    // Trafik Işıklarının tanımlandığı özelliklerinin girildiği contructor

    public TrafficLightsDemo() {
        kirmizi = new Circle(10,Color.RED);
        yesil = new Circle(10,Color.GREEN);
        sari = new Circle(10,Color.YELLOW);

        VBox isiklar = new VBox(5,kirmizi,sari,yesil);
        isiklar.setAlignment(Pos.CENTER);

        arkaPlan = new Rectangle(40,90);
        arkaPlan.setFill(Color.TRANSPARENT);
        arkaPlan.setStroke(Color.ORANGE);

        this.getChildren().addAll(isiklar,arkaPlan);
    }

    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
    // Kırmızı Işığı aktif eden fonksiyon
    public void turnOnRed(){
        kirmizi.setFill(Color.RED);
        kirmizi.setStroke(Color.BLACK);
        yesil.setFill(Color.GRAY);
        yesil.setStroke(Color.BLACK);
        sari.setFill(Color.GRAY);
        sari.setStroke(Color.BLACK);
    }
    // Sarı Işığı aktif eden fonksiyon
    public void turnOnYellow(){
        kirmizi.setFill(Color.GRAY);
        kirmizi.setStroke(Color.BLACK);
        yesil.setFill(Color.GRAY);
        yesil.setStroke(Color.BLACK);
        sari.setFill(Color.YELLOW);
        sari.setStroke(Color.BLACK);
    }
    //Yeşil ışığı aktif eden fonksiyon
    public void turnOnGreen(){
        kirmizi.setFill(Color.GRAY);
        kirmizi.setStroke(Color.BLACK);
        yesil.setFill(Color.GREEN);
        yesil.setStroke(Color.BLACK);
        sari.setFill(Color.GRAY);
        sari.setStroke(Color.BLACK);
    }
}
