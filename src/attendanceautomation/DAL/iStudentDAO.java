/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import java.util.Date;

/**
 *
 * @author BBran
 */
public interface iStudentDAO {
    
    
    /**
     * returnere dagens dato.
     * @return 
     */
    public Date getCurrentDate();
    
    /**
     * sender en dato og personID ind i DB'en for at registrere personen er tilstede på denne dato.
     * @param date 
     */
    public void studentIsPresent(Date date, int personID);
    
}
