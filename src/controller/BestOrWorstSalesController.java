/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ProductsInTransaction;
import epicposadmin.EpicPOSAdmin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import tables.ProductsInTransactionManager;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class BestOrWorstSalesController implements Initializable {

    private EpicPOSAdmin application;
    @FXML
    private DatePicker dpDateFrom;
    @FXML
    private DatePicker dpDateTo;
    @FXML
    private TableView<ProductsInTransaction> tblInventory;
    @FXML
    private TableColumn<ProductsInTransaction, String> tblClmItemId;
    @FXML
    private TableColumn<ProductsInTransaction, String> tblClmItemName;
    @FXML
    private TableColumn<ProductsInTransaction, String> tblClmDescription;
    @FXML
    private TableColumn<ProductsInTransaction, Integer> tblClmQuantity;
    @FXML
    private TableColumn<ProductsInTransaction, Double> tblClmUnitPrice;
    private ObservableList<ProductsInTransaction> tableDataProduct = FXCollections.observableArrayList();
    @FXML
    private Button btnExportPdf;
    @FXML
    private Button btnExportExcel;
    @FXML
    private Button btnShowRange;
    @FXML
    private ComboBox<String> cbDateRange;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    private ObservableList<String> dateRange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateRange = FXCollections.observableArrayList();
        dateRange.add("Today");
        dateRange.add("Yesterday");
        dateRange.add("This Week");
        dateRange.add("Last Week");
        dateRange.add("This Month");
        dateRange.add("Last Month");
        dateRange.add("All Sales");
        cbDateRange.setItems(dateRange);
        cbDateRange.setVisibleRowCount(5);

        configureSaleItemTable();
    }

    private void configureSaleItemTable() {
        tblClmItemId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmItemName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityBought"));
        tblClmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblInventory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblInventory.setItems(null);
//
        tableDataProduct = FXCollections.observableArrayList();
        try {
            tableDataProduct.addAll(ProductsInTransactionManager.getBestSelling());
            tblInventory.setItems(tableDataProduct);
        } catch (SQLException ex) {
            Logger.getLogger(BestOrWorstSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
// tableDataProduct.addAll(ProductsInTransactionManager.getProductsList(selectedInvoice.getTicketNumber()));
    }

    @FXML
    private void btnShowRnangeOnAction(ActionEvent event) {
        if (dpDateFrom.getValue() == null && dpDateTo.getValue() == null) {
            dpDateFrom.requestFocus();

        } else {
            tblInventory.getItems().clear();
//            tableDataProduct.addAll(ProductsInTransactionManager.getSaleportBetweenDateRange(dpDateFrom.getValue().toString(), dpDateTo.getValue().toString()));
            tblInventory.setItems(tableDataProduct);
        }
    }

    public void exportReportExcel() throws JRException, IOException {
        String sourceFileName = ("src\\reports\\bestOrWorstSellers.jasper");
        ObservableList<ProductsInTransaction> beanSaleItemList = FXCollections.observableArrayList();
        for (ProductsInTransaction model : tableDataProduct) {
            ProductsInTransaction beanSaleItem = new ProductsInTransaction();
            beanSaleItem.setProductId(model.getProductId());
            beanSaleItem.setProductName(model.getProductName());
            beanSaleItem.setQuantityBought(model.getQuantityBought());
            beanSaleItem.setTotal(model.getTotal());
            beanSaleItem.setTicketNumber(model.getTicketNumber());
            beanSaleItem.setUnitPrice(model.getUnitPrice());
            beanSaleItem.setProductDescription(model.getProductDescription());
            beanSaleItemList.add(beanSaleItem);

        }
//        for (ProductsInTransaction beanSaleItemList1 : beanSaleItemList) {
//            System.out.println(beanSaleItemList1.getProductId());
//        }
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanSaleItemList);
        HashMap parameters = new HashMap<>();
//        parameters.put("receiptDate", dateLocal.getText());
//        System.out.print("Hello");
        try {
            JasperPrint printFileName = JasperFillManager.fillReport(
                    sourceFileName, parameters, beanColDataSource);

            if (printFileName != null) {
                String outPdfName = "BestOrWorstSale.xlsx";
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setInitialDirectory(new File("src"));

                directoryChooser.setTitle("Save File To");
                EpicPOSAdmin instance = EpicPOSAdmin.getInstance();
                File selectedDirectory = directoryChooser.showDialog(instance.getPrimaryStage());
                System.out.print(selectedDirectory);

                JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter
                exporter.setExporterInput(new SimpleExporterInput(printFileName)); // set compiled report as input exporter
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(selectedDirectory.getAbsolutePath() + "/" + outPdfName)); // set output file via path with filename
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(false); // setup configuration
                configuration.setRemoveEmptySpaceBetweenRows(true);
                configuration.setDetectCellType(true);
                exporter.setConfiguration(configuration); // set configuration
                exporter.exportReport();
            }
//            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void exportReportPdf() throws JRException, IOException {
        String sourceFileName = ("src\\reports\\bestOrWorstSellers.jasper");
        ObservableList<ProductsInTransaction> beanSaleItemList = FXCollections.observableArrayList();

        for (ProductsInTransaction model : tableDataProduct) {
            ProductsInTransaction beanSaleItem = new ProductsInTransaction();
            beanSaleItem.setProductId(model.getProductId());
            beanSaleItem.setProductName(model.getProductName());
            beanSaleItem.setQuantityBought(model.getQuantityBought());
            beanSaleItem.setTotal(model.getTotal());
            beanSaleItem.setTicketNumber(model.getTicketNumber());
            beanSaleItem.setUnitPrice(model.getUnitPrice());
            beanSaleItem.setProductDescription(model.getProductDescription());
            beanSaleItemList.add(beanSaleItem);
        }
//        for (ProductsInTransaction beanSaleItemList1 : beanSaleItemList) {
//            System.out.println(beanSaleItemList1.getProductId());
//        }
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanSaleItemList);
        HashMap parameters = new HashMap<>();
//        parameters.put("receiptDate", dateLocal.getText());
        try {
            JasperPrint printFileName = JasperFillManager.fillReport(
                    sourceFileName, parameters, beanColDataSource);
            if (printFileName != null) {
                String outPdfName = "BestOrWorstSale.pdf";
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setInitialDirectory(new File("src"));

                directoryChooser.setTitle("Save File To");
                EpicPOSAdmin instance = EpicPOSAdmin.getInstance();
                File selectedDirectory = directoryChooser.showDialog(instance.getPrimaryStage());
                System.out.print(selectedDirectory);

                JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setExporterInput(new SimpleExporterInput(printFileName));
                pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(selectedDirectory.getAbsolutePath() + "/" + outPdfName));
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                configuration.setCreatingBatchModeBookmarks(true);
                pdfExporter.setConfiguration(configuration);
                pdfExporter.exportReport();
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbDateRangeOnAction(ActionEvent event
    ) {
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event
    ) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event
    ) {
    }

    @FXML
    private void btnExportPdfOnAction(ActionEvent event) {
        try {
            exportReportPdf();
        } catch (JRException ex) {
            Logger.getLogger(BestOrWorstSalesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BestOrWorstSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnExportExcelOnAction(ActionEvent event
    ) {
        try {
            exportReportExcel();
        } catch (JRException ex) {
            Logger.getLogger(BestOrWorstSalesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BestOrWorstSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setApp(EpicPOSAdmin application) {
        this.application = application;
    }
}
