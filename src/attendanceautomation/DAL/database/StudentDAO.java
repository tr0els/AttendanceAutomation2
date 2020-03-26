/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL.database;

import attendanceautomation.DAL.iStudentDAO;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class StudentDAO implements iStudentDAO {

    
     /**
     * returnere dagens dato via SQL now
     * @return 
     */
    @Override
    public Date getCurrentDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * sender en dato og studentID ind i DB'en for at registrere tilstede.
     * @param date 
     */
    @Override
    public void studentIsPresent(Date date, int studentID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
