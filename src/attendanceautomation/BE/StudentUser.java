/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Charlotte
 */
public class StudentUser extends User {

    private List<String> last30DaysAbsent;
    private int absent;
    private XYChart.Series AbsenceDistributedPerDay;
    private final StringProperty Klasse;
    
    
    public StudentUser(int id, String name, String email, String phonenr, String password, String klasse) {
        super(id, name, email, phonenr, password);
        this.Klasse = new SimpleStringProperty(klasse);
    }
    
    //Get klasse as property
    public StringProperty getKlasseProperty() {
        return Klasse;
    }
    
    //get Klasse as String
    public String getKlasse(){
     return Klasse.get();
    }
    
    //set Klasse via Stirng 
    public void setKlasse(String newKlasse){
        Klasse.set(newKlasse);
    }
    
    
    
}
