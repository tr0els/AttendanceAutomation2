/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author BBran
 */
public class StudViewController implements Initializable {

    @FXML
    private Label lblStudentFullname;
    @FXML
    private JFXListView<String> listviewTodaysClasses;
    @FXML
    private JFXButton btnAttendCurrentClass;
    @FXML
    private PieChart piechartAttendance;
    @FXML
    private JFXListView<String> listviewMissedClasses;

    @FXML
    private Label lblAbsencepercent;
    @FXML
    private Label lblCurrentClass;
    
    private int absence;
    private AttendanceAutomationModel model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       model = new AttendanceAutomationModel(); 
       absence = Integer.parseInt(model.selectedStudent().get(3));
       handlePieChart();
       handleCurrentclass();
       handleMissedClasses();
       lblAbsencepercent.setText(absence + "%");
       lblStudentFullname.setText(model.selectedStudent().get(0));
       lblCurrentClass.setText("SDE");
    }    
           
    
    public void handlePieChart()
    {
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Present", 100-absence),
                new PieChart.Data("Absent", absence)
        );
         
         piechartAttendance.setData(pieChartData);
         piechartAttendance.setClockwise(true);
         piechartAttendance.setLabelLineLength(10);
         piechartAttendance.setLegendVisible(false);
         piechartAttendance.setStartAngle(90);

    }
    
    public void handleCurrentclass()
    {
        ObservableList<String> currentClass = FXCollections.observableArrayList(model.currentClass());
        
        listviewTodaysClasses.setItems(currentClass);
        listviewTodaysClasses.setFocusTraversable(false);
        listviewTodaysClasses.setMouseTransparent(true);
    }
    
    public void handleMissedClasses()
    {
        ObservableList<String> missedClass = FXCollections.observableArrayList(model.missedClass());
        
        listviewMissedClasses.setItems(missedClass);
        listviewMissedClasses.setFocusTraversable(false);
        listviewMissedClasses.setMouseTransparent(true);
        
    }
}
