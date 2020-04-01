/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Model;

import attendanceautomation.BE.Classes;
import attendanceautomation.BLL.MockManager;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Troels Klein
 */
public class AttendanceAutomationModel
{
    private MockManager bll;
    
    private AttendanceAutomationModel() {
        bll = new MockManager(); // MockManager should later be a BLLManager
    }
    
    private static class SingletonHolder {
        private static final AttendanceAutomationModel INSTANCE = new AttendanceAutomationModel();
    }
    
    public static AttendanceAutomationModel getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String> absentStudentList()
    {
        return bll.absentStudentList();
    }

    public List<String> studentAbsentDays()
    {
        return bll.studentAbsentDays();
    }

    public XYChart.Series absencePerDay()
    {
        return bll.absencePerDay();
    }

    public List<String> selectedStudent()
    {
        return bll.selectedStudent();
    }

    public String selectedClass()
    {
        return bll.selectedClass();
    }

    public List<String> currentClass()
    {
        return bll.currentClass();
    }
    
    public List<String> missedClass()
    {
        return bll.missedClass();
    }
    
    public String getTest() {
        return bll.getTest();
    }
    
    public void setTest(String txt) {
        bll.setTest(txt);
    }
    

    public List<Classes> listClasses(){
        return bll.listClasses();
    }

    
    /*
        Tager infoen fra longincontrolleren og sender det til BLL
    */
    public boolean loginModel(String email, String password){
        return bll.LoginBLL(email, password);
    }
    
    
    /*
        Login controlleren skal bruge information om hvilken rolle useren har
        så denne metode returnere dette fra BLL
    */
    public String getRole(){
    
        return bll.getRole();

    }
}
