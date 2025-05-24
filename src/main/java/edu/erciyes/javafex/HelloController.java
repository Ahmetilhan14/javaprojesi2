package edu.erciyes.javafex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;



public class HelloController {

    @FXML
    private Button btnKuzey,btnGuney,btnDogu,btnBati;
    private int sayacK=0,sayacG=0,sayacD=0,sayacB=0;
    private final TrafficLightsDemo trafficlight=new TrafficLightsDemo();
    @FXML
    public void initialize(){
         // Kuzey için ışıklar
        btnKuzey.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        sayacK++;
        yonuIslet("Kuzey",sayacK);
        }
         });

         //Güney için ışıklar

       btnGuney.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               sayacG++;
               yonuIslet("Guney",sayacG);
           }
       });
       // Doğu için yakılan ışıklar
        btnDogu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sayacD++;
                yonuIslet("Dogu",sayacD);
            }
        });
        //Batı için ışıklar
        btnBati.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sayacB++;
                yonuIslet("Bati",sayacB);
            }
        });

    }
    private void yonuIslet(String yon,int sayac){
        int durum=sayac%3;
        switch (durum){
            case 1:
                trafficlight.turnOnRed(yon);
                break;
            case 2:
                trafficlight.turnOYellow(yon);
                break;
            case 3:
                trafficlight.turnOnGreen(yon);
                break;
        }
    }


    }

