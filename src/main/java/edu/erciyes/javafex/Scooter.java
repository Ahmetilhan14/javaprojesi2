package edu.erciyes.javafex;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Scooter implements Vehicle{
    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getSize() {
        return 0;
    }

    @Override
    public void ciz(Pane root, double x, double y, String direction) {
        Image arabaImage = new Image(getClass().getResource("/scooter.png").toExternalForm());
        ImageView imageView = new ImageView(arabaImage);

        switch (direction.toLowerCase()) {
            case "north":
                imageView.setRotate(270);
                y -= 80;
                x += 30;
                break;
            case "east":
                imageView.setRotate(0);
                y += 10;
                break;
            case "south":
                imageView.setRotate(90);
                x += 30;
                break;
            case "west":
                imageView.setRotate(180);
                x -= 40;
                y += 10;
                break;
        }

        imageView.setFitWidth(30);
        imageView.setFitHeight(20);

        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

        root.getChildren().add(imageView);
    }
}
