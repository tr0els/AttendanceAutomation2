/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Kim
 */
public class Student extends Person
{

    private List<String> last30DaysAbsent;
//    private int present;
    private int absent;
    private XYChart.Series AbsenceDistributedPerDay;
    private double absence;
    private int classId;

    private String teachers;

    /*
        en Student constructer. da student klassen er extended af person så skal
        vi kalde med "super" for at få dens variabler
        personrole blive sat til student ved at bruge "IsStudent"
     */
    public Student(String email, String password)
    {
        super(email, password);

        personRole = new IsStudent();
    }

    public Student()
    {
        this.absence = absence;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    public double getAbsence()
    {
        return absence;
    }

    public void setAbsence(double absence)
    {
        this.absence = absence;
    }

    @Override
    public String toString()
    {
        String strAbsence = String.format("%.1f", absence);
        return super.getName() + " - " + strAbsence + " %";
    }
    
    
}
