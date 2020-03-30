/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.DAL.DALException;
import attendanceautomation.GUI.Model.studentModel;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
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

    private int absence;
    private studentModel model;
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

    private int personID;
    private LocalDate currentDate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            model = new studentModel();

            currentDate = model.getCurrentDate();

            String strDate = currentDate.format(DateTimeFormatter.ofPattern("dd. MMMM yyyy"));
            
            showDate.setText(strDate);

        } catch (DALException ex) {
            Logger.getLogger(StudViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handlePieChart() {

    }

    public void handleMissedClasses() {

    }

    public void handleBarChart() {
        //use studentid?
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

        //TO_DO Skaffe personID fra BE!
        personID = 1;
        model.studentIsPresent(currentDate, personID);
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
