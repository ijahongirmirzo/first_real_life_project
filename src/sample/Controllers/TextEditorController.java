/*
 * Edited by Intellij IDEA.
 * User: ijakhongirmirzo extends ilmSoft
 */

package sample.Controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.SqliteConnector;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class TextEditorController extends TableViewController implements Initializable {
    private PreparedStatement pst;
    private Connection connection = SqliteConnector.Connector();
    @FXML private JFXTextField firstNameSetter, lastNameSetter,middleNameSetter,addressSetter,birthAddressSetter,birthdaySetter,birthYearSetter,illnessSetter;
    public TableViewController parentController;
    @FXML private JFXComboBox<String> monthPicker=new JFXComboBox();
    public String birthday;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monthPicker.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
    }
     private void databaseSetter() throws SQLException {
         birthday=birthdaySetter.getText()+"-"+monthPicker.getSelectionModel().getSelectedItem()+"-"+birthYearSetter.getText();
        String query = "INSERT INTO PatientJournal (" +
                "firstName," +
                " lastName," +
                " middleName, " +
                "address, " +
                "birthAddress, " +
                "birthday, " +
                "illness, " +
                "cameDate) VALUES(?,?,?,?,?,?,?,datetime('now','localtime'))";
        pst = connection.prepareStatement(query);
        pst.setString(1,firstNameSetter.getText());
        pst.setString(2, lastNameSetter.getText());
        pst.setString(3, middleNameSetter.getText());
        pst.setString(4,addressSetter.getText());
        pst.setString(5,birthAddressSetter.getText());
        pst.setString(6,birthday);
        pst.setString(7,illnessSetter.getText());
        pst.executeUpdate();
    }
    @FXML private void saveButtonHandler(ActionEvent event) throws SQLException {
        if(checkTextField()==true) {
            databaseSetter();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            Alert infoalert = new Alert(Alert.AlertType.INFORMATION);
            infoalert.setTitle("Information");
            infoalert.setHeaderText(null);
            infoalert.setContentText("Saved successfully!");
            Optional<ButtonType> infoAction = infoalert.showAndWait();
            parentController.refreshTable();
        }
            else {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Attention please");
            warning.setHeaderText(null);
            warning.setContentText("Please fill out all the forms!");
            warning.showAndWait();
        }
    }
    private boolean checkTextField(){
        if(firstNameSetter.getText().isEmpty()|
                lastNameSetter.getText().isEmpty()|
                middleNameSetter.getText().isEmpty()|
                addressSetter.getText().isEmpty()|
                birthAddressSetter.getText().isEmpty()|
                birthdaySetter.getText().isEmpty()|
                birthYearSetter.getText().isEmpty()|
                monthPicker.getSelectionModel().isEmpty()|
                illnessSetter.getText().isEmpty()){
            return false;
        }else
            return true;
    }
    @FXML private void cancelButtonHandler(ActionEvent event) throws IOException {
        if (firstNameSetter.getText().isEmpty()&&
                lastNameSetter.getText().isEmpty()&&
                middleNameSetter.getText().isEmpty()&&
                addressSetter.getText().isEmpty()&&
                birthAddressSetter.getText().isEmpty()&&
                birthdaySetter.getText().isEmpty()&&
                birthYearSetter.getText().isEmpty()&&
                monthPicker.getSelectionModel().isEmpty()&&
                illnessSetter.getText().isEmpty()){
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please,are you sure to delete?");
            alert.setHeaderText(null);
            alert.setContentText("Do you really want to cancel?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
        }
    }
}
