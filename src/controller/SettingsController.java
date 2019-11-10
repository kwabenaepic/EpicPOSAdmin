/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import epicposadmin.Preferences;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import library.assistant.data.model.MailServerInfo;
//import library.assistant.database.DataHelper;
//import library.assistant.database.DatabaseHandler;
//import library.assistant.database.export.DatabaseExporter;
//import library.assistant.ui.mail.TestMailController;
//import library.assistant.util.LibraryAssistantUtil;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class SettingsController implements Initializable {

    @FXML
    private JFXTextField serverName;
    @FXML
    private JFXTextField smtpPort;
    @FXML
    private JFXTextField emailAddress;

//    private final static Logger LOGGER = LogManager.getLogger(DatabaseHandler.class.getName());
    @FXML
    private JFXPasswordField emailPassword;
    @FXML
    private JFXCheckBox sslCheckbox;
    @FXML
    private JFXSpinner progressSpinner;
    @FXML
    private TextField tfCompanyName;
    @FXML
    private TextField tfContactOne;
    @FXML
    private TextField tfContactTwo;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextArea taAddress;
    @FXML
    private PasswordField pfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDefaultValues();
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
//        int ndays = Integer.parseInt(nDaysWithoutFine.getText());
//        float fine = Float.parseFloat(finePerDay.getText());
//        String uname = username.getText();
//        String pass = password.getText();

        Preferences preferences = Preferences.getPreferences();
        preferences.setCompanyName(tfCompanyName.getText());
        preferences.setContactOne(tfContactOne.getText());
        preferences.setContactTwo(tfContactTwo.getText());
        preferences.setLocation(tfLocation.getText());
        preferences.setAddress(taAddress.getText());

        Preferences.writePreferenceToFile(preferences);

    }

//    private Stage getStage() {
////        return ((Stage) nDaysWithoutFine.getScene().getWindow());
//    }
    private void initDefaultValues() {
        Preferences preferences = Preferences.getPreferences();

        tfCompanyName.setText(String.valueOf(preferences.getCompanyName()));
        tfContactOne.setText(String.valueOf(preferences.getContactOne()));
        tfContactTwo.setText(String.valueOf(preferences.getContactTwo()));
        tfLocation.setText(String.valueOf(preferences.getLocation()));
        taAddress.setText(String.valueOf(preferences.getAddress()));

//        String passHash = String.valueOf(preferences.getPassword());
//        password.setText(passHash.substring(0, Math.min(passHash.length(), 10)));
//        loadMailServerConfigurations();
    }

    @FXML
    private void handleTestMailAction(ActionEvent event) {
//        MailServerInfo mailServerInfo = readMailSererInfo();
//        if (mailServerInfo != null) {
//            TestMailController controller = (TestMailController) LibraryAssistantUtil.loadWindow(getClass().getResource("/library/assistant/ui/mail/test_mail.fxml"), "Test Email", null);
//            controller.setMailServerInfo(mailServerInfo);
//        }
    }

    @FXML
    private void saveMailServerConfuration(ActionEvent event) {
//        MailServerInfo mailServerInfo = readMailSererInfo();
//        if (mailServerInfo != null) {
//            if (DataHelper.updateMailServerInfo(mailServerInfo)) {
//                AlertMaker.showSimpleAlert("Success", "Saved successfully!");
//            } else {
//                AlertMaker.showErrorMessage("Failed", "Something went wrong!");
//            }
//        }
    }

//    private MailServerInfo readMailSererInfo() {
//        try {
//            MailServerInfo mailServerInfo
//                    = new MailServerInfo(serverName.getText(), Integer.parseInt(smtpPort.getText()), emailAddress.getText(), emailPassword.getText(), sslCheckbox.isSelected());
//            if (!mailServerInfo.validate() || !LibraryAssistantUtil.validateEmailAddress(emailAddress.getText())) {
//                throw new InvalidParameterException();
//            }
//            return mailServerInfo;
//        } catch (Exception exp) {
//            AlertMaker.showErrorMessage("Invalid Entries Found", "Correct input and try again");
//            LOGGER.log(Level.WARN, exp);
//        }
//        return null;
//    }
    private void loadMailServerConfigurations() {
//        MailServerInfo mailServerInfo = DataHelper.loadMailServerInfo();
//        if (mailServerInfo != null) {
//            LOGGER.log(Level.INFO, "Mail server info loaded from DB");
//            serverName.setText(mailServerInfo.getMailServer());
//            smtpPort.setText(String.valueOf(mailServerInfo.getPort()));
//            emailAddress.setText(mailServerInfo.getEmailID());
//            emailPassword.setText(mailServerInfo.getPassword());
//            sslCheckbox.setSelected(mailServerInfo.getSslEnabled());
//        }
    }

    @FXML
    private void handleDatabaseExportAction(ActionEvent event) {
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setTitle("Select Location to Create Backup");
//        File selectedDirectory = directoryChooser.showDialog(getStage());
//        if (selectedDirectory == null) {
//            AlertMaker.showErrorMessage("Export cancelled", "No Valid Directory Found");
//        } else {
//            DatabaseExporter databaseExporter = new DatabaseExporter(selectedDirectory);
//            progressSpinner.visibleProperty().bind(databaseExporter.runningProperty());
//            new Thread(databaseExporter).start();
//        }
    }
}
