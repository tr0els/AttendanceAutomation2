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
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public class AttendanceAutomationModel {

    private BLLManager manager;
    private ObservableList<Classes> teacherClasses;
    private ObservableList<Student> studentsInClass;
    private ObservableList<Student> allStudents;
    private int choiceBoxChosenClass;
    private ObservableList<LocalDate> daysPresent;

    /**
     * Constructor Skal holdes privat da alt instantiering af denne klasse skal
     * foregå ved hjælp af singletonen's getInstance metode.
     *
     * @throws DALException
     */
    private AttendanceAutomationModel() throws DALException {
        manager = new BLLManager();
        daysPresent = FXCollections.observableArrayList();
        teacherClasses = FXCollections.observableArrayList();
        teacherClasses.addAll(manager.getTeacherClasses());
        studentsInClass = FXCollections.observableArrayList();
        allStudents = FXCollections.observableArrayList();
    }

    /**
     * Den sender login info fra useren ned til BLL
     *
     * @param username
     * @param password
     * @return Student object
     */
    public Student getCurrentStudent(String username, String password) {
        return manager.getCurrentStudent(username, password);
    }

    /**
     * Den sender login info fra useren ned til BLL
     *
     * @param username
     * @param password
     * @return Teacher object
     */
    public Teacher getCurrentTeacher(String username, String password) {
        return manager.getCurrentTeacher(username, password);
    }

    /**
     * Første gang metoden kaldes, oprettes en instans af modellen (new).
     * Instansengemmes i konstanten INSTANCE så den kan returnes ved behov.
     */
    private static class SingletonHolder {

        private static final AttendanceAutomationModel INSTANCE;

        static {
            try {
                INSTANCE = new AttendanceAutomationModel();
            } catch (DALException ex) {
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
        daysPresent.remove(date);
    }

    /**
     * sender en personID ind i DB for at tjekke om studenten har registreret
     * sig idag.
     *
     * @param personID
     * @return
     */
    public String studentAlreadyRegistered(int personID) {

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
    public int getRole(String username) {

        return manager.getRole(username);

    }

    /**
     * returnere fraværsprocent.
     *
     * @param personID
     * @return
     */
    public double studentAbsence(int personID) {
        return manager.studentAbsence(personID);
    }

    /**
     * den henter info fra databasen omkring hvilke dage eleven har fremmøde
     *
     * @param personID
     * @param x
     * @return
     */
    public ObservableList<LocalDate> missedDays(int personID, int x) {
        daysPresent.clear();
        daysPresent.addAll(manager.missedDays(personID, x));
        return daysPresent;
    }

    /*
        en funktion der sender passwords til bll for at blive hashet. 
        Denne funktion bliver ikke brugt lige nu, men skal højst sandsyneligt bruges
        i fremtiden.
     */
    public void hashPassword(String password) {
        manager.HashPassword(password);
    }

    /**
     * returnere antal dage siden semesterstart
     */
    public int countAlldays() {
        return manager.countAlldays();
    }

    /**
     * returnere en list af classes og sortere dem efter navn
     *
     * @return
     * @throws DALException
     */
    public List<Classes> getTeacherClasses() throws DALException {
        Comparator<Classes> byName = (Classes cl1, Classes cl2) -> cl1.getClassName().compareTo(cl2.getClassName());
        teacherClasses.sort(byName);
        return teacherClasses;
    }

    /**
     * Returnere en liste af elever i en valgt klasse.
     *
     * @param choiceBoxChosenClass
     * @return
     * @throws DALException
     */
    public ObservableList<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException {
        Comparator<Student> byAbsence = (Student stud1, Student stud2) -> (int) (stud2.getAbsence() - stud1.getAbsence());

        List<Student> tempStudents = manager.getStudentsInClass(choiceBoxChosenClass);
        studentsInClass.clear();
        studentsInClass.addAll(tempStudents);

        for (int i = 0; i < studentsInClass.size(); i++) {
            studentsInClass.get(i).setAbsence(manager.studentAbsence(studentsInClass.get(i).getPersonID()));
        }

        studentsInClass.sort(byAbsence);

        return studentsInClass;
    }

    /**
     * Henter informationer på en student, som navn, telefon, osv...
     *
     * @param selectedStudent
     * @return
     * @throws DALException
     */
    public Student getStudentInfo(Student selectedStudent) throws DALException {
        Student stud = new Student();
        stud = manager.getStudentInfo(selectedStudent);
        return stud;
    }

    /**
     * retunere and XYChart.Series med det info som er nødvendigt for at vise
     * fravær procent per dag.
     *
     * @param personID
     * @return
     */
    public XYChart.Series modelMissedDaysforAbsencePerDay(int personID) {
        return manager.missedDaysforAbsencePerDay(personID);
    }

    /**
     * Retunere en lære til den valgte student.
     *
     * @param selectedStudent
     * @return
     * @throws DALException
     */
    public Teacher getStudentTeacher(Student selectedStudent) throws DALException {
        Teacher teach = new Teacher();
        teach = manager.getStudentTeacher(selectedStudent);
        return teach;
    }

    /**
     * Returnere en liste af alle de elever som tilhøre den valgte klasse.
     *
     * @param choiceBoxChosenClass
     * @return
     * @throws DALException
     */
    public ObservableList<Student> getAllStudents(Classes choiceBoxChosenClass) throws DALException {
        Comparator<Student> byAbsence = (Student stud1, Student stud2) -> (int) (stud2.getAbsence() - stud1.getAbsence());

        List<Student> tempStudents = manager.getAllStudents(choiceBoxChosenClass);
        allStudents.clear();
        allStudents.addAll(tempStudents);

        for (int i = 0; i < allStudents.size(); i++) {
            allStudents.get(i).setAbsence(manager.studentAbsence(allStudents.get(i).getPersonID()));
        }

        allStudents.sort(byAbsence);

        return allStudents;
    }
}
