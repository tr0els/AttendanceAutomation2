/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BBran
 */
public class StudViewController implements Initializable {

    @FXML
    private Label lblStudentFullname;

    @FXML
    private PieChart piechartAttendance;
    @FXML
    private JFXListView<String> listviewMissedClasses;

    @FXML
    private Label lblAbsencepercent;

    private int absence;
    private AttendanceAutomationModel model;
    @FXML
    private MenuItem menuitemSLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXButton btnAttendCurrentClass;
    @FXML
    private BarChart<?, ?> chartAbsenceperDay;
    @FXML
    private MenuItem menuitemClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model = new AttendanceAutomationModel();
        absence = Integer.parseInt(model.selectedStudent().get(3));
        handlePieChart();
        handleMissedClasses();
        lblAbsencepercent.setText(absence + "%");
        lblStudentFullname.setText(model.selectedStudent().get(0));
        handleBarChart();
    }

    public void handlePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Present", 100 - absence),
                new PieChart.Data("Absent", absence)
        );

        piechartAttendance.setData(pieChartData);
        piechartAttendance.setClockwise(true);
        piechartAttendance.setLabelLineLength(10);
        piechartAttendance.setLegendVisible(false);
        piechartAttendance.setStartAngle(90);

    }

    public void handleMissedClasses() {
        ObservableList<String> missedClass = FXCollections.observableArrayList(model.missedClass());

        listviewMissedClasses.setItems(missedClass);
//        listviewMissedClasses.setFocusTraversable(false);
//        listviewMissedClasses.setMouseTransparent(true);

    }

    public void handleBarChart() {
        chartAbsenceperDay.getData().add(model.absencePerDay()); //use studentid?
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
    private void handleAttendance(ActionEvent event) {

        //register todays date into DB as present, also check if already registered today.
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
