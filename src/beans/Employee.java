
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package beans;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author KwabenaEpic
 */
public class Employee {
    private static final Map<String, Employee> Employee = new HashMap<>();
    private final IntegerProperty              employeeNumber;
    private final StringProperty               firstName;
    private final StringProperty               lastName;
    private final StringProperty               phone;
    private final StringProperty               mobile;
    private final StringProperty               email;

//  private final StringProperty               employeeId;
    private final StringProperty       username;
    private final StringProperty       password;
    private final StringProperty       imagePath;
    private final ObjectProperty<Blob> image;
    private final StringProperty       address;
    private final StringProperty       city;
    private final StringProperty       securityGroup;
    private final BooleanProperty      enabled;

    public Employee() {
        this.employeeNumber = new SimpleIntegerProperty();
        this.firstName      = new SimpleStringProperty();
        this.lastName       = new SimpleStringProperty();
        this.phone          = new SimpleStringProperty();
        this.mobile         = new SimpleStringProperty();
        this.email          = new SimpleStringProperty();

//      this.employeeId     = new SimpleStringProperty();
        this.username      = new SimpleStringProperty();
        this.password      = new SimpleStringProperty();
        this.imagePath     = new SimpleStringProperty();
        this.image         = new SimpleObjectProperty<>();
        this.address       = new SimpleStringProperty();
        this.city          = new SimpleStringProperty();
        this.securityGroup = new SimpleStringProperty();
        this.enabled       = new SimpleBooleanProperty();
    }

    public final StringProperty addressProperty() {
        return address;
    }

    public final StringProperty cityProperty() {
        return city;
    }

    public final StringProperty emailProperty() {
        return email;
    }

//  public final StringProperty employeeIdProperty() {
//      return employeeId;
//  }
    public final IntegerProperty employeeNumberProperty() {
        return employeeNumber;
    }

    public final BooleanProperty enabledProperty() {
        return enabled;
    }

    public final StringProperty firstNameProperty() {
        return firstName;
    }

    public final ObjectProperty<Blob> imagePath() {
        return image;
    }

    public final StringProperty imagePathProperty() {
        return imagePath;
    }

    public final StringProperty lastNameProperty() {
        return lastName;
    }

    public final StringProperty mobileProperty() {
        return mobile;
    }

    public static Employee of(String staffId) {
        Employee employee = Employee.get(staffId);

        if (employee == null) {
            employee = new Employee();
            Employee.put(staffId, employee);
        }

        return employee;
    }

    public final StringProperty passwordProperty() {
        return password;
    }

    public final StringProperty phoneProperty() {
        return phone;
    }

    public final StringProperty securityGroupProperty() {
        return securityGroup;
    }

    public final StringProperty usernameProperty() {
        return username;
    }

    public final String getAddress() {
        return address.get();
    }

    public final void setAddress(String value) {
        address.set(value);
    }

    public final String getCity() {
        return city.get();
    }

    public final void setCity(String value) {
        city.set(value);
    }

    public final String getEmail() {
        return email.get();
    }

    public final void setEmail(String value) {
        email.set(value);
    }

//  public final String getEmployeeId() {
//      return employeeId.get();
//  }
//
//  public final void setEmployeeId(String value) {
//      employeeId.set(value);
//  }
    public final Integer getEmployeeNumber() {
        return employeeNumber.get();
    }

    public final void setEmployeeNumber(Integer value) {
        employeeNumber.set(value);
    }

    public final Boolean getEnabled() {
        return enabled.get();
    }

    public final void setEnabled(Boolean value) {
        enabled.set(value);
    }

    public final String getFirstName() {
        return firstName.get();
    }

    public final void setFirstName(String value) {
        firstName.set(value);
    }

    public final Blob getImage() {
        return image.get();
    }

    public final void setImage(Blob value) {
        image.set(value);
    }

    public final String getImagePath() {
        return imagePath.get();
    }

    public final void setImagePath(String value) {
        imagePath.set(value);
    }

    public final String getLastName() {
        return lastName.get();
    }

    public final void setLastName(String value) {
        lastName.set(value);
    }

    public final String getMobile() {
        return mobile.get();
    }

    public final void setMobile(String value) {
        mobile.set(value);
    }

    public final String getPassword() {
        return password.get();
    }

    public final void setPassword(String value) {
        password.set(value);
    }

    public final String getPhone() {
        return phone.get();
    }

    public final void setPhone(String value) {
        phone.set(value);
    }

    public final String getSecurityGroup() {
        return securityGroup.get();
    }

    public final void setSecurityGroup(String value) {
        securityGroup.set(value);
    }

    public final String getUsername() {
        return username.get();
    }

    public final void setUsername(String value) {
        username.set(value);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
