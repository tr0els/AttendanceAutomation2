/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.BE.Classes;
import attendanceautomation.DAL.MockDAO;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author BBran
 */
public class MockManager
{

    private MockDAO mockDao;

    public MockManager()
    {
        mockDao = new MockDAO();
    }

    public List<String> absentStudentList()
    {
        return mockDao.absentStudentList();
    }

    public List<String> studentAbsentDays()
    {
        return mockDao.studentAbsentDays();
    }

    public XYChart.Series absencePerDay()
    {
        return mockDao.absencePerDay();
    }

    public List<String> selectedStudent()
    {
        return mockDao.selectedStudent();
    }

    public String selectedClass()
    {
        return mockDao.selectedClass();
    }

    public List<String> currentClass()
    {
        return mockDao.currentClass();
    }

    public List<String> missedClass()
    {
        return mockDao.missedClass();
    }

    public String getTest()
    {
        return mockDao.getTest();
    }

    public void setTest(String txt)
    {
        mockDao.setTest(txt);
    }


    public List<Classes> listClasses()
    {
        return mockDao.listClasses();

    
    public boolean LoginBLL (String email, String password){
        return mockDao.Login(email, password);
    }
    
    public String getRole(){
    
        return mockDao.getRole();

    }
}
