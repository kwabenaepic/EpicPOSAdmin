package controller;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import java.sql.SQLException;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import beans.Employee;
import beans.EmployeeLogins;
import database.ConnectionManager;

import epicposadmin.EpicPOSAdmin;
import java.util.Random;
import javafx.application.Platform;
import org.controlsfx.control.ToggleSwitch;

import tables.EmployeeLoginsManager;

//import org.controlsfx.dialog.Dialog;
//import org.controlsfx.dialog.Dialogs;
import tables.EmployeeManager;

/**
 * Login Controller.
 */
public class LoginController implements Initializable {

    private Stage stage = null;
    TextField userId;
    TextField password;
    Button login;
    @FXML
    Label errorMessage;
    private EpicPOSAdmin application;
    private Employee loggedUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnClose;
    private InetAddress ip;
    private static int sessionID = 0;
    private String currentSessionId;
    @FXML
    private ToggleSwitch tsConnection;

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Boolean status = ConnectionManager.getInstance().getConnectionStatus();
        if (status == false) {
            System.out.println("Connection Lost");
            tsConnection.setSelected(false);
            btnLogin.setDisable(true);
        } else {
            tsConnection.setSelected(true);
            btnLogin.setDisable(false);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        Boolean status = ConnectionManager.getInstance().getConnectionStatus();
                        if (status == false) {
                            System.out.println("Connection Lost");
                            tsConnection.setSelected(false);
                            btnLogin.setDisable(true);
                        } else {
                            tsConnection.setSelected(true);
                            btnLogin.setDisable(false);
                        }
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        //don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        try {
            this.ip = InetAddress.getLocalHost();
//          System.out.println(ip.getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentSessionId = generateSessionID();
    }

    @FXML
    public void processLogin(ActionEvent event) throws SQLException {
        if (application == null) {
        } else {
            if (isValidCondition()) {
                Employee bean = EmployeeManager.validate(tfUsername.getText(), tfPassword.getText());

                if (bean == null) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Incorrect Username / Password");
                    String s = "Username/Password is Incorrect";
                    alert.setContentText(s);
                    alert.showAndWait();
                    tfUsername.requestFocus();
                    tfPassword.setText("");
                    errorMessage.setText("Username/Password is incorrect");
                } else {
                    if (!application.userLogging(tfUsername.getText(), tfPassword.getText())) {
                        errorMessage.setText("Username/Password is incorrect");
                    }

                    String timeStamp;
                    String loginTime;
                    Format formatter;
                    Date date = new Date();

                    // 29-Jan-02
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    timeStamp = formatter.format(date);

                    EmployeeLogins beans = new EmployeeLogins();

                    beans.setDate(timeStamp);
                    beans.setSessionId(currentSessionId);
                    beans.setUserName(bean.getUsername());
                    beans.setStatus(1);
                    beans.setSalesOutletId(ip.getHostName());
                    application.sessionId = currentSessionId;
                    try {
                        boolean result = EmployeeLoginsManager.insertLogin(beans);
                    } catch (Exception ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    application.gotoDashboard();
                }
            }
        }
    }

    public void setApp(EpicPOSAdmin application) {
        this.application = application;
    }

    private boolean isValidCondition() {
        boolean validCondition = true;

        if (tfUsername.getText().trim().isEmpty() || tfPassword.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Enter Valid Text");
            String s = "Text should be at least 5 characters long. " + "Enter valid text and save.";
            alert.setContentText(s);
            alert.showAndWait();
            validCondition = false;
            tfUsername.requestFocus();
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    public static int ticketGenerator() {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((100 - 1) + 1) + 1;
        return randomNum;
    }

    private String generateSessionID() {
//        sessionID++;
        return (ip.getHostName() + ticketGenerator());
    }
}



//~ Formatted by Jindent --- http://www.jindent.com
