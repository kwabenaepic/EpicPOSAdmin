/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class InventoryValueController implements Initializable {

    @FXML
    private AnchorPane acContent;
    @FXML
    private BorderPane bpRoot;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnAdd;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TableView<?> tblInventory;
    @FXML
    private TableColumn<?, ?> tblClmItemId;
    @FXML
    private TableColumn<?, ?> tblClmItemName;
    @FXML
    private TableColumn<?, ?> tblClmDescription;
    @FXML
    private TableColumn<?, ?> tblClmUnitPrice;
    @FXML
    private TableColumn<?, ?> tblClmQuantity;
    @FXML
    private TableColumn<?, ?> tblClmAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddItemOnAction(ActionEvent event) {
    }

}
