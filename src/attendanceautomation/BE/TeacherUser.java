/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author Charlotte
 */
public class TeacherUser extends User {

    public TeacherUser(int id, String name, String email, String phonenr, String password) {
        super(id, name, email, phonenr, password);
    }
    
}
