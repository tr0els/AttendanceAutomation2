/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author BBran
 */
public class BLLManager {

    private iDataDAO datadao;
    
    private LocalDate semesterStart = LocalDate.of(2020, 1, 27); //evt. lave det om til dynamisk via tabel/DB?

    public BLLManager() throws DALException {

        datadao = new DataDAO();
    }

    /**
     * returnere dagens dato.
     *
     * @return
     */
    public LocalDate getCurrentdate() {

        return datadao.getCurrentDate();
    }

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     */
    public void studentIsPresent(LocalDate date, int personID) {

        datadao.studentIsPresent(date, personID);
    }

    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public boolean studentAlreadyRegistered(int personID) {

        return datadao.studentAlreadyRegistered(personID);
    }

        /*
        sender information fra loginmodel til DAO for at blive verified
    */
    public boolean LoginBLL (String email, String password){
        return datadao.Login(email, password);
    }
    
    /*
        rollen som model spørger efter bliver returneret her
    */
    public String getRole(){
    
        return datadao.getRole();

    }
    
    public void countWeekdays()
    {
        int weekdays = 0;
        
        LocalDate startDate = semesterStart;
        LocalDate endDate = LocalDate.now(); //skift ud med getCurrentdate()
        System.out.println(startDate);
        while (startDate.isBefore(endDate))
        {
            DayOfWeek dw = startDate.getDayOfWeek();
            if (dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY)
            {
            ++weekdays;}
            
            startDate = startDate.plusDays(1);
            
        }
        
        
        
        
        System.out.println(endDate);
        System.out.println(weekdays);
    }
    
}
