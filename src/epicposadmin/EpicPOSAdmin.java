
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package epicposadmin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.geometry.Rectangle2D;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import beans.Employee;
import beans.EmployeeLogins;
import beans.Product;

import controller.DashboardController;
import controller.LoginController;
import controller.SplashFXMLController;

import tables.EmployeeLoginsManager;
import tables.EmployeeManager;
import tables.ProductManager;

/**
 *
 * @author KwabenaEpic
 */
public class EpicPOSAdmin extends Application {

    private static EpicPOSAdmin application;
    public Stage stage;
    private Employee loggedUser;
    public String sessionId;

    public EpicPOSAdmin() {
        application = this;
    }

    private Initializable dashboard(String fxml) throws IOException {
        stage.close();
        Stage stag = new Stage();
        this.stage = stag;
        FXMLLoader loader = new FXMLLoader();
        InputStream in = EpicPOSAdmin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(EpicPOSAdmin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        // Make scene occupy full screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(page, screenBounds.getWidth(), screenBounds.getHeight());
//      Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.sizeToScene();
        stage.show();

        stage.setMaximized(true);
        return (Initializable) loader.getController();
    }

    public void gotoDashboard() {
        try {
            DashboardController dashboardFrm = (DashboardController) dashboard("/view/Dashboard.fxml");
            dashboardFrm.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            LoginController loginFrm = (LoginController) login("/view/Login.fxml");
            loginFrm.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoProfile() {

    }

    public void gotoSplash() {
        try {
            SplashFXMLController splashFrm = (SplashFXMLController) splash("/view/SplashFXML.fxml");
            splashFrm.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable login(String fxml) throws IOException {
        stage.close();
        Stage stag = new Stage();
        this.stage = stag;
        FXMLLoader loader = new FXMLLoader();
        InputStream in = EpicPOSAdmin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(EpicPOSAdmin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        // Make scene occupy full screen
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//      Scene scene = new Scene(page, screenBounds.getWidth(), screenBounds.getHeight());
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.sizeToScene();
        this.stage.show();
//      stage.setMaximized(true);
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        Application.launch(EpicPOSAdmin.class, (java.lang.String[]) null);
    }

    private void readCSV() throws Exception {
        String CsvFile = ("C:/Users/Junior/Documents/NetBeansProjects/EpicPOSAdmin/src/InventoryItems.csv");
//      Classader classLoader = getClass().getClassLoader();
//      InputStream fileIs = classLoader.getResourceAsStream("./es1.csv");
//      getClass().getResource("/views/Calculator.fxml")
        String FieldDelimiter = ",";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                Product record = new Product();
                record.setName(fields[0]);
                record.setCategory((fields[1]));
//                record.setAttribute((fields[2]));
//                record.setSize((fields[3]));
//                record.setALU((fields[4]));
                record.setUPC((fields[5]));
                record.setVendorCode((fields[6]));
                record.setDescription((fields[7]));
                record.setCostPrice(Double.parseDouble(fields[8]));
                record.setUnitPrice(Double.parseDouble(fields[9]));
                record.setQuantity(Integer.parseInt(fields[10]));
                record.setReorderLevel(Integer.parseInt(fields[11]));
//              record.setProductId(Integer.parseInt(fields[13]));
                boolean result = ProductManager.insert(record);

//              System.out.println(record.getDateCreated());
                if (result) {//
                    System.out.println("Daily Stock for   was inserted!");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = EpicPOSAdmin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(EpicPOSAdmin.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        // Make scene occupy full screen
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//      Scene scene = new Scene(page, screenBounds.getWidth(), screenBounds.getHeight());
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();

//      stage.setMaximized(true);
        return (Initializable) loader.getController();
    }

    private Initializable splash(String fxml) throws IOException {
        stage.close();
        Stage stag = new Stage();
        this.stage = stag;
        FXMLLoader loader = new FXMLLoader();
        InputStream in = EpicPOSAdmin.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(EpicPOSAdmin.class.getResource(fxml));
        AnchorPane page;

        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        // Make scene occupy full screen
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//      Scene scene = new Scene(page, screenBounds.getWidth(), screenBounds.getHeight());
        Scene scene = new Scene(page);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.sizeToScene();
        this.stage.show();

//      stage.setMaximized(true);
        return (Initializable) loader.getController();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//      readCSV();
        try {
            stage = primaryStage;
//          gotoSplash();
            gotoLogin();
//          gotoDashboard();
        } catch (Exception ex) {
            Logger.getLogger(EpicPOSAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userLogging(String username, String password) throws SQLException {
        loggedUser = EmployeeManager.validate(username, password);
        return true;
    }

    public void userLogout() {
        String timeStamp;
        Format formatter;
        Date date = new Date();
        // 29-Jan-02
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        timeStamp = formatter.format(date);
        EmployeeLogins bean = new EmployeeLogins();

        try {
            boolean result = EmployeeLoginsManager.updateLogout(bean, this.sessionId);
        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loggedUser = null;
        gotoLogin();
    }

    public static EpicPOSAdmin getInstance() {
        return application;
    }

    public Employee getLoggedUser() {
        return loggedUser;
    }

    public Stage getPrimaryStage() {
        return stage;
    }
}
