/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Student;
import attendanceautomation.DAL.DALException;
import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
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
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label showDate;

    private double absence;
    private AttendanceAutomationModel model;
    private int personID;
    private int daysPresent;
    private LocalDate currentDate;
    private String registeredToday;
    @FXML
    private JFXListView<LocalDate> listviewMissedDays;
    @FXML
    private Label lblMissedDays;
    private Student CurrentStudent = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model = AttendanceAutomationModel.getInstance();

        daysPresent = 15; //hvor langt tilbage listen over missed days viser
        lblMissedDays.setText("Missed Days (Last " + daysPresent + " days)");

        //absence = model.studentAbsence(personID); bliver hentet nede i setCurrentUser

        currentDate = model.getCurrentDate();
        String strDate = currentDate.format(DateTimeFormatter.ofPattern("dd. MMMM yyyy"));
        showDate.setText(strDate);
        
        //registeredToday = model.studentAlreadyRegistered(personID); bliver hentet nede i setCurrentUser
        if (registeredToday != null) {
            btnAttendCurrentClass.setDisable(true);
            btnAttendCurrentClass.setText(registeredToday);
        }

        handlePieChart();
        handleBarChart();
        handleMissedDays();
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

        String strAbsence = String.format("%.1f", absence);
        lblAbsencepercent.setText(strAbsence + "%");
    }

    public void handleMissedDays() {
        listviewMissedDays.setItems(model.missedDays(personID, daysPresent));
    }

    public void handleBarChart() {
        
        
        
        XYChart.Series weekdaysabsent = new XYChart.Series<>();
        weekdaysabsent.getData().add(new XYChart.Data<>("Monday", 50));
        weekdaysabsent.getData().add(new XYChart.Data<>("Tuesday", 10));
        weekdaysabsent.getData().add(new XYChart.Data<>("Wednesday", 30));
        weekdaysabsent.getData().add(new XYChart.Data<>("Thursday", 40));
        weekdaysabsent.getData().add(new XYChart.Data<>("friday", 100));
        chartAbsenceperDay.getData().addAll(weekdaysabsent);
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

    /**
     * Henter dato for idag og sender den og personID ned i db for at registrere
     * at man er tilstede. Konvertere dato til string og viser den i label
     * showDate.
     *
     * @param event
     */
    @FXML
    private void handleAttendance(ActionEvent event) {

        model.studentIsPresent(currentDate, personID);
        btnAttendCurrentClass.setDisable(true);
        btnAttendCurrentClass.setText("Already Registered!");
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
    
    public void setCurrentUser(Student student){
        CurrentStudent = student;
        personID = CurrentStudent.getPersonID(); 
        absence = model.studentAbsence(personID);
        registeredToday = model.studentAlreadyRegistered(personID);
        lblStudentFullname.setText(CurrentStudent.getName());
    }
    
    
}
