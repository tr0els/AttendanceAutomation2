/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import attendanceautomation.BE.Classes;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author BBran
 */
public class MockDAO
{

    private String test = "test";
    
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
        dataSeries1.getData().add(new XYChart.Data("Thursday", 17));
        dataSeries1.getData().add(new XYChart.Data("Friday", 23));

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

        missedClass.add("Monday - 11.01.2020");
        missedClass.add("Friday - 15.01.2020");
        missedClass.add("Monday - 18.01.2020");

        return missedClass;

    }
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String txt) {
        test = txt;
    }
    
    public List<Classes> listClasses(){
        
        Classes class1 = new Classes(1, "CSe2019_A");
        Classes class2 = new Classes(2, "CSe2019_B");
        Classes class3 = new Classes(3, "CSe2018_A");
        Classes class4 = new Classes(4, "CSe2018_B");
        Classes class5 = new Classes(5, "CSe2017_A");
        Classes class6 = new Classes(6, "CSe2017_B");
        
        ArrayList<Classes> listClasses = new ArrayList<>();
        
        listClasses.add(class1);
        listClasses.add(class2);
        listClasses.add(class3);
        listClasses.add(class4);
        listClasses.add(class5);
        listClasses.add(class6);
        
        return listClasses;
    }
}
