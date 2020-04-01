/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author Kim
 */
public class Student extends Person{
    
    private List<String> last30DaysAbsent;
//    private int present;
    private int absent;
    private XYChart.Series AbsenceDistributedPerDay;

    private String teachers;

    public Student(String email, String password) {
        super(email, password);

        
        personRole = new IsStudent();
    }
    
}
