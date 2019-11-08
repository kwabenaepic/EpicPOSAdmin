/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Product;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tables.ProductManager;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class InventoryController implements Initializable {

    @FXML
    private AnchorPane acContent;
    @FXML
    private ComboBox<?> cbTankId;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<Product> tblInventory;

    @FXML
    private TableColumn<Product, String> tblClmItemName;
    @FXML
    private TableColumn<Product, String> tblClmDescription;
    @FXML
    private TableColumn<Product, Double> tblClmCostPrice;
    @FXML
    private TableColumn<Product, Integer> tblClmQuantity;
    @FXML
    private TableColumn<Product, Integer> tblClmReorderLevel;
    @FXML
    private TableColumn<Product, String> tblClmCategory;
    @FXML
    private TableColumn<Product, Integer> tblClmItemId;
    @FXML
    private TableColumn<Record, Boolean> tblClmAction;
    @FXML
    private TableColumn<Product, String> tblClmVendorId;
    @FXML
    private TableColumn<Product, Double> tblClmUnitPrice;
    @FXML
    private TableColumn<Product, String> tblClmAttribute;
    @FXML
    private TableColumn<Product, String> tblClmSize;
    @FXML
    private TableColumn<Product, String> tblClmALU;
    @FXML
    private TableColumn<Product, String> tblClmUPC;
    private ObservableList<Product> tableData = FXCollections.observableArrayList();
    @FXML
    private TextField tfItemName;
    @FXML
    private ComboBox<?> cbDepartment;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfAttribute;
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
    private BorderPane bpRoot;
    @FXML
    private AnchorPane bpRightAnchor;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ComboBox<?> cbVendor;
    @FXML
    private TextField tfOrderCost;
    @FXML
    private TextField tfReorderPoint;
    @FXML
    private TextField tfItemNumber;
    @FXML
    private TextField tfALU;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    private ObservableList<Product> masterData;
    @FXML
    private Button btnHide;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
//        bpRightAnchor.setMinWidth(0);
        splitPane.getItems().remove(bpRightAnchor);

        configureTable();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(tableData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (product.getProductId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblInventory.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblInventory.setItems(sortedData);

    }

    @FXML
    private void cbAddDailyTankReconByTankIdOnAction(ActionEvent event) {
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddItemOnAction(ActionEvent event) {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/AddInventory.fxml"));
        // initializing the controller
//        DailyStockReceiptsController dailystockReceiptsController = new DailyStockReceiptsController();
//        fXMLLoader.setController(dailystockReceiptsController);
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddInventoryController addInventoryController = fXMLLoader.getController();
//            AddCustomerController addCustomerController = fXMLLoader.getController();
//            media.setId(userId);
//            dailystockReceiptsController.setNameMedia(nameMedia);
//            dailystockReceiptsController.lblCustomerContent.setText("ADD CUSTOMER");
//            dailystockReceiptsController.btnUpdate.setVisible(false);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configureTable() {
        tblClmItemId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmCostPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        tblClmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmReorderLevel.setCellValueFactory(new PropertyValueFactory<>("reorderLevel"));
        tblClmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmVendorId.setCellValueFactory(new PropertyValueFactory<>("vendorId"));
//        tblClmAttribute.setCellValueFactory(new PropertyValueFactory<>("attribute"));
//        tblClmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        tblClmALU.setCellValueFactory(new PropertyValueFactory<>("ALU"));
        tblClmUPC.setCellValueFactory(new PropertyValueFactory<>("UPC"));
        tblClmAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        tblClmAction.setCellFactory(
             new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }

        });

        // In order to limit the amount of setup in Getting Started we set the width
        // of the 3 columns programmatically but one can do it from SceneBuilder.
        tblClmItemId.setPrefWidth(40);
//        quantityColumn.setPrefWidth(75);
//        priceColumn.setPrefWidth(75);
        tblClmItemName.setPrefWidth(100);
//
        tblClmItemId.setMinWidth(40);
//        itemIdColumn.setMinWidth(50);
//        quantityColumn.setMinWidth(75);
        tblClmItemName.setMinWidth(100);
//
        tblClmItemId.setMaxWidth(100);
//        itemIdColumn.setMaxWidth(750);
//        quantityColumn.setMaxWidth(750);
//        descriptionColumn.setMaxWidth(4430);
//        //Insert Button
//        TableColumn col_action = new TableColumn<>("Action");
//        table.getColumns().add(col_action);
        tblInventory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblInventory.setItems(null);
//
        tableData = FXCollections.observableArrayList();
        try {
            tableData.addAll(ProductManager.getProductsList());
            tblInventory.setItems(tableData);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnHideEditOnAction(ActionEvent event) {
//        splitPane.setDividerPositions(0.8);
        splitPane.getItems().add(1, bpRightAnchor);
        splitPane.setDividerPosition(1, 0.8);
    }

    @FXML
    private void cbDepartmentOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
    }

    @FXML
    private void btnHideOnAction(ActionEvent event) {
        splitPane.getItems().remove(bpRightAnchor);

    }

    //Define the button cell
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
            //Action when the button is pressed

            editButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    if (splitPane.getItems().contains(bpRightAnchor)) {
                        Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        tfItemName.setText(currentProduct.getName());
                        taDescription.setText(currentProduct.getDescription());
                        tfCostPrice.setText(currentProduct.getCostPrice().toString());
                        tfUnitPrice.setText(currentProduct.getUnitPrice().toString());
                        tfQuantity.setText(currentProduct.getQuantity().toString());
                        tfReorderPoint.setText(currentProduct.getReorderLevel().toString());
                        tfItemNumber.setText(currentProduct.getProductId().toString());

                    } else {

                        splitPane.getItems().add(1, bpRightAnchor);
                        splitPane.setDividerPosition(2, 0.8);
                        Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        tfItemName.setText(currentProduct.getName());
                        taDescription.setText(currentProduct.getDescription());
                        tfCostPrice.setText(currentProduct.getCostPrice().toString());
                        tfUnitPrice.setText(currentProduct.getUnitPrice().toString());
                        tfQuantity.setText(currentProduct.getQuantity().toString());
                        tfReorderPoint.setText(currentProduct.getReorderLevel().toString());
                        tfItemNumber.setText(currentProduct.getProductId().toString());
                    }
//                        splitPane.getItems().add(1, bpRightAnchor);
//                        splitPane.setDividerPosition(1, 0.8);
//                    Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
//                    tfItemName.setText(currentProduct.getName());
//                    currentProduct.setQuantity(Integer.parseInt(showDialog()));
//                    tableItems.remove(currentProduct);
////                    saleItems.remove(currentProduct.getProductId());
//                    tableItems.add(currentProduct);
//                    calcSubTotal();

                }
            });

            //Action when the button is pressed
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

//                    saleItems.remove(currentProduct.getProductNumber());
//                    tableItems.remove(currentProduct);
//                    if (saleItems.isEmpty()) {
//                        reSetItems();
//                    }
//                    reSetItems();
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
