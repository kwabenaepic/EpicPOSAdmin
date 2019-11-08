/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author KwabenaEpic
 */
public class EmployeesLogedIn {

//    public final void setEmployeeId(String value) {
//        employeeId.set(value);
//    }
//
//    public final String getEmployeeId() {
//        return employeeId.get();
//    }
//
//    public final StringProperty employeeIdProperty() {
//        return employeeId;
//    }

    public final void setFullname(String value) {
        fullname.set(value);
    }

    public final String getFullname() {
        return fullname.get();
    }

    public final StringProperty fullnameProperty() {
        return fullname;
    }

    public final void setSalesOutletId(String value) {
        salesOutletId.set(value);
    }

    public final String getSalesOutletId() {
        return salesOutletId.get();
    }

    public final StringProperty salesOutletIdProperty() {
        return salesOutletId;
    }

//    private final StringProperty employeeId;
    private final StringProperty fullname;
    private final StringProperty salesOutletId;

    public EmployeesLogedIn() {
//        this.employeeId = new SimpleStringProperty();
        this.fullname = new SimpleStringProperty();
        this.salesOutletId = new SimpleStringProperty();
    }
}
