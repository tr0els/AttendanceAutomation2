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

/*
    sætter Role(); til at sende "Teacher" tilbage
*/
class IsTeacher implements Role {
    
    public String Role() {
        return "Teacher";
    }
}
/*
    sætter Role(); til at sende "Student" tilbage
*/
class IsStudent implements Role {

    public String Role() {
        return "Student";
    }
}