/*
 * Edited by Intellij IDEA.
 * User: ijakhongirmirzo extends ilmSoft
 */

package sample.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Patient extends RecursiveTreeObject<Patient> {




    private IntegerProperty patientID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty middleName;
    private StringProperty address;
    private StringProperty birthAddress;
    private StringProperty birthday;
    private StringProperty illness;
    private StringProperty cameDate;

    public Patient(){
        patientID.set(0);
    }

    public Patient( String firstName,String lastName, String middleName, String address, String birthAddress, String birthday, String illness, String cameDate) {
        //this.patientID = new SimpleIntegerProperty(patientID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.address = new SimpleStringProperty(address);
        this.birthAddress = new SimpleStringProperty(birthAddress);
        this.birthday = new SimpleStringProperty(birthday);
        this.illness = new SimpleStringProperty(illness);
        this.cameDate =new SimpleStringProperty(cameDate);
    }
    public Patient(Integer patientID, String firstName, String lastName, String middleName, String address, String birthAddress, String birthday, String illness, String cameDate) {
        this.patientID = new SimpleIntegerProperty(patientID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.address = new SimpleStringProperty(address);
        this.birthAddress = new SimpleStringProperty(birthAddress);
        this.birthday = new SimpleStringProperty(birthday);
        this.illness = new SimpleStringProperty(illness);
        this.cameDate =new SimpleStringProperty(cameDate);
    }
    public Integer getPatientID() {
        return patientID.get();
    }

    public IntegerProperty patientIDProperty() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID.set(patientID);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getBirthAddress() {
        return birthAddress.get();
    }

    public StringProperty birthAddressProperty() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress.set(birthAddress);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getIllness() {
        return illness.get();
    }

    public StringProperty illnessProperty() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness.set(illness);
    }

    public String getCameDate() {
        return cameDate.get();
    }

    public StringProperty cameDateProperty() {
        return cameDate;
    }

    public void setCameDate(String cameDate) {
        this.cameDate.set(cameDate);
    }
}
