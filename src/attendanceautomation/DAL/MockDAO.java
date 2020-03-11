/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author BBran
 */
public class MockDAO
{

    public List<String> absentStudentList()
    {
        ArrayList<String> studentList = new ArrayList<>();

        studentList.add("John Doe - 20%");
        studentList.add("Jane Doe - 10%");

        return studentList;
    }

    public List<String> studentAbsentDays()
    {
        ArrayList<String> absentDays = new ArrayList<>();

        absentDays.add("16-02-2020");
        absentDays.add("17-02-2020");
        absentDays.add("18-02-2020");

        return absentDays;
    }

    public XYChart.Series absencePerDay()
    {

        XYChart.Series dataSeries1 = new XYChart.Series();

        dataSeries1.getData().add(new XYChart.Data("Monday", 10));
        dataSeries1.getData().add(new XYChart.Data("Tuesday", 0));
        dataSeries1.getData().add(new XYChart.Data("Wednesday", 50));
        dataSeries1.getData().add(new XYChart.Data("Thursday", 20));
        dataSeries1.getData().add(new XYChart.Data("Friday", 40));

        return dataSeries1;
    }

    public List<String> selectedStudent()
    {
        ArrayList<String> selectedStudent = new ArrayList<>();

        selectedStudent.add("John Doe");
        selectedStudent.add("JohnDoe@easv.dk");
        selectedStudent.add("88 88 88 88");
        selectedStudent.add("20");

        return selectedStudent;

    }

    public String selectedClass()
    {
        return "SDE";
    }

    public List<String> currentClass()
    {
        ArrayList<String> currentClass = new ArrayList<>();

        currentClass.add("SDE - C3 (9:00-11:30)");
        currentClass.add("SCO - C3 (12:00-14:30)");

        return currentClass;
    }

    public List<String> missedClass()
    {
        ArrayList<String> missedClass = new ArrayList<>();

        missedClass.add("SDE | (Monday - 11.01.2020)");
        missedClass.add("SCO | (Friday - 15.01.2020");
        missedClass.add("DBO | (Monday - 18.01.2020)");

        return missedClass;

    }
}
