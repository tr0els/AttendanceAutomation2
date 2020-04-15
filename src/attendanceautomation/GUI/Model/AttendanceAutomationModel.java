/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Model;

import attendanceautomation.BE.Classes;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.BLL.BLLManager;
import attendanceautomation.DAL.DALException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Troels
 */
public class AttendanceAutomationModel
{

    private BLLManager manager;
    private ObservableList<Classes> teacherClasses;
    private ObservableList<Student> studentsInClass;
    private int choiceBoxChosenClass;
    private ObservableList<LocalDate> daysPresent;

    /**
     * Constructor Skal holdes privat da alt instantiering af denne klasse skal
     * foregå ved hjælp af singletonen's getInstance metode.
     *
     * @throws DALException
     */
    private AttendanceAutomationModel() throws DALException
    {
        manager = new BLLManager();
        daysPresent = FXCollections.observableArrayList();
        teacherClasses = FXCollections.observableArrayList();
        teacherClasses.addAll(manager.getTeacherClasses());
        studentsInClass = FXCollections.observableArrayList();
    }

    public Student getCurrentStudent(String username, String password)
    {
        return manager.getCurrentStudent(username, password);
    }

    /**
     * Første gang metoden kaldes, oprettes en instans af modellen (new).
     * Instansengemmes i konstanten INSTANCE så den kan returnes ved behov.
     */
    private static class SingletonHolder
    {

        private static final AttendanceAutomationModel INSTANCE;

        static
        {
            try
            {
                INSTANCE = new AttendanceAutomationModel();
            } catch (DALException ex)
            {
                throw new ExceptionInInitializerError("kunne ikke forbinde til BLL");
            }
        }
    }

    /**
     * Returnerer model instansen Fordi metoden er static kan den kaldes uden at
     * først lave et objekt af klassen.
     *
     * @return instans af modellen
     */
    public static AttendanceAutomationModel getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Henter datoen for idag og returnere den.
     *
     * @return Dagens dato
     */
    public LocalDate getCurrentDate()
    {
        return manager.getCurrentdate();

    }

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     */
    public void studentIsPresent(LocalDate date, int personID)
    {
        manager.studentIsPresent(date, personID);
    }

    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public String studentAlreadyRegistered(int personID)
    {

        return manager.studentAlreadyRegistered(personID);
    }

    /*
        Tager infoen fra longincontrolleren og sender det til BLL
     */
    public boolean loginModel(String email, String password)
    {
        return manager.LoginBLL(email, password);
    }

    /*
        Login controlleren skal bruge information om hvilken rolle useren har
        så denne metode returnere dette fra BLL
     */
    public int getRole(String username)
    {

        return manager.getRole(username);

    }

    /**
     * returnere fraværsprocent.
     *
     * @param personID
     * @return
     */
    public double studentAbsence(int personID)
    {
        return manager.studentAbsence(personID);
    }

    public ObservableList<LocalDate> missedDays(int personID, int x)
    {
        daysPresent.clear();
        daysPresent.addAll(manager.missedDays(personID, x));
        return daysPresent;
    }

    /*
        en funktion der sender passwords til bll for at blive hashet. 
        Denne funktion bliver ikke brugt lige nu, men skal højst sandsyneligt bruges
        i fremtiden.
     */
    public void hashPassword(String password)
    {
        manager.HashPassword(password);
    }

    /**
     * DELETE ME WHEN DONE
     */
    public void countWeekdays()
    {
        manager.countWeekdays();
    }

    public List<Classes> getTeacherClasses() throws DALException
    {
        return teacherClasses;
    }

    public ObservableList<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException
    {
        List<Student> tempStudents = manager.getStudentsInClass(choiceBoxChosenClass);
        studentsInClass.clear();
        studentsInClass.addAll(tempStudents);
        return studentsInClass;
    }

    public Student getStudentInfo(Student selectedStudent) throws DALException
    {
        Student stud = new Student();
        stud = manager.getStudentInfo(selectedStudent);
        return stud;
    }
    
    public XYChart.Series modelMissedDaysforAbsencePerDay(int personID){
        return manager.missedDaysforAbsencePerDay(personID);
    }

    public Teacher getClassTeacher(Classes choiceBoxChosenClass) throws DALException
    {
        Teacher teach = new Teacher();
        teach = manager.getClassTeacher(choiceBoxChosenClass);
        return teach;
    }

}
