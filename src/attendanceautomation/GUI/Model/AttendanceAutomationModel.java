/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Model;

import attendanceautomation.BLL.MockManager;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author BBran
 */
public class AttendanceAutomationModel
{

    private MockManager manager;

    public AttendanceAutomationModel()
    {
        manager = new MockManager();

    }

    public List<String> absentStudentList()
    {
        return manager.absentStudentList();
    }

    public List<String> studentAbsentDays()
    {
        return manager.studentAbsentDays();
    }

    public XYChart.Series absencePerDay()
    {
        return manager.absencePerDay();
    }

    public List<String> selectedStudent()
    {
        return manager.selectedStudent();
    }

    public String selectedClass()
    {
        return manager.selectedClass();
    }

    public List<String> currentClass()
    {
        return manager.currentClass();
    }
    
    public List<String> missedClass()
    {
        return manager.missedClass();
    }
}
