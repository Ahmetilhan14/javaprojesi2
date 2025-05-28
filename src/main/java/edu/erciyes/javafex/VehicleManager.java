package edu.erciyes.javafex;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleManager {
    private Pane northPane;
    private Pane southPane;
    private Pane eastPane;
    private Pane westPane;

    public List<Vehicle> araclarNorth = new ArrayList<>();
    public List<Vehicle> araclarSouth = new ArrayList<>();
    public List<Vehicle> araclarWest = new ArrayList<>();
    public List<Vehicle> araclarEast = new ArrayList<>();

    public VehicleManager(Pane northPane, Pane southPane, Pane eastPane, Pane westPane) {
        this.northPane = northPane;
        this.southPane = southPane;
        this.eastPane = eastPane;
        this.westPane = westPane;
    }

    public void clearPanesAndLists() {
        northPane.getChildren().clear();
        southPane.getChildren().clear();
        eastPane.getChildren().clear();
        westPane.getChildren().clear();

        araclarNorth.clear();
        araclarSouth.clear();
        araclarWest.clear();
        araclarEast.clear();
    }

    public void selectVehicle(int count, String direction) {
        Random rand = new Random();
        List<Vehicle> selectedList = getDirectionList(direction);
        Pane pane = getDirectionPane(direction);

        double startX = 0;
        double startY = 0;
        double spacing = 0;

        for (int i = 0; i < count; i++) {
            int rnd = rand.nextInt(4);
            Vehicle arac;
            switch (rnd) {
                case 0: arac = new Araba(); break;
                case 1: arac = new Kamyon(); break;
                case 2: arac = new Bus(); break;
                case 3: arac = new Scooter(); break;
                default: arac = new Araba(); break;
            }

            selectedList.add(arac);
            double x = startX;
            double y = startY;

            switch (direction) {
                case "north":
                    y = 300 - spacing;
                    spacing += 45;
                    break;
                case "south":
                    y = spacing;
                    spacing += 45;
                    break;
                case "east":
                    x = spacing;
                    spacing += (rnd == 0) ? 25 : 45;
                    break;
                case "west":
                    x = 300 - spacing;
                    spacing += (rnd == 0) ? 25 : 45;
                    break;
            }

            arac.setDirection(direction);
            arac.setMovement("left");
            arac.setPosition(x, y);
            arac.ciz(pane, x, y, direction);
        }
    }

    private List<Vehicle> getDirectionList(String direction) {
        switch (direction) {
            case "north": return araclarNorth;
            case "south": return araclarSouth;
            case "east": return araclarEast;
            case "west": return araclarWest;
            default: return new ArrayList<>();
        }
    }

    private Pane getDirectionPane(String direction) {
        switch (direction) {
            case "north": return northPane;
            case "south": return southPane;
            case "east": return eastPane;
            case "west": return westPane;
            default: return new Pane();
        }
    }
}
