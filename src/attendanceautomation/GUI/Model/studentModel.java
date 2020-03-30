/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Model;

import attendanceautomation.BLL.BLLManager;
import attendanceautomation.DAL.DALException;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class studentModel {
    
    private BLLManager manager;
    
    public studentModel() throws DALException
    {
        manager = new BLLManager();
    }
    

    /**
     * Henter datoen for idag og returnere den.
     * @return Dagens dato
     */
    public LocalDate getCurrentDate()
    {
        return manager.getCurrentdate();
        
    }
    

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er tilstede på denne dato.
     * @param date 
     */
    public void studentIsPresent(LocalDate date, int personID)
    {
        manager.studentIsPresent(date, personID);
    }
    
}
