/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class BLLManager {
    
    private iDataDAO datadao;
    
    public BLLManager() throws DALException{
        
        datadao = new DataDAO();
    }
    
    /**
     * returnere dagens dato.
     * @return 
     */
    public LocalDate getCurrentdate(){
        
        return datadao.getCurrentDate();
    }
    
    
    /**
     * sender en dato og personID ind i DB'en for at registrere personen er tilstede på denne dato.
     * @param date 
     */
    public void studentIsPresent(LocalDate date, int personID){
        
        datadao.studentIsPresent(date, personID);
    }
    
}
