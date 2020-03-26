/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Model;

import attendanceautomation.BLL.BLLManager;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class studentModel {
    
    private BLLManager manager;
    
    public studentModel()
    {
        manager = new BLLManager();
    }
    

    /**
     * Henter datoen for idag og returnere den.
     * @return Dagens dato
     */
    public Date getCurrentDate()
    {
        return manager.getCurrentdate();
        
    }
    

    /**
     * sender en dato ned i systemet for at registrere at man er tilstede denne dato.
     * @param date 
     */
    public void studentIsPresent(Date date, int studentID)
    {
        manager.studentIsPresent(date, studentID);
    }
    
}
