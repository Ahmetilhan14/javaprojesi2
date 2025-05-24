package edu.erciyes.javafex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController2 {

    @FXML private ComboBox<Integer> EastVehicleCount;
    @FXML private ComboBox<Integer> WestVehicleCount;
    @FXML private ComboBox<Integer> SouthVehicleCount;
    @FXML private ComboBox<Integer> NorthVehicleCount;
    @FXML private Button Sec;
    @FXML private Label errorMessageLabel;

    @FXML private Pane northPane;
    @FXML private Pane southPane;
    @FXML private Pane eastPane;
    @FXML private Pane westPane;

    private boolean isUpdating = false;
    private boolean isUpdating2 = false;

    public List<Vehicle> araclarNorth = new ArrayList<>();
    public List<Vehicle> araclarSouth = new ArrayList<>();
    public List<Vehicle> araclarWest = new ArrayList<>();
    public List<Vehicle> araclarEast = new ArrayList<>();

    @FXML
    public void initialize() {
        setItems(NorthVehicleCount, 20);
        setItems(SouthVehicleCount, 20);
        setItems(EastVehicleCount, 20);
        setItems(WestVehicleCount, 20);

        NorthVehicleCount.setValue(3);
        WestVehicleCount.setValue(3);
        EastVehicleCount.setValue(3);
        SouthVehicleCount.setValue(3);

        attachComboBoxListeners();
    }

    private void attachComboBoxListeners() {
        NorthVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        SouthVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        EastVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        WestVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
    }

    public void setItems(ComboBox<Integer> comboBox, int maxValue) {
        comboBox.getItems().clear();
        for (int i = 0; i <= maxValue; i++) {
            comboBox.getItems().add(i);
        }
        comboBox.setVisibleRowCount(5);
    }

    @FXML
    public void changeHandler() {
        if (isUpdating || isUpdating2) return;
        isUpdating = true;

        int east = getComboValue(EastVehicleCount);
        int west = getComboValue(WestVehicleCount);
        int north = getComboValue(NorthVehicleCount);
        int south = getComboValue(SouthVehicleCount);

        int total = east + west + north + south;
        int remaining = 20 - total;

        updateComboBoxWithoutTrigger(EastVehicleCount, east, remaining + east);
        updateComboBoxWithoutTrigger(WestVehicleCount, west, remaining + west);
        updateComboBoxWithoutTrigger(NorthVehicleCount, north, remaining + north);
        updateComboBoxWithoutTrigger(SouthVehicleCount, south, remaining + south);

        isUpdating = false;
    }

    private void updateComboBoxWithoutTrigger(ComboBox<Integer> comboBox, int currentValue, int maxAllowed) {
        comboBox.setOnAction(null);
        comboBox.getItems().clear();

        int limit = Math.min(maxAllowed, 20);
        for (int i = 0; i <= limit; i++) {
            comboBox.getItems().add(i);
        }

        comboBox.setValue(Math.min(currentValue, limit));

        comboBox.setOnAction(e -> {
            if (!isUpdating2) changeHandler();
        });
    }

    private int getComboValue(ComboBox<Integer> comboBox) {
        return comboBox.getValue() != null ? comboBox.getValue() : 0;
    }

    @FXML
    public void secHandler(MouseEvent mouseEvent) {
        clearPanesAndLists();

        selectVehicle(getComboValue(NorthVehicleCount), "north");
        selectVehicle(getComboValue(SouthVehicleCount), "south");
        selectVehicle(getComboValue(WestVehicleCount), "west");
        selectVehicle(getComboValue(EastVehicleCount), "east");
    }

    private void clearPanesAndLists() {
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

    @FXML
    public void rndHandler(MouseEvent mouseEvent) {
        clearPanesAndLists();

        Random rand = new Random();
        int totalVehicles = rand.nextInt(21);
        int[] directions = new int[4];

        for (int i = 0; i < totalVehicles; i++) {
            directions[rand.nextInt(4)]++;
        }

        selectVehicle(directions[0], "north");
        selectVehicle(directions[1], "south");
        selectVehicle(directions[2], "west");
        selectVehicle(directions[3], "east");

        System.out.println("bitti");
    }
}
