
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.net.URL;

import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.util.Callback;

import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;

import beans.Employee;
import beans.Product;

import tables.EmployeeManager;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class EmployeeController implements Initializable {

    private ObservableList<Employee> tableData = FXCollections.observableArrayList();
    @FXML
    private AnchorPane acContent;
    @FXML
    private BorderPane bpRoot;
    @FXML
    private ComboBox<?> cbTankId;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnAdd;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TableView<Employee> tblEmployees;
    @FXML
    private TableColumn<Employee, Integer> tblClmIEmployeeNumber;
    @FXML
    private TableColumn<Employee, String> tblClmFirstName;
    @FXML
    private TableColumn<Employee, String> tblClmLastName;
    @FXML
    private TableColumn<Employee, String> tblClmPhone;
    @FXML
    private TableColumn<Employee, String> tblClmMobile;
    @FXML
    private TableColumn<Employee, String> tblClmEmail;
    @FXML
    private TableColumn<Employee, String> tblClmEmployeeId;
    @FXML
    private TableColumn<Employee, String> tblClmPassword;
    @FXML
    private TableColumn<Employee, String> tblClmImagePath;
    @FXML
    private TableColumn<Employee, String> tblClmAddress;
    @FXML
    private TableColumn<Employee, String> tblClmCity;
    @FXML
    private TableColumn<Record, Boolean> tblClmAction;
    @FXML
    private AnchorPane bpRightAnchor;
    private TextField tfItemName;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private TableColumn<Employee, String> tblClmSecurityGroup;
    @FXML
    private TableColumn<Employee, String> tblClmUsername;
    @FXML
    private ComboBox<?> cbSeurityGroup;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMobile;
    @FXML
    private TextField tfPhone;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextArea taAddress;
    @FXML
    private ImageView picBoxImageView;
    @FXML
    private TextField tfUpload;
    @FXML
    private Button btnUpload;
    @FXML
    private TextField tfEmployeeNumber;
    @FXML
    private Button btnHide;
    @FXML
    private CheckBox cbEnabled;
    @FXML
    private TableColumn<Employee, CheckBox> tblClmEnabled;

    @FXML
    private void btnAddEmployeeOnAction(ActionEvent event) {
        FXMLLoader fXMLLoader = new FXMLLoader();

        fXMLLoader.setLocation(getClass().getResource("/view/AddEmployee.fxml"));

        // initializing the controller
//      DailyStockReceiptsController dailystockReceiptsController = new DailyStockReceiptsController();
//      fXMLLoader.setController(dailystockReceiptsController);
        try {
            fXMLLoader.load();

            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);

            scene.setFill(new Color(0, 0, 0, 0));

            AddEmployeeController addEmployeeController = fXMLLoader.getController();
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
    }

    @FXML
    private void btnHideOnAction(ActionEvent event) {
        splitPane.getItems().remove(bpRightAnchor);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tblEmployees.refresh();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUploadOnAction(ActionEvent event) {
    }

    @FXML
    private void cbAddDailyTankReconByTankIdOnAction(ActionEvent event) {
    }

    @FXML
    private void cbSecurityGroupOnAction(ActionEvent event) {
    }

    private void configureTable() {
        tblClmIEmployeeNumber.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        tblClmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblClmLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblClmPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblClmMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblClmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblClmEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblClmEnabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        tblClmUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblClmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tblClmImagePath.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        tblClmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblClmCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblClmSecurityGroup.setCellValueFactory(new PropertyValueFactory<>("securityGroup"));
        tblClmAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(
                    TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        // Adding the Button to the cell
        tblClmAction.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new EmployeeController.ButtonCell();
            }
        });

        tblEmployees.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblEmployees.setItems(null);

//      actionCol.setSortable(false);
        tableData = FXCollections.observableArrayList();

        try {
            tableData.addAll(EmployeeManager.getEmployeesList());
            tblEmployees.setItems(tableData);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        splitPane.getItems().remove(bpRightAnchor);
        configureTable();
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
    }

    // Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {

        final Button editButton = new Button("");
        final Button deleteButton = new Button("");

        ButtonCell() {
            editButton.setId("btnEdit");
            editButton.setPrefWidth(31);
            editButton.setPrefHeight(31);
            deleteButton.setId("btnDelete");
            deleteButton.setPrefWidth(31);
            deleteButton.setPrefHeight(31);

            // Action when the button is pressed
            editButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {

                    // get Selected Item
                    if (splitPane.getItems().contains(bpRightAnchor)) {
                        Employee currentEmployee = (Employee) ButtonCell.this.getTableView()
                                .getItems()
                                .get(ButtonCell.this.getIndex());

                        tfFirstName.setText(currentEmployee.getFirstName());
                        tfLastName.setText(currentEmployee.getLastName());
                        tfPhone.setText(currentEmployee.getPhone());
                        tfMobile.setText(currentEmployee.getMobile());
                        tfEmail.setText(currentEmployee.getEmail());
                        pfPassword.setText(currentEmployee.getPassword());
                        taAddress.setText(currentEmployee.getAddress());

                        if (currentEmployee.getImage() != null) {
                            ByteArrayInputStream byteArrayInputStream = null;

                            try {
                                byteArrayInputStream = new ByteArrayInputStream(
                                        currentEmployee.getImage().getBytes(
                                                1, (int) currentEmployee.getImage().length()));
                            } catch (SQLException ex) {
                                Logger.getLogger(EmployeeController.class.getName())
                                        .log(Level.SEVERE, null, ex);
                            }
                            picBoxImageView.setImage(new Image(byteArrayInputStream));
                        } else {
                            picBoxImageView.setImage(null);
                        }

                        tfEmployeeNumber.setText(currentEmployee.getUsername());
                        cbEnabled.setSelected(currentEmployee.getEnabled());
                    } else {
                        splitPane.getItems().add(1, bpRightAnchor);
                        splitPane.setDividerPosition(1, 0.8);

                        Employee currentEmployee = (Employee) ButtonCell.this.getTableView()
                                .getItems()
                                .get(ButtonCell.this.getIndex());

                        tfFirstName.setText(currentEmployee.getFirstName());
                        tfLastName.setText(currentEmployee.getLastName());
                        tfPhone.setText(currentEmployee.getPhone());
                        tfMobile.setText(currentEmployee.getMobile());
                        tfEmail.setText(currentEmployee.getEmail());
                        pfPassword.setText(currentEmployee.getPassword());
                        taAddress.setText(currentEmployee.getAddress());

                        if (currentEmployee.getImage() != null) {
                            ByteArrayInputStream byteArrayInputStream = null;

                            try {
                                byteArrayInputStream = new ByteArrayInputStream(
                                        currentEmployee.getImage().getBytes(
                                                1, (int) currentEmployee.getImage().length()));
                            } catch (SQLException ex) {
                                Logger.getLogger(EmployeeController.class.getName())
                                        .log(Level.SEVERE, null, ex);
                            }

                            picBoxImageView.setImage(new Image(byteArrayInputStream));
                        } else {
                            picBoxImageView.setImage(null);
                        }

                        tfEmployeeNumber.setText(currentEmployee.getUsername());
                        cbEnabled.setSelected(currentEmployee.getEnabled());
                    }
                }
            });

            // Action when the button is pressed
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {

                    // get Selected Item
                    Product currentProduct = (Product) ButtonCell.this.getTableView()
                            .getItems()
                            .get(ButtonCell.this.getIndex());

                    // saleItems.remove(currentProduct.getProductNumber());
                    // tableItems.remove(currentProduct);
                    // if (saleItems.isEmpty()) {
                    // reSetItems();
                    // }
                    // reSetItems();
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);

            if (!empty) {
                HBox pane = new HBox(editButton, deleteButton);

                pane.setSpacing(10);
                pane.setAlignment(Pos.CENTER);
                setGraphic(pane);
            } else {
                setGraphic(null);
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
