/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.BE.Classes;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.time.DayOfWeek;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.css.Size;
import javafx.scene.chart.XYChart;

/**
 *
 * @author BBran
 */
public class BLLManager {

    private final iDataDAO datadao;

    private final LocalDate semesterStart = LocalDate.of(2020, 1, 27); //evt. lave det om til dynamisk via tabel/DB?
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
    public void studentIsPresent(final LocalDate date, final int personID) {

        datadao.studentIsPresent(date, personID);
    }

    /**
     * Tjekker om det er en skoledag og/eller om eleven har registeret sig.
     *
     * @param personID
     * @return
     */
    public String studentAlreadyRegistered(final int personID) {

        final LocalDate date = datadao.getCurrentDate();
        final DayOfWeek dw = date.getDayOfWeek();
        String btnMessage = null;

        if (!daysOff.contains(date) && dw == DayOfWeek.SATURDAY && dw == DayOfWeek.SUNDAY) {
            btnMessage = "No School Today!";
        } else if (datadao.studentAlreadyRegistered(personID) == true) {
            btnMessage = "Already Registered";
        }

        return btnMessage;
    }

    /*

        denne metode tager det info som kommer fra brugeren. den finder det gemte salt
        på serveren med emailen som sammenligner. derefter hasher den passwordet
        så vi kan sammenligne det med det hash som ligger på serveren.
     */
    public boolean LoginBLL(final String email, final String password) {

        boolean verifiedLogin = false;
        final byte[] salt = datadao.getSalt(email);

        try {

            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(salt);

            final byte[] HashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            //datadao.setPasswordandSalt(HashedPassword, salt);
            verifiedLogin = datadao.Login(email, HashedPassword);

        } catch (final Exception e) {
        }

        return verifiedLogin;

    }

    /*
        rollen som model spørger efter bliver returneret her
     */
    public int getRole(final String username) {

        return datadao.getRole(username);

    }

    /*
        denne metoder bliver ikke brugt lige nu, men den hasher passwords som så 
        kan gemmes på serveren.
     */
    public void HashPassword(final String passwordToHash) {
        final byte[] salt = createSalt();

        try {

            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            final byte[] HashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            datadao.setPasswordandSalt(HashedPassword, salt);

        } catch (final Exception e) {
        }
    }

    /**
     * udregner skoledage fra semesterStart og tager højde for weekender og
     * helligdage (SCHOOL_DAYS_OFF i DB).
     */
    public int countWeekdays() {
        int weekdays = 0;

        LocalDate date = semesterStart;
        final LocalDate endDate = getCurrentdate();

        while (date.isBefore(endDate)) {

            final DayOfWeek dw = date.getDayOfWeek();
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
    public double studentAbsence(final int personID) {

        List<LocalDate> daysPresent = new ArrayList<>();

        daysPresent = datadao.daysPresent(personID);

        final double countDaysPresent = daysPresent.size();
        final double countSchooldays = countWeekdays();

        final double absencePercent = 100 - ((countDaysPresent / countSchooldays) * 100);

        return absencePercent;

    }

    /**
     * returnere liste over dage hvor eleven ikke har været i skole hvor x er
     * intervallet fra dagens dato listen tager højde for
     * helligdage(SCHOOL_DAYS_OFF i DB) og weekender.
     *
     * @param personID
     * @return
     */
    public List<Classes> getTeacherClasses() throws DALException {
        return datadao.getTeacherClasses();
    }

    public List<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException {
        return datadao.getStudentsInClass(choiceBoxChosenClass);
    }

    public Student getStudentInfo(Student selectedStudent) throws DALException {
        return datadao.getStudentInfo(selectedStudent);
    }

    public Teacher getClassTeacher(Classes choiceBoxChosenClass) throws DALException {
        return datadao.getClassTeacher(choiceBoxChosenClass);
    }

    public List<LocalDate> missedDays(int personID, int x) {

        List<LocalDate> daysPresent = new ArrayList<>();
        daysPresent = datadao.xDaysPresent(personID, x);

        List<LocalDate> missedDays = new ArrayList<>();

        LocalDate today = getCurrentdate();
        LocalDate intervalDate = today.minusDays(x - 1);

        while (intervalDate.isBefore(today)) {
            DayOfWeek dw = intervalDate.getDayOfWeek();
            if (!daysPresent.contains(intervalDate) && !daysOff.contains(intervalDate) && dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY) {
                missedDays.add(intervalDate);
            }
            intervalDate = intervalDate.plusDays(1);
        }

        return missedDays;
    }

    public XYChart.Series missedDaysforAbsencePerDay(int personID) {

        int totalDays = 0;

        LocalDate startDate = semesterStart;
        final LocalDate endDate = getCurrentdate();

        while (startDate.isBefore(endDate)) {
            ++totalDays;
            startDate = startDate.plusDays(1);

        }

        List<LocalDate> days = new ArrayList<>();
        days.addAll(missedDays(personID, totalDays));

        double monday = 0;
        double tuesday = 0;
        double wednesday = 0;
        double thursday = 0;
        double friday = 0;
        double allAbsentDays = 0;

        for (int i = 0; i < days.size(); i++) {
            String[] test = days.get(i).toString().split("-");

            LocalDate localdate = LocalDate.of(Integer.parseInt(test[0]), Integer.parseInt(test[1]), Integer.parseInt(test[2]));

            DayOfWeek dayOfWeek = localdate.getDayOfWeek();

            System.out.println(dayOfWeek);

            switch (dayOfWeek) {
                case MONDAY:
                    ++monday;
                    ++allAbsentDays;
                    break;
                case TUESDAY:
                    ++tuesday;
                    ++allAbsentDays;
                    break;
                case WEDNESDAY:
                    ++wednesday;
                    ++allAbsentDays;
                    break;
                case THURSDAY:
                    ++thursday;
                    ++allAbsentDays;
                    break;
                case FRIDAY:
                    ++friday;
                    ++allAbsentDays;
                    break;
            }

        }

        monday = (monday / allAbsentDays) * 100;
        System.out.println(monday);

        tuesday = (tuesday / allAbsentDays) * 100;
        System.out.println(tuesday);

        wednesday = (wednesday / allAbsentDays) * 100;
        System.out.println(wednesday);

        thursday = (thursday / allAbsentDays) * 100;
        System.out.println(thursday);

        friday = (friday / allAbsentDays) * 100;
        System.out.println(friday);
        
        XYChart.Series weekdaysabsent = new XYChart.Series<>();
        weekdaysabsent.getData().add(new XYChart.Data<>("Monday", monday));
        weekdaysabsent.getData().add(new XYChart.Data<>("Tuesday", tuesday));
        weekdaysabsent.getData().add(new XYChart.Data<>("Wednesday", wednesday));
        weekdaysabsent.getData().add(new XYChart.Data<>("Thursday", thursday));
        weekdaysabsent.getData().add(new XYChart.Data<>("friday", friday));

        return weekdaysabsent;
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

    public Student getCurrentStudent(String username, String password) {

        final byte[] salt = datadao.getSalt(username);

        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(salt);

            final byte[] HashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return datadao.getCurrentStudent(username, HashedPassword);
        } catch (Exception ex) {
        }

        return null;
    }

}
