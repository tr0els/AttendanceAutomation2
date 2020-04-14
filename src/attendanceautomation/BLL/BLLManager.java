/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.BE.Classes;
import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BBran
 */
public class BLLManager {

    private iDataDAO datadao;

    private LocalDate semesterStart = LocalDate.of(2020, 1, 27); //evt. lave det om til dynamisk via tabel/DB?
    private List<LocalDate> daysOff;

    public BLLManager() throws DALException {

        datadao = new DataDAO();
        
        daysOff = new ArrayList<>();
        daysOff = datadao.schoolDaysOff();
        
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
     * Tjekker om det er en skoledag og/eller om eleven har registeret sig.
     *
     * @param personID
     * @return
     */
    public String studentAlreadyRegistered(int personID) {

        LocalDate date = datadao.getCurrentDate();
        DayOfWeek dw = date.getDayOfWeek();
        String btnMessage = null;
        
        if (!daysOff.contains(date) && dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY)
        {
            btnMessage = "No School Today!";
        }
        else if (datadao.studentAlreadyRegistered(personID) == true)       
        {
            btnMessage = "Already Registered";
        }
        
        return btnMessage;
    }

    /*

        denne metode tager det info som kommer fra brugeren. den finder det gemte salt
        på serveren med emailen som sammenligner. derefter hasher den passwordet
        så vi kan sammenligne det med det hash som ligger på serveren.
     */
    public boolean LoginBLL(String email, String password) {
        
        boolean verifiedLogin = false;
        byte[] salt = datadao.getSalt(email);

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(salt);

            byte[] HashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            //datadao.setPasswordandSalt(HashedPassword, salt);

            verifiedLogin =  datadao.Login(email, HashedPassword);

        } catch (Exception e) {
        }
        
        return verifiedLogin;

    }

    /*
        rollen som model spørger efter bliver returneret her
     */

    public int getRole(String username) {

        return datadao.getRole(username);

    }

    /*
        denne metoder bliver ikke brugt lige nu, men den hasher passwords som så 
        kan gemmes på serveren.
    */
    public void HashPassword(String passwordToHash) {
        byte[] salt = createSalt();

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            byte[] HashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            datadao.setPasswordandSalt(HashedPassword, salt);

        } catch (Exception e) {
        }
    }

    /**
     * udregner skoledage fra semesterStart og tager højde for weekender
     * og helligdage (SCHOOL_DAYS_OFF i DB).
     */
    public int countWeekdays() { 
        int weekdays = 0;

        LocalDate date = semesterStart;
        LocalDate endDate = getCurrentdate();


        while (date.isBefore(endDate)) {
           
            DayOfWeek dw = date.getDayOfWeek();
            if (!daysOff.contains(date) && dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY) {
                ++weekdays;
            }

            date = date.plusDays(1);

        }

        return weekdays;
    }

    /**
     * Udregner og returnere en students fraværsprocent
     *
     * @param personID
     */
    public double studentAbsence(int personID) {

        List<LocalDate> daysPresent = new ArrayList<>();

        daysPresent = datadao.daysPresent(personID);

        double countDaysPresent = daysPresent.size();
        double countSchooldays = countWeekdays();

        double absencePercent = 100 - ((countDaysPresent / countSchooldays) * 100);

        System.out.println(absencePercent); //DELETE ME WHEN DONE
        System.out.println(countDaysPresent); //DELETE ME WHEN DONE
        System.out.println(countSchooldays); //DELETE ME WHEN DONE

        return absencePercent;

    }
    
    /**
     * returnere liste over dage hvor eleven ikke har været i skole hvor x er intervallet fra dagens dato
     * listen tager højde for helligdage(SCHOOL_DAYS_OFF i DB) og weekender.
     * @param personID
     * @return 
     */

    public List<Classes> getTeacherClasses() throws DALException
    {
        return datadao.getTeacherClasses();
    }
    
    public List<LocalDate> missedDays(int personID, int x)
    {
        List<LocalDate> daysPresent = new ArrayList<>();
        daysPresent = datadao.xDaysPresent(personID, x);

        List<LocalDate> missedDays = new ArrayList<>();
        
        LocalDate today = getCurrentdate();
        LocalDate intervalDate = today.minusDays(x);
        
        while (intervalDate.isBefore(today))
                {
                    DayOfWeek dw = intervalDate.getDayOfWeek();
                    if (!daysPresent.contains(intervalDate) && !daysOff.contains(intervalDate) && dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY)
                    {
                        missedDays.add(intervalDate);
                    }
                    intervalDate = intervalDate.plusDays(1);
                }
        
        return missedDays;
    }
    
    

    /*
    dette var brugt til at lave salt som gør hashet passwords mere sikkert.
     */
    public byte[] createSalt() {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }


}
