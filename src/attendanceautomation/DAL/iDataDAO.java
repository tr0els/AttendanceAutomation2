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
    public boolean Login(String email, byte[] HashedPassword);


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
      * Get a list of classes associated with logged in teacher.
      * @return 
      */
     public List<Classes> getTeacherClasses();

     
     /**
      * Returns a list of students on the specified class
      * @param choiceBoxChosenClass
      * @return
      * @throws DALException 
      */
     public List<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException;

     /** Get a list of x days student(personID) was present.
     *
     * @return
     */
    public List<LocalDate> xDaysPresent (int personID, int x);    

    public void setPasswordandSalt(byte[] HashedPassword, byte[] salt);

    public byte[] getSalt(String email);

    public int getRole(String username);
    
    public Student getStudentInfo(Student selectedStudent) throws DALException;
    
    public Teacher getClassTeacher(Classes choiceBoxChosenClass) throws DALException;

}
