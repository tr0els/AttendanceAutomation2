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

    public Teacher(String email, String password) {
        super(email, password);
        
        personRole = new IsTeacher();
    }


    
    
    
}
