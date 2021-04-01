/*
 * Edited by Intellij IDEA.
 * User: ijakhongirmirzo extends ilmSoft
 */

package sample.Controllers;

import javafx.stage.StageStyle;
import sample.Model.Patient;
import sample.SqliteConnector;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableViewController implements Initializable{
    private String fromDate, toDate;
    @FXML
    private JFXDatePicker fromDatePicker, toDatePicker;
    @FXML
    public String query = "SELECT * from PatientJournal where cameDate between date('now') and 10000-10-10";
    @FXML
    JFXTextField searchField;
    Patient patient;
    protected Connection connection = SqliteConnector.Connector();
    @FXML
    protected JFXTreeTableView<Patient> patientTable;
    protected ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setConnection() throws SQLException {
        JFXTreeTableColumn<Patient, Number> indexColumn = new JFXTreeTableColumn<Patient, Number>("#");
        indexColumn.setSortable(false);
        indexColumn.setPrefWidth(35);
        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(patientTable.getRoot().getChildren().indexOf(column.getValue()) + 1));
        JFXTreeTableColumn<Patient, String> firstNameColumn = new JFXTreeTableColumn("Firts name");
        firstNameColumn.setPrefWidth(150);
        firstNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> lastNameColumn = new JFXTreeTableColumn("Last name");
        lastNameColumn.setPrefWidth(150);
        lastNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().lastNameProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> middleNameColumn = new JFXTreeTableColumn("Middle name");
        middleNameColumn.setPrefWidth(150);
        middleNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().middleNameProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> addressColumn = new JFXTreeTableColumn("Address");
        addressColumn.setPrefWidth(150);
        addressColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().addressProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> birthAddressColumn = new JFXTreeTableColumn("Birth address");
        birthAddressColumn.setPrefWidth(150);
        birthAddressColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().birthAddressProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> birthdayColumn = new JFXTreeTableColumn("Birthday");
        birthdayColumn.setPrefWidth(150);
        birthdayColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().birthdayProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> illnessColumn = new JFXTreeTableColumn("Illness");
        illnessColumn.setPrefWidth(150);
        illnessColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().illnessProperty();
            }
        });
        JFXTreeTableColumn<Patient, String> cameDateColumn = new JFXTreeTableColumn("Registered date");
        cameDateColumn.setPrefWidth(150);
        cameDateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().cameDateProperty();
            }
        });

        ResultSet rs = connection.createStatement().executeQuery(query);
        while (rs.next()) {
            patient = new Patient(
                    rs.getInt("ID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("middleName"),
                    rs.getString("address"),
                    rs.getString("birthAddress"),
                    rs.getString("birthday"),
                    rs.getString("illness"),
                    rs.getString("cameDate"));
            patients.add(new Patient(patient.getPatientID(), patient.getFirstName(), patient.getLastName(), patient.getMiddleName(), patient.getAddress(), patient.getBirthAddress(), patient.getBirthday(), patient.getIllness(), patient.getCameDate()));
        }
        final TreeItem<Patient> root = new RecursiveTreeItem<>(patients, RecursiveTreeObject::getChildren);
        patientTable.getColumns().setAll(indexColumn, firstNameColumn, lastNameColumn, middleNameColumn, addressColumn, birthAddressColumn, birthdayColumn, illnessColumn, cameDateColumn);
        patientTable.setRoot(root);
        patientTable.setShowRoot(false);
    }

    @FXML
    protected void removeButtonHandler() throws SQLException {
        TreeItem<Patient> selected = patientTable.getSelectionModel().getSelectedItem();
        if (!patientTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please,verify to delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                String query = "DELETE  from PatientJournal where id=" + selected.getValue().getPatientID() + ";";
                PreparedStatement pst;
                pst = connection.prepareStatement(query);
                pst.executeUpdate();
                Alert infoalert = new Alert(Alert.AlertType.INFORMATION);
                infoalert.setTitle("Information");
                infoalert.setHeaderText(null);
                infoalert.setContentText("Deleted successfully!");
                Optional<ButtonType> infoAction = infoalert.showAndWait();
                refreshTable();
            }
        } else {
            Alert error = new Alert((Alert.AlertType.WARNING));
            error.setTitle("Attention!");
            error.setHeaderText(null);
            error.setContentText("Nobody chosen!");
            error.showAndWait();
        }
    }

    @FXML
    protected void newButtonHandler(ActionEvent event) throws IOException{
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/TextEditor.fxml"));
        root = loader.load();
        TextEditorController controller = loader.getController();
        controller.parentController = this;

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add new");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void searchFieldListener(){
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() >= 2) {
                query = "Select * from PatientJournal Where firstName like '%" + newValue + "%' or lastName like '%" + newValue + "%' or middleName like '%" + newValue + "%' or address like '%" + newValue + "%' or birthAddress like '%" + newValue + "%' or birthday like '%" + newValue + "%' or illness like '%" + newValue + "%'";
                try {
                    refreshTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    protected void filterButtonHandler() throws SQLException {
        if (fromDatePicker.getValue() == null && toDatePicker.getValue() != null) {
            toDate = toDatePicker.getValue().toString();
            fromDate = "1000-06-22";
        }
        else if (toDatePicker.getValue() == null && fromDatePicker.getValue() != null) {
            fromDate = fromDatePicker.getValue().toString();
            toDate = "9999-06-22";
        }
        else if (fromDatePicker.getValue() == null && toDatePicker.getValue() == null) {
            fromDate = "1000-06-22";
            toDate = "9999-06-22";
        } else {
            fromDate = fromDatePicker.getValue().toString();
            toDate = toDatePicker.getValue().toString();
        }

        query = "Select * from PatientJournal Where cameDate between '" + fromDate + "' AND '" + toDate + "'";
        patients.clear();
        ResultSet rs = connection.createStatement().executeQuery(query);
        while (rs.next()) {
            patient = new Patient(
                    rs.getInt("ID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("middleName"),
                    rs.getString("address"),
                    rs.getString("birthAddress"),
                    rs.getString("birthday"),
                    rs.getString("illness"),
                    rs.getString("cameDate"));
            patients.add(new Patient(patient.getPatientID(), patient.getFirstName(), patient.getLastName(), patient.getMiddleName(), patient.getAddress(), patient.getBirthAddress(), patient.getBirthday(), patient.getIllness(), patient.getCameDate()));
        }

        final TreeItem<Patient> root = new RecursiveTreeItem<>(patients, RecursiveTreeObject::getChildren);
        patientTable.setRoot(root);
        patientTable.setShowRoot(false);
    }

    @FXML
    private void clearSearchField() {
        searchField.clear();
    }

    @FXML
    protected void refreshTable() throws SQLException {
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        //query = "SELECT * from PatientJournal ";
        patients.clear();
        ResultSet rs = connection.createStatement().executeQuery(query);
        while (rs.next()) {
            patient = new Patient(
                    rs.getInt("ID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("middleName"),
                    rs.getString("address"),
                    rs.getString("birthAddress"),
                    rs.getString("birthday"),
                    rs.getString("illness"),
                    rs.getString("cameDate"));
            patients.add(new Patient(patient.getPatientID(), patient.getFirstName(), patient.getLastName(), patient.getMiddleName(), patient.getAddress(), patient.getBirthAddress(), patient.getBirthday(), patient.getIllness(), patient.getCameDate()));
        }

        final TreeItem<Patient> root = new RecursiveTreeItem<>(patients, RecursiveTreeObject::getChildren);
        patientTable.setRoot(root);
        patientTable.setShowRoot(false);
    }


}