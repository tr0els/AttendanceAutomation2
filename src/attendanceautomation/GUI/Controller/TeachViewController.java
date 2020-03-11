/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author BBran
 */
public class TeachViewController implements Initializable
{

    @FXML
    private Label lblSelectedclass;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        model = new AttendanceAutomationModel();
        
        lblStudentname.setText(model.selectedStudent().get(0));
        lblEmail.setText(model.selectedStudent().get(1));
        lblPhone.setText(model.selectedStudent().get(2));
        lblSelectedclass.setText(model.selectedClass());
        handleStudentList();
        handleStudentAbsence();
        handleBarChart();
    }

    public void handleStudentList()
    {
        ObservableList<String> currentClass = FXCollections.observableArrayList(model.absentStudentList());

        listviewStudents.setItems(currentClass);
    }

    public void handleStudentAbsence()
    {
        ObservableList<String> currentClass = FXCollections.observableArrayList(model.studentAbsentDays());

        listviewAbsenceDays.setItems(currentClass);
    }
    
    public void handleBarChart()
    {

        
        chartAbsenceperDay.getData().add(model.absencePerDay());
    }
    

}
