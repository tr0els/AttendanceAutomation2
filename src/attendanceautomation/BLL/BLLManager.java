/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
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
    public boolean LoginBLL(String email, String password) {
        return datadao.Login(email, password);
    }

    /*
        rollen som model spørger efter bliver returneret her
     */
    public int getRole(String username, String password) {

        return datadao.getRole(username, password);

    }

    /**
     * calculates schooldays from semesterstart, taking in account weekends and
     * dates from DB (SCHOOL_DAYS_OFF)
     */
    public int countWeekdays() {
        int weekdays = 0;
        List<LocalDate> daysOff = new ArrayList<>();

        daysOff = datadao.schoolDaysOff();
        LocalDate date = semesterStart;
        LocalDate endDate = getCurrentdate();

        System.out.println(date); //DELETE ME WHEN DONE
        System.out.println(daysOff); //DELETE ME WHEN DONE

        daysOff.contains(date);
        while (date.isBefore(endDate)) {
           
            DayOfWeek dw = date.getDayOfWeek();
            if (!daysOff.contains(date) && dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY) {
                ++weekdays;
            }

            date = date.plusDays(1);

        }

        System.out.println(endDate); //DELETE ME WHEN DONE
        System.out.println(weekdays); //DELETE ME WHEN DONE

        return weekdays;
    }

    /**
     * Calculates students (personID) abscence in percent
     *
     * @param personID
     */
    public double studentAbsence(int personID) {

        List<LocalDate> daysPresent = new ArrayList<>();

        daysPresent = datadao.daysPresent(personID);

        double countDaysPresent = daysPresent.size();
        double countSchooldays = countWeekdays();

        double absencePercent = ((countDaysPresent / countSchooldays) * 100);

        System.out.println(absencePercent); //DELETE ME WHEN DONE
        System.out.println(countDaysPresent); //DELETE ME WHEN DONE
        System.out.println(countSchooldays); //DELETE ME WHEN DONE

        return absencePercent;

    }

}
