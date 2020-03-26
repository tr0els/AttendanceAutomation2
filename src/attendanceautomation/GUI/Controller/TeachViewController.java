/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BBran
 */
public class TeachViewController implements Initializable {

    @FXML
    private JFXListView<String> listviewStudents;
    @FXML
    private ImageView imgStudent;
    @FXML
    private Label lblStudentname;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhone;
    @FXML
    private JFXListView<String> listviewAbsenceDays;
    @FXML
    private BarChart<?, ?> chartAbsenceperDay;

    private AttendanceAutomationModel model;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuitemTLogout;
    @FXML
    private MenuItem menuitemClose;
    @FXML
    private Label lblPhone1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model = AttendanceAutomationModel.getInstance();

        lblStudentname.setText(model.selectedStudent().get(0));
        lblEmail.setText(model.selectedStudent().get(1));
        lblPhone.setText(model.selectedStudent().get(2));
        handleStudentList();
        handleStudentAbsence();
        handleBarChart();
    }

    public void handleStudentList() {
        ObservableList<String> currentClass = FXCollections.observableArrayList(model.absentStudentList());

        listviewStudents.setItems(currentClass);
    }

    public void handleStudentAbsence() {
        ObservableList<String> currentStudent = FXCollections.observableArrayList(model.studentAbsentDays());

        listviewAbsenceDays.setItems(currentStudent);
        listviewAbsenceDays.setFocusTraversable(false);
        listviewAbsenceDays.setMouseTransparent(true);
    }

    public void handleBarChart() {

        chartAbsenceperDay.getData().add(model.absencePerDay());
    }

    @FXML
    private void handleLogout(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomation/GUI/View/Login.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation");
            Stage Currentstage = (Stage) menuBar.getScene().getWindow();
            Currentstage.close();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCloseprogram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("About");
        a.setHeaderText(null);
        a.setGraphic(null);
        a.setWidth(100);
        a.setContentText("This program was made by:\n"
                + " \n"
                + "Charlotte Christensen\n"
                + "Troels Klein\n"
                + "René Jørgensen\n"
                + "Kim Christensen\n"
                + "Brian Brandt");
        a.show();
    }

}
