/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import attendanceautomation.BE.Classes;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author BBran
 */
public interface iDataDAO {

    /**
     * returnere dagens dato.
     *
     * @return
     */
    public LocalDate getCurrentDate();

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     * @param personID
     */
    public void studentIsPresent(LocalDate date, int personID);

    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public boolean studentAlreadyRegistered(int personID);

    /**
     * denne metode skal kunne hente information fra serveren til at sammenligne
     * med det input der kommer fra useren
     *
     * @param email
     * @param password
     * @return
     */
    public boolean Login(String email, String password);

    public int getRole(String username, String password);

    /**
     * Get a list of set schooldays off from DB.
     *
     * @return
     */
    public List<LocalDate> schoolDaysOff();

    /**
     * Get a list of all days student(personID) was present.
     *
     * @return
     */
    public List<LocalDate> daysPresent(int personID);
    
    /**
      * 
      * @return 
      */
     public List<Classes> getTeacherClasses();
     /** Get a list of x days student(personID) was present.
     *
     * @return
     */
    public List<LocalDate> xDaysPresent (int personID, int x);    

}
