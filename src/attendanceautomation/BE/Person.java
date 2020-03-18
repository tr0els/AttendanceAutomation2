/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author Draik
 */
public class Person {
    
    private String name;
    private String email;
    private String password;
    private int phoneNumber;

    
    
    
    //using the IsTeacher interface to dynamically chance the status of the 
    //person between teacher or student.
    public IsTeacher isThisPersonATeacher;
    
    public boolean CheckTeacher(){
        return isThisPersonATeacher.isTeacher();
    }
    
    public void setTeacherStatus(IsTeacher newTeacher){
        isThisPersonATeacher = newTeacher;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    


    
    
}
