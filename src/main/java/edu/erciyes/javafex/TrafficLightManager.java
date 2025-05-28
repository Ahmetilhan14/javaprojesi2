package edu.erciyes.javafex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
public class TrafficLightManager {
    private static final double YELLOW_DURATION = 1.0;

    public final TrafficLightsDemo northLights;
    public final TrafficLightsDemo southLights;
    public final TrafficLightsDemo eastLights;
    public final TrafficLightsDemo westLights;

    public TrafficLightManager(TrafficLightsDemo north, TrafficLightsDemo east, TrafficLightsDemo south, TrafficLightsDemo west) {
        this.northLights = north;
        this.eastLights = east;
        this.southLights = south;
        this.westLights = west;
    }

    public void startTrafficLoop(double northGreenDuration, double eastGreenDuration, double southGreenDuration, double westGreenDuration) {
        Timeline timeline = new Timeline();
        double currentTime = 0;

        // Başta tümü kırmızı
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> allRed()));

        // Kuzey
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> northLights.turnOnRed()));
        currentTime += YELLOW_DURATION;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> northLights.turnOnYellow()));
        currentTime += 1;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> northLights.turnOnGreen()));
        currentTime += northGreenDuration;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> northLights.turnOnRed()));

        // Doğu
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> eastLights.turnOnYellow()));
        currentTime += YELLOW_DURATION;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> eastLights.turnOnGreen()));
        currentTime += eastGreenDuration;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> eastLights.turnOnRed()));

        // Güney
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> southLights.turnOnYellow()));
        currentTime += YELLOW_DURATION;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> southLights.turnOnGreen()));
        currentTime += southGreenDuration;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> southLights.turnOnRed()));

        // Batı
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> westLights.turnOnYellow()));
        currentTime += YELLOW_DURATION;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> westLights.turnOnGreen()));
        currentTime += westGreenDuration;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTime), e -> {
            westLights.turnOnRed();
            allRed();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void allRed() {
        northLights.turnOnRed();
        southLights.turnOnRed();
        eastLights.turnOnRed();
        westLights.turnOnRed();
    }
}

