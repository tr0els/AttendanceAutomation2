/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.Model.AttendanceAutomationModel;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public class LoginController implements Initializable
{

    @FXML
    private Label label;
    @FXML
    private TextField txtfieldUsername;
    @FXML
    private PasswordField passwordfieldPassword;
    @FXML
    private JFXButton btnLogIn;
    @FXML
    private MenuItem menuitemClose;
    @FXML
    private MenuItem menuitemAbout;

    private AttendanceAutomationModel model;

    /**
     * Initializer for LoginController
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = AttendanceAutomationModel.getInstance();
        txtfieldUsername.setText("student@email.com");
        passwordfieldPassword.setText("1234");
    }

    /**
     * Tager det info som brugeren har inputtet og sender det til modelen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleLogIn(ActionEvent event) throws IOException
    {
        String username = txtfieldUsername.getText();
        String password = passwordfieldPassword.getText().toString();

        boolean check = model.loginModel(username, password);

        if (model.loginModel(username, password) == true)
        {
            int role = model.getRole(username);

            if (role == 1)
            {
                OpenStudentMenu(event, currentStudent(username, password));
            }
            if (role == 2)
            {
                OpenTeacherMenu(event);
            }
        }
    }

    /**
     * Åbner Teacher menuen
     *
     * @param event
     */
    private void OpenTeacherMenu(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomation/GUI/View/TeachView.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Teacher Menu");
            Stage Currentstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
     * Åbner Student menuen
     *
     * @param event
     * @param stud
     */
    private void OpenStudentMenu(ActionEvent event, Student stud)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/attendanceautomation/GUI/View/StudView.fxml"));
            loader.load();
            Parent root = loader.getRoot();

            StudViewController studcontroller = loader.getController();
            studcontroller.setCurrentUser(stud);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Student Menu");
            Stage Currentstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
     * Lukker programmet
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
        Alert a = new Alert(AlertType.INFORMATION);
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
     * Sikkerhedstjek - Sammenligner username og password med det der ligger i
     * Databasen.
     *
     * @param username
     * @param password
     * @return
     */
    public Student currentStudent(String username, String password)
    {
        return model.getCurrentStudent(username, password);
    }

    /**
     * Sikkerhedstjek - Sammenligner username og password med det der ligger i
     * Databasen.
     *
     * @param username
     * @param password
     * @return
     */
    public Teacher currentTeacher(String username, String password)
    {
        return model.getCurrentTeacher(username, password);

    }

}
