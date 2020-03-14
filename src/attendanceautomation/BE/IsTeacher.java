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
public interface IsTeacher {
    
    boolean isTeacher();
    
}

class isATeacher implements IsTeacher{

    public boolean isTeacher() {
        return true;
    }
    
    public boolean isNotTeacher() {
        return false;
    }

    
}
