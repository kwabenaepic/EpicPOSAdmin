package beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author KwabenaEpic
 */
public class EmployeeLogins {

    private final IntegerProperty loginId;
    private final StringProperty userName;
    private final StringProperty loginTime;
    private final StringProperty logoutTime;
    private final StringProperty date;
    private final IntegerProperty status;
    private final StringProperty salesOutletId;
    private final StringProperty sessionId;

    public EmployeeLogins() {
        this.loginId = new SimpleIntegerProperty();
        this.userName = new SimpleStringProperty();
        this.loginTime = new SimpleStringProperty();
        this.logoutTime = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.status = new SimpleIntegerProperty();
        this.salesOutletId = new SimpleStringProperty();
        this.sessionId = new SimpleStringProperty();
    }

    public final String getSessionId() {
        return sessionId.get();
    }

    public final void setSessionId(String value) {
        sessionId.set(value);
    }

    public StringProperty sessionIdProperty() {
        return sessionId;
    }

    public final StringProperty dateProperty() {
        return date;
    }

    public final IntegerProperty loginIdProperty() {
        return loginId;
    }

    public final StringProperty loginTimeProperty() {
        return loginTime;
    }

    public final StringProperty logoutTimeProperty() {
        return logoutTime;
    }

    public final StringProperty salesOutletIdProperty() {
        return salesOutletId;
    }

    public final IntegerProperty statusProperty() {
        return status;
    }

    public final StringProperty userNameProperty() {
        return userName;
    }

    public final String getDate() {
        return date.get();
    }

    public final void setDate(String value) {
        date.set(value);
    }

    public final Integer getLoginId() {
        return loginId.get();
    }

    public final void setLoginId(Integer value) {
        loginId.set(value);
    }

    public final String getLoginTime() {
        return loginTime.get();
    }

    public final void setLoginTime(String value) {
        loginTime.set(value);
    }

    public final String getLogoutTime() {
        return logoutTime.get();
    }

    public final void setLogoutTime(String value) {
        logoutTime.set(value);
    }

    public final String getSalesOutletId() {
        return salesOutletId.get();
    }

    public final void setSalesOutletId(String value) {
        salesOutletId.set(value);
    }

    public final Integer getStatus() {
        return status.get();
    }

    public final void setStatus(Integer value) {
        status.set(value);
    }

    public final String getUserName() {
        return userName.get();
    }

    public final void setUserName(String value) {
        userName.set(value);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
