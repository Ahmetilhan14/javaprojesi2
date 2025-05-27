package edu.erciyes.javafex;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleMove {

    private final double SCREEN_HEIGHT = 800;
    private final double SCREEN_WIDTH = 1000;

    private final Map<Vehicle, SequentialTransition> animasyonlar = new HashMap<>();

    public void forward(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            switch (vehicle.direction) {
                case "north":
                    handleNorth(vehicle);
                    break;
                case "south":
                    handleSouth(vehicle);
                    break;
                case "east":
                    handleEast(vehicle);
                    break;
                case "west":
                    handleWest(vehicle);
                    break;
            }
        }
    }

    private void handleNorth(Vehicle vehicle) {
        switch (vehicle.movement) {
            case "forward":
                hareketEt(vehicle, 0, SCREEN_HEIGHT - vehicle.getY() + 100, 4); // 4 saniye toplam süre
                break;
            case "left":
                hareketYap(vehicle, 0, 200, 200, 0, 0);
                break;
            case "right":
                hareketYap(vehicle, 0, 200, -200, 0, 180);
                break;
        }
    }

    private void handleSouth(Vehicle vehicle) {
        switch (vehicle.movement) {
            case "forward":
                hareketEt(vehicle, 0, vehicle.getY() - 500, 4);
                break;
            case "left":
                hareketYap(vehicle, 0, -200, -200, 0, 180);
                break;
            case "right":
                hareketYap(vehicle, 0, -200, 200, 0, 0);
                break;
        }
    }

    private void handleEast(Vehicle vehicle) {
        switch (vehicle.movement) {
            case "forward":
                hareketEt(vehicle, -(vehicle.getX()) + 100, 0, 4);
                break;
            case "left":
                hareketYap(vehicle, -200, 0, 0, 300, 90);
                break;
            case "right":
                hareketYap(vehicle, -500, 0, 0, -300, 270);
                break;
        }
    }

    private void handleWest(Vehicle vehicle) {
        switch (vehicle.movement) {
            case "forward":
                hareketEt(vehicle, SCREEN_WIDTH - vehicle.getX() + 100, 0, 4);
                break;
            case "left":
                hareketYap(vehicle, 200, 0, 0, -200, 270);
                break;
            case "right":
                hareketYap(vehicle, 200, 0, 0, 200, 90);
                break;
        }
    }

    private void hareketEt(Vehicle vehicle, double byX, double byY, double sureSaniye) {
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(sureSaniye / 2), vehicle.getImageView());
        transition1.setByX(byX / 2);
        transition1.setByY(byY / 2);

        PauseTransition pause = new PauseTransition(Duration.seconds(sureSaniye / 2));

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(sureSaniye / 2), vehicle.getImageView());
        transition2.setByX(byX / 2);
        transition2.setByY(byY / 2);

        SequentialTransition sequential = new SequentialTransition(transition1, pause, transition2);

        sequential.setOnFinished(e -> {
            vehicle.getImageView().setVisible(false);
        });

        animasyonlar.put(vehicle, sequential);
        sequential.play();

        vehicle.setPosition(vehicle.getX() + byX, vehicle.getY() + byY);
    }

    private void hareketYap(Vehicle vehicle, double onceX, double onceY, double sonraX, double sonraY, int rotate) {
        TranslateTransition ilk = new TranslateTransition(Duration.seconds(1), vehicle.getImageView());
        ilk.setByX(onceX);
        ilk.setByY(onceY);
        ilk.setOnFinished(e -> vehicle.getImageView().setRotate(rotate));

        TranslateTransition ikinci = new TranslateTransition(Duration.seconds(1), vehicle.getImageView());
        ikinci.setByX(sonraX);
        ikinci.setByY(sonraY);

        SequentialTransition hareket = new SequentialTransition(ilk, ikinci);
        hareket.setOnFinished(e -> vehicle.getImageView().setVisible(false));

        animasyonlar.put(vehicle, hareket);
        hareket.play();

        vehicle.setPosition(vehicle.getX() + onceX + sonraX, vehicle.getY() + onceY + sonraY);
    }

    public void durdur() {
        for (SequentialTransition anim : animasyonlar.values()) {
            anim.pause();
        }
    }

    public void devamEt() {
        for (SequentialTransition anim : animasyonlar.values()) {
            anim.play();
        }
    }
}
