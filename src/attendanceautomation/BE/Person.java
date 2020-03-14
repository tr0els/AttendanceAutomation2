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
    private String Email;
    private int phoneNumber;
    
    public IsTeacher isThisPersonATeacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public boolean CheckTeacher(){
        return isThisPersonATeacher.isTeacher();
    }
    
    public void setTeacherStatus(IsTeacher newTeacher){
        isThisPersonATeacher = newTeacher;
    }
    
}
