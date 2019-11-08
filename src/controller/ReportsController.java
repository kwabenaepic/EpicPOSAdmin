/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class ReportsController implements Initializable {

    AnchorPane home, inventory, inventoryValue, employees, sales, reports, bestOrWorstSellers, controls, stockSummaryAmbient, meterReconciliation;
    @FXML
    private StackPane holderPaneTwo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
//            home = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            inventory = FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"));
            inventoryValue = FXMLLoader.load(getClass().getResource("/view/InventoryValue.fxml"));
            sales = FXMLLoader.load(getClass().getResource("/view/SalesReports.fxml"));
            bestOrWorstSellers = FXMLLoader.load(getClass().getResource("/view/BestOrWorstSales.fxml"));
//            meterReconciliation = FXMLLoader.load(getClass().getResource("/view/MeterReconciliation.fxml"));
//            controls = FXMLLoader.load(getClass().getResource("/views/Controls.fxml"));
            setNode(sales);
        } catch (IOException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setNode(Node node) {
        holderPaneTwo.getChildren().clear();
        holderPaneTwo.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void lblSwitchSalesReport(ActionEvent event) {
        setNode(sales);
    }

    @FXML
    private void btnSwitchInventoryReport(ActionEvent event) {
        setNode(inventory);
    }

    @FXML
    private void lblSwitchBestOrWorst(ActionEvent event) {
        setNode(bestOrWorstSellers);
    }

    @FXML
    private void btnSwitchInventoryValueReport(ActionEvent event) {
        setNode(inventoryValue);
    }

}
