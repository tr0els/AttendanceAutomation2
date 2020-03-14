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
 * @author Draik
 */
public class Student extends Person{
    
    private List<String> last30DaysAbsent;
    private int present;
    private int absent;
    private XYChart.Series AbsenceDistributedPerDay;
    
    public Student(String name, String email, int phoneNumber){
    
        super();
        
        isThisPersonATeacher = new isNotTeacher();
    }
    
}
