  
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
public class Teacher extends Person{
    
    private String students;

    /*
        en Teacher constructer. da teacher klassen er extended af person så skal
        vi kalde med "super" for at få dens variabler
        personrole blive sat til lære ved at bruge "IsTeacher"
    */
    public Teacher(String email, String password) {
        super(email, password);
        
        personRole = new IsTeacher();
    }

    public Teacher(){
    
    }

    
    
    
}