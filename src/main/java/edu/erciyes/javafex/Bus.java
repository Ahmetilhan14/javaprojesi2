package edu.erciyes.javafex;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
public class Bus implements Vehicle{
    private String name = "Otobüs";
    private double size = 18;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    @Override
    public void ciz(Pane root, double x, double y, String direction) {
        Image arabaImage = new Image(getClass().getResource("/bus.png").toExternalForm());
        ImageView imageView = new ImageView(arabaImage);


        switch (direction.toLowerCase()) {
            case "north":
                imageView.setRotate(90);
                y -= 60;
                x += 10;
                break;
            case "east":
                imageView.setRotate(180);
                //x -= 80;
                y += 35;
                break;
            case "south":
                imageView.setRotate(270);
                y += 30;
                x += 10;
                break;
            case "west":
                imageView.setRotate(0);
                x -= 80;
                y += 35;
                break;
        }

        imageView.setFitWidth(80);
        imageView.setFitHeight(15);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        root.getChildren().add(imageView);
    }
}
