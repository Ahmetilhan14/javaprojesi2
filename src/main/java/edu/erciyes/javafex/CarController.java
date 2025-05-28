package edu.erciyes.javafex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarController {
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


    private VehicleMove move=new VehicleMove();
    private TrafficLightsDemo northLights, southLights, eastLights, westLights;
    private Group lightsGroup;
    private Group carsGroup;
    private TrafficLightManager trafficLightManager;
    private VehicleManager vehicleManager;


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

        northLights = new TrafficLightsDemo();
        northLights.setPosition(-150,150);
        northPane.getChildren().add(northLights);
        southLights = new TrafficLightsDemo();
        southLights.setPosition(100,0);
        southPane.getChildren().add(southLights);
        eastLights = new TrafficLightsDemo();
        eastLights.setPosition(30,-170);
        eastPane.getChildren().add(eastLights);
        westLights = new TrafficLightsDemo();
        westLights.setPosition(260,80);
        westPane.getChildren().add(westLights);
        trafficLightManager = new TrafficLightManager(northLights, southLights, eastLights, westLights);
        vehicleManager = new VehicleManager(northPane,southPane,eastPane,westPane);
    }
    private void attachComboBoxListeners() {
        NorthVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        SouthVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        EastVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
        WestVehicleCount.setOnAction(e -> { if (!isUpdating2) changeHandler(); });
    }
    public void setItems(ComboBox<Integer> comboBox, int maxValue) {
       // comboBox.getItems().clear();
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
        vehicleManager.selectVehicle(getComboValue(NorthVehicleCount), "north");
        vehicleManager.selectVehicle(getComboValue(SouthVehicleCount), "south");
        vehicleManager.selectVehicle(getComboValue(WestVehicleCount), "west");
        vehicleManager.selectVehicle(getComboValue(EastVehicleCount), "east");
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
    private List<Vehicle> getDirectionList(String direction) {
        switch (direction) {
            case "north": return araclarNorth;
            case "south": return araclarSouth;
            case "east": return araclarEast;
            case "west": return araclarWest;
            default: return new ArrayList<>();
        }
    }
    @FXML
    public void rndHandler(MouseEvent mouseEvent) {
        Random rand = new Random();
        int totalVehicles = rand.nextInt(21);
        int[] directions = new int[4];
        for (int i = 0; i < totalVehicles; i++) {
            directions[rand.nextInt(4)]++;
        }
        vehicleManager.selectVehicle(directions[0], "north");
        vehicleManager.selectVehicle(directions[3], "east");
        vehicleManager.selectVehicle(directions[1], "south");
        vehicleManager.selectVehicle(directions[2], "west");

        System.out.println("bitti");
    }
    public void start(MouseEvent mouseEvent) {
        trafficLightManager.startTrafficLoop(3,3,3,3);
        Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(northLights.isGreen()){
                    move.forward(vehicleManager.araclarNorth);
                }
                if(southLights.isGreen()){
                    move.forward(vehicleManager.araclarSouth);
                }
                if(eastLights.isGreen()){
                    move.forward(vehicleManager.araclarEast);
                }
                if(westLights.isGreen()){
                    move.forward(vehicleManager.araclarWest);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    @FXML
    public void durdur(MouseEvent mouseEvent) {
        move.durdur(); // Animasyonları durdur
    }
    @FXML
    public void devamet(MouseEvent mouseEvent) {
        move.devamEt(); // Kaldığı yerden devam
    }
}

