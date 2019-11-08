/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import epicposadmin.EpicPOSAdmin;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class SplashFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    private EpicPOSAdmin application;
    @FXML
    private ProgressBar pBprogressBar;
    @FXML
    private Button clickme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        pBprogressBar = new ProgressBar();
//        pBprogressBar.setProgress(-1.0f);
        ProgressIndicator progressIndicator = new ProgressIndicator();
        IntegerProperty seconds = new SimpleIntegerProperty();
        pBprogressBar.progressProperty().bind(seconds.divide(50.0));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
                new KeyFrame(Duration.minutes(0.2), e -> {
                    // do anything you need here on completion...
                    System.out.println("Minute over");
                    new SplashScreen().start();
                }, new KeyValue(seconds, 50))
        );
//        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        new SpashScreen().start();
    }

    @FXML
    private void onclickme(ActionEvent event) {
//        pBprogressBar.setProgress(-1.0f);
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {

            try {
                Thread.sleep(100);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        application.gotoLogin();
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    }
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void setApp(EpicPOSAdmin application) {
        this.application = application;
    }

}
