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
 * @author BBran
 */
public class LoginController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = AttendanceAutomationModel.getInstance();
    }

    
    /*
        Tager det info der er blevet tastet ind og sender det ned til DAO for at
        blive verified
    */
    @FXML
    private void handleLogIn(ActionEvent event) throws IOException {
        String username = txtfieldUsername.getText();
        String password = passwordfieldPassword.getText().toString();

        
        if (model.loginModel(username, password) == true) {
            String role = model.getRole();
            
            if (role.equals("Student")){
                OpenStudentMenu(event);
            }
            if (role.equals("Teacher")){
                OpenTeacherMenu(event);
            }
        }
    }

    /*
        åbner lære Menuen
    */
    private void OpenTeacherMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomation/GUI/View/TeachView.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Teacher Menu");
            Stage Currentstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Currentstage.close();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /*
        åbner studerende menuen
    */
    private void OpenStudentMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomation/GUI/View/StudView.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Student Menu");
            Stage Currentstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

}
