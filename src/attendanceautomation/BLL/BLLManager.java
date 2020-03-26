/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.DAL.database.StudentDAO;
import attendanceautomation.DAL.iStudentDAO;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class BLLManager {
    
    private iStudentDAO studentdao;
    
    public BLLManager(){
        
        studentdao = new StudentDAO();
    }
    
    /**
     * returnere dagens dato.
     * @return 
     */
    public Date getCurrentdate(){
        
        return studentdao.getCurrentDate();
    }
    
    
    /**
     * sender en dato og personID ind i DB'en for at registrere personen er tilstede på denne dato.
     * @param date 
     */
    public void studentIsPresent(Date date, int personID){
        
        studentdao.studentIsPresent(date, personID);
    }
    
}
