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
public class AttendanceAutomationModel {

    private BLLManager manager;

    public AttendanceAutomationModel() throws DALException {
      manager = new BLLManager();
     
    }

    /**
     * shit doesn't work! Skal laves så den kaster exceptions
     */
    private static class SingletonHolder {
        private static final AttendanceAutomationModel INSTANCE;
        static {
        try {
            INSTANCE =  new AttendanceAutomationModel();
        } catch (DALException e) {
            throw new ExceptionInInitializerError(e); //
        }
    }}
    
    public static AttendanceAutomationModel getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    
    /**
     * Henter datoen for idag og returnere den.
     *
     * @return Dagens dato
     */
    public LocalDate getCurrentDate() {
        return manager.getCurrentdate();

    }

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     */
    public void studentIsPresent(LocalDate date, int personID) {
        manager.studentIsPresent(date, personID);
    }

    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public boolean studentAlreadyRegistered(int personID) {

        return manager.studentAlreadyRegistered(personID);
    }

    /*
        Tager infoen fra longincontrolleren og sender det til BLL
     */
    public boolean loginModel(String email, String password) {
        return manager.LoginBLL(email, password);
    }

    /*
        Login controlleren skal bruge information om hvilken rolle useren har
        så denne metode returnere dette fra BLL
     */
    public int getRole(String username, String password) {

        return manager.getRole(username, password);

    }
    
    
    /**
     * DELETE ME WHEN DONE
     */
    public void countWeekdays()
    {
        manager.countWeekdays();
    }
    
    
    public double studentAbsence(int personID)
    {
        return manager.studentAbsence(personID);
    }
}
