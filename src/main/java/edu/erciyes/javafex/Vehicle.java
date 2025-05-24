package edu.erciyes.javafex;
import javafx.scene.layout.Pane;

public interface Vehicle {
    String getName();
    double getSize();
    void ciz(Pane root, double x, double y, String direction);
}
