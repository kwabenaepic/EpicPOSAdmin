
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URL;

import java.sql.Blob;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import beans.Employee;

import tables.EmployeeManager;

/**
 * FXML Controller class
 *
 * @author KwabenaEpic
 */
public class AddEmployeeController implements Initializable {
    private Stage                  stage = null;
    @FXML
    private TextField              tfFirstName;
    @FXML
    private TextField              tfLastName;
    @FXML
    private TextField              tfEmail;
    @FXML
    private TextField              tfMobile;
    @FXML
    private TextField              tfPhone;
    @FXML
    private PasswordField          pfPassword;
    @FXML
    private TextArea               taAddress;
    @FXML
    private ImageView              picBoxImageView;
    @FXML
    private TextField              tfUpload;
    @FXML
    private Button                 btnUpload;
    @FXML
    private Button                 btnSave;
    @FXML
    private Button                 btnCancel;
    @FXML
    private Button                 btnClose;
    @FXML
    private ComboBox<String>       cbSeurityGroup;
    @FXML
    private TextField              tfEmployeeId;
    @FXML
    private CheckBox               cbEnabled;
    private ObservableList<String> securityGroup;
    @FXML
    private TextField              tfUsername;
    FileInputStream                fis;
    File                           target, targetDir;
    Blob                           employeeImage;

    @FXML
    private void btnCancelOnAction(ActionEvent event) {}

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws Exception {
        saveEmployeeInfo();
        clearFields();
    }

    @FXML
    private void btnUploadOnAction(ActionEvent event) throws SQLException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image         image         = SwingFXUtils.toFXImage(bufferedImage, null);

                picBoxImageView.setImage(image);
                tfUpload.setText(file.getAbsolutePath());

//
//              if (fis != null) {
//                  picBoxImageView.setImage(image);
////              Path movefrom = FileSystems.getDefault().getPath(selectedFile.getPath());
//                  target = (file.getAbsoluteFile());
//
//                  System.out.println(getClass().getResource("/images"));
////             targetDir = FileSystems.getDefault().getPath("userfiles/"+UNAME+"/"+ANAME);
////            try{
////              Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
////            }catch (IOException e){}
//
//              }
            } catch (IOException ex) {
                Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cbSecurityGroupOnAction(ActionEvent event) {}

    private void clearFields() {
        tfFirstName.setText("");
        tfLastName.setText("");
        tfEmail.setText("");
        tfMobile.setText("");
        tfPhone.setText("");
        tfUsername.setText("");
        pfPassword.setText("");
        taAddress.setText("");
        tfEmployeeId.setText("");
        tfUpload.setText("");
        cbSeurityGroup.setValue("");
        cbEnabled.setSelected(false);
        picBoxImageView.setImage(null);
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        OutputStream oOutStream;

        try (InputStream oInStream = new FileInputStream(srcFile)) {
            oOutStream = new FileOutputStream(destFile);

            // Transfer bytes from in to out
            byte[]              oBytes = new byte[1024];
            int                 nLength;
            BufferedInputStream oBuffInputStream = new BufferedInputStream(oInStream);

            while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
                oOutStream.write(oBytes, 0, nLength);
            }
        }

        oOutStream.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
//      targetDir = new File(getClass().getClassLoader().getResource("/images"));
        securityGroup = FXCollections.observableArrayList();
        securityGroup.add("Teller");
        securityGroup.add("Manager");
        cbSeurityGroup.setItems(securityGroup);
    }

    private void saveEmployeeInfo() throws Exception {
        Employee bean = new Employee();

        bean.setFirstName(tfFirstName.getText());
        bean.setLastName(tfLastName.getText());
        bean.setEmail(tfEmail.getText());
        bean.setMobile(tfMobile.getText());
        bean.setPhone(tfPhone.getText());
        bean.setPassword(pfPassword.getText());
        bean.setUsername(tfUsername.getText());
        bean.setAddress(taAddress.getText());

//      bean.setEmployeeId(tfEmployeeId.getText());
        bean.setImagePath(tfUpload.getText());
        bean.setSecurityGroup(cbSeurityGroup.getValue());
        bean.setEnabled(cbEnabled.isSelected());

//      boolean isSelected = cbEnabled.isSelected();

        boolean result = EmployeeManager.insert(bean);

        if (result) {

//          copyFile(target, targetDir);
            Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Employee Save");
            alert.setContentText("New  Employee : " + bean.getFirstName() + " was added!");
            alert.showAndWait();

//          System.out.println("New  Employee : " + bean.getFirstName() + " was added!");
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
