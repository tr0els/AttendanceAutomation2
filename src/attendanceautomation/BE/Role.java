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
public interface Role {
    String Role();
}

class IsTeacher implements Role {
    
    public String Role() {
        return "Teacher";
    }
}

class IsStudent implements Role {

    public String Role() {
        return "Student";
    }
}
