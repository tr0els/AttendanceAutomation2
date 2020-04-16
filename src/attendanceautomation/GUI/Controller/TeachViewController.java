/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Classes;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.DAL.DALException;
import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public class TeachViewController implements Initializable
{

    @FXML
    private JFXListView<Student> listviewStudents;
    @FXML
    private ImageView imgStudent;
    @FXML
    private Label lblStudentname;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhone;
    @FXML
    private JFXListView<LocalDate> listviewAbsenceDays;
    @FXML
    private BarChart<?, ?> chartAbsenceperDay;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuitemTLogout;
    @FXML
    private MenuItem menuitemClose;
    @FXML
    private ChoiceBox<Classes> choiceBoxClasses;
    @FXML
    private Label lblTeacher;
    @FXML
    private JFXButton btnsetStudentPresent;

    private AttendanceAutomationModel model;
    private Classes choiceBoxChosenClass;
    private Student selectedStudent;
    private int daysPresent;
    private Student stud = new Student();

    /**
     * Initializer for TeachViewController
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        choiceBoxClasses.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            choiceBoxChosenClass = choiceBoxClasses.getSelectionModel().getSelectedItem();
            int cBCC = choiceBoxChosenClass.getId();
            if (cBCC == 4)
            {
                try
                {
                    listviewStudents.setItems(model.getAllStudents(choiceBoxChosenClass));
                } catch (DALException ex)
                {
                    Logger.getLogger(TeachViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else
            try
            {
                listviewStudents.setItems(model.getStudentsInClass(choiceBoxChosenClass));
            } catch (DALException ex)
            {
                Logger.getLogger(TeachViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        model = AttendanceAutomationModel.getInstance();
        try
        {
            choiceBoxClasses.setItems(FXCollections.observableArrayList(model.getTeacherClasses()));
            int startChoiceId = 4;
            choiceBoxClasses.setValue(model.getTeacherClasses().get(0));
        } catch (DALException ex)
        {
            Logger.getLogger(TeachViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logger brugeren ud
     *
     * @param event
     */
    @FXML
    private void handleLogout(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomation/GUI/View/Login.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation");
            Stage Currentstage = (Stage) menuBar.getScene().getWindow();
            Currentstage.close();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Lukker programmet ned
     *
     * @param event
     */
    @FXML
    private void handleCloseprogram(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * Giver en beskrivelse over hvem der har lavet programmet
     *
     * @param event
     */
    @FXML
    private void handleAbout(ActionEvent event)
    {
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

    /**
     * Sætter selectStudent som giver ekstra information omkring den studerende
     *
     * @param event
     * @throws DALException
     */
    @FXML
    private void handleSelectStudent(MouseEvent event) throws DALException
    {
        selectedStudent = listviewStudents.getSelectionModel().getSelectedItem();
        stud = model.getStudentInfo(selectedStudent);

        Teacher teach = new Teacher();
        teach = model.getStudentTeacher(selectedStudent);
        int studId = stud.getPersonID();
        lblStudentname.setText(stud.getName());
        lblEmail.setText(stud.getEmail());
        lblPhone.setText("" + stud.getPhoneNumber());
        listviewAbsenceDays.setItems(model.missedDays(studId, model.countAlldays()));
        lblTeacher.setText(teach.getName());
        chartAbsenceperDay.getData().clear();
        chartAbsenceperDay.getData().addAll(model.modelMissedDaysforAbsencePerDay(studId));
    }

    /**
     * Bruges af Teacher til at sætte en elev som værende tilstede i tilfælde af
     * fejl
     *
     * @param event
     */
    @FXML
    private void handleSetStudentPresent(ActionEvent event)
    {
        LocalDate selectedDate = listviewAbsenceDays.getSelectionModel().getSelectedItem();
        int studentID = stud.getPersonID();
        if (selectedDate != null)
        {
            model.studentIsPresent(selectedDate, studentID);

        }
    }
}
