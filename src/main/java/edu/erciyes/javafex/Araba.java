package edu.erciyes.javafex;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.application.Application;

public class Araba implements Vehicle{
    final private String name = "Araba";
    final private double size = 12.0;


    @Override
    public double getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void ciz(Pane root, double x, double y, String direction) {
        Image arabaImage = new Image(getClass().getResource("/car.png").toExternalForm());
        ImageView imageView = new ImageView(arabaImage);

        switch (direction.toLowerCase()) {
            case "north":
                imageView.setRotate(90);
                y -= 80;
                x += 30;
                break;
            case "east":
                imageView.setRotate(180);
                y += 10;
                break;
            case "south":
                imageView.setRotate(270);
                x += 30;
                break;
            case "west":
                imageView.setRotate(0);
                x -= 40;
                y += 10;
                break;
        }

        imageView.setFitWidth(30);
        imageView.setFitHeight(40);

        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

        root.getChildren().addAll(imageView);
    }
}
