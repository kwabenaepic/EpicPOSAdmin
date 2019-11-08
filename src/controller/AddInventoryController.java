/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Product;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tables.ProductManager;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class AddInventoryController implements Initializable {

    @FXML
    private TextField tfItemName;
    @FXML
    private ComboBox<?> cbDepartment;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfSize;
    @FXML
    private TextField tfCostPrice;
    @FXML
    private TextField tfUnitPrice;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfUPC;
    @FXML
    private ComboBox<?> cbVendor;
    @FXML
    private TextField tfReorderPoint;
    @FXML
    private TextField tfALU;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnClose;
    private Stage stage = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cbDepartmentOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        isValidCondition();
        Product bean = new Product();
        bean.setName(tfItemName.getText());
        bean.setDescription(taDescription.getText());
        bean.setCostPrice(Double.parseDouble(tfCostPrice.getText()));
        bean.setUnitPrice(Double.parseDouble(tfUnitPrice.getText()));
        bean.setQuantity(Integer.parseInt(tfQuantity.getText()));
        bean.setReorderLevel(Integer.parseInt(tfReorderPoint.getText()));

        try {
            boolean result = ProductManager.insert(bean);
            showNotification();
            clearFields();

        } catch (Exception ex) {
            Logger.getLogger(AddInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isValidCondition() {
        boolean validCondition = true;

        if (tfItemName.getText().trim().isEmpty() || tfUnitPrice.getText().trim().isEmpty()
             || tfQuantity.getText().trim().isEmpty() || taDescription.getText().trim().isEmpty()
             || tfReorderPoint.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Enter Valid Text");
            String s = "Fields with * can't be left blank.";
            alert.setContentText(s);
            alert.showAndWait();
            validCondition = false;
//            tfUsername.requestFocus();
        } else {
            validCondition = true;
        }

        return validCondition;
    }

    public void clearFields() {
        tfItemName.setText("");
        taDescription.setText("");
        tfUnitPrice.setText("");
        tfCostPrice.setText("");
        tfQuantity.setText("");
        tfReorderPoint.setText("");
        tfUPC.setText("");

    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {

    }

    private void showNotification() {
        Notifications notificationBuilder = Notifications.create()
             .title("Item Saved")
             .text("")
             .graphic(null)
             .hideAfter(Duration.seconds(3))
             .position(Pos.CENTER);
        notificationBuilder.show();
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
