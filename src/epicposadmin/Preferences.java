package epicposadmin;

import com.google.gson.Gson;
import epicposadmin.AlertMaker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import library.assistant.alert.AlertMaker;
import org.apache.commons.codec.digest.DigestUtils;

public class Preferences {

    public static final String CONFIG_FILE = "config.txt";
//"src\\config\\weConfig.txt"
    int nDaysWithoutFine;
    float finePerDay;
    String username;
    String password;
    String companyName, location, contactOne, contactTwo, address;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getContactOne() {
        return contactOne;
    }

    public String getContactTwo() {
        return contactTwo;
    }

    public String getAddress() {
        return address;
    }

    public Preferences() {
//        nDaysWithoutFine = 14;
//        finePerDay = 2;
//        username = "admin";
//        setPassword("admin");
        companyName = "Campany Name";
        contactOne = "055000000";
        contactTwo = "024111111";
        address = "P. O. Box DN234, Dunkwa On Offine";
        location = "Dunkwa On Offin";
    }

//    public int getnDaysWithoutFine() {
//        return nDaysWithoutFine;
//    }
//
//    public void setnDaysWithoutFine(int nDaysWithoutFine) {
//        this.nDaysWithoutFine = nDaysWithoutFine;
//    }
//
//    public float getFinePerDay() {
//        return finePerDay;
//    }
//
//    public void setFinePerDay(float finePerDay) {
//        this.finePerDay = finePerDay;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        if (password.length() < 16) {
//            this.password = DigestUtils.shaHex(password);
//        } else {
//            this.password = password;
//        }
//    }
    public static void initConfig() {
        Writer writer = null;
        try {
            Preferences preference = new Preferences();
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preference, writer);
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Preferences getPreferences() {
        Gson gson = new Gson();
        Preferences preferences = new Preferences();
        try {
            preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preferences.class.getName()).info("Config file is missing. Creating new one with default config");
            initConfig();
        }
        return preferences;
    }

    public static void writePreferenceToFile(Preferences preference) {
        Writer writer = null;
        try {
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preference, writer);

            AlertMaker.showSimpleAlert("Success", "Settings updated");
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            AlertMaker.showErrorMessage(ex, "Failed", "Cant save configuration file");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
