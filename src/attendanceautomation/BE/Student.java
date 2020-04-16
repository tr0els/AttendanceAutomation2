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
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public class Student extends Person
{

    private List<String> last30DaysAbsent;
    private int absent;
    private XYChart.Series AbsenceDistributedPerDay;
    private double absence;
    private int classId;
    private String teachers;

    /**
     * Constructor for Student. Student klassen er extended af Person så vi
     * kalder den med "super" for at kunne til tilgå dens variabler. Personrole
     * bliver sat til Student ved at bruge "IsStudent"
     *
     * @param email
     * @param password
     */
    public Student(String email, String password)
    {
        super(email, password);

        personRole = new IsStudent();
    }

    /**
     * Constructor for Student
     */
    public Student()
    {
        this.absence = absence;
    }

    /**
     * Returnerer Student classId
     *
     * @return
     */
    public int getClassId()
    {
        return classId;
    }

    /**
     * Sætter Student classId
     *
     * @param classId
     */
    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    /**
     * Returnerer Student absence
     *
     * @return
     */
    public double getAbsence()
    {
        return absence;
    }

    /**
     * Sætter Student absence
     *
     * @param absence
     */
    public void setAbsence(double absence)
    {
        this.absence = absence;
    }

    /**
     * Definerer en toString metode til at tilgå Student
     *
     * @return
     */
    @Override
    public String toString()
    {
        String strAbsence = String.format("%.1f", absence);
        return super.getName() + " - " + strAbsence + " %";
    }

}
