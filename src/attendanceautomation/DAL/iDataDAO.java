/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import java.time.LocalDate;


/**
 *
 * @author BBran
 */
public interface iDataDAO {
    
     /**
     * returnere dagens dato.
     * @return 
     */
    public LocalDate getCurrentDate();
    
    /**
     * sender en dato og personID ind i DB'en for at registrere personen er tilstede på denne dato.
     * @param date 
     */
    public void studentIsPresent(LocalDate date, int personID);
    
    
    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret sig idag.
     * @param personID
     * @return 
     */
    public boolean studentAlreadyRegistered(int personID);
}
