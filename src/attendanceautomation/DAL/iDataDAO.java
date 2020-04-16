/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import attendanceautomation.BE.Classes;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public interface iDataDAO
{

    /**
     * Returnerer dagens dato.
     *
     * @return
     */
    public LocalDate getCurrentDate();

    /**
     * Sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     * @param personID
     */
    public void studentIsPresent(LocalDate date, int personID);

    /**
     * Sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public boolean studentAlreadyRegistered(int personID);

    /**
     * Tager det info fra brugeren og sammenligner det med data på serveren for
     * at se om det input brugeren har puttet ind findes i databasen
     *
     * @param email
     * @param password
     * @return
     */
    public boolean Login(String email, byte[] HashedPassword);

    /**
     * Returnerer en liste, med skoledage hvor der er fri, fra DB'en
     *
     * @return
     */
    public List<LocalDate> schoolDaysOff();

    /**
     * Returnerer en liste med alle dage en studerende(personID) var tilstede
     *
     * @return
     */
    public List<LocalDate> daysPresent(int personID);

    /**
     * Returnerer liste med hvilke classes lærerne har.
     *
     * @return
     */
    public List<Classes> getTeacherClasses();

    /**
     * Returnerer en liste med students i den valgte Classes
     *
     * @param choiceBoxChosenClass
     * @return
     * @throws DALException
     */
    public List<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException;

    /**
     * Returnerer en liste med x dage hvor den studerende(personID) var tilstede
     *
     * @return
     */
    public List<LocalDate> xDaysPresent(int personID, int x);

    /**
     * Denne metode bliver ikke brugt lige nu. Den tager det hashet password og
     * salt og gemmer det på serveren
     *
     * @param HashedPassword
     * @param salt
     */
    public void setPasswordandSalt(byte[] HashedPassword, byte[] salt);

    /**
     * Henter saltet fra serveren så den kan bruges til at hashe et identisk
     * hash fra serveren.
     *
     * @param email
     * @return
     */
    public byte[] getSalt(String email);

    /**
     * Finder rollen på brugeren som har succesfuldt logget ind.
     *
     * @param username
     * @return
     */
    public int getRole(String username);

    /**
     * Returnerer et Student objekt med informationer på baggrund af et id
     *
     * @param selectedStudent
     * @return
     * @throws DALException
     */
    public Student getStudentInfo(Student selectedStudent) throws DALException;

    /**
     * Sikkerhedstjek - Sammenligner username og password med det der ligger i
     * Databasen.
     *
     * @param username
     * @param password
     * @return
     */
    public Student getCurrentStudent(String username, byte[] password);

    /**
     * Sikkerhedstjek - Sammenligner username og password med det der ligger i
     * Databasen.
     *
     * @param username
     * @param HashedPassword
     * @return
     */
    public Teacher getCurrentTeacher(String username, byte[] HashedPassword);

    /**
     * Returnerer en Teacher på baggrund af den valgte Student
     *
     * @param selectedStudent
     * @return
     * @throws DALException
     */
    public Teacher getStudentTeacher(Student selectedStudent) throws DALException;

    /**
     * Returnerer en liste med Students på baggrund af en valgt klasse
     *
     * @param choiceBoxChosenClass
     * @return
     * @throws DALException
     */
    public List<Student> getAllStudents(Classes choiceBoxChosenClass) throws DALException;

}
