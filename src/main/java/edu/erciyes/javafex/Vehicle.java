package edu.erciyes.javafex;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

abstract class Vehicle {
    protected double x;
    protected double y;
    protected String movement;
    protected String direction;
    protected ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }


    public void setMovement(String movement) {
        this.movement = movement;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() { return x; }
    public double getY() { return y; }

    public abstract String getName();
    public abstract double getSize();


    abstract void ciz(Pane root, double x, double y, String direction);

}
