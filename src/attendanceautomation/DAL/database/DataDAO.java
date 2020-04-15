/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL.database;

import attendanceautomation.BE.Classes;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.iDataDAO;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BBran
 */
public class DataDAO implements iDataDAO
{

    Student stud = new Student("Studentemail", "123");
    Teacher teach = new Teacher("Teacheremail", "123");

    private DatabaseConnector dbCon;

    public DataDAO() throws DALException
    {

        dbCon = new DatabaseConnector();

    }

    /**
     * returnere dagens dato via SQL now
     *
     * @return
     */
    @Override
    public LocalDate getCurrentDate()
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT CONVERT(date, GETDATE()) as [Current_Date]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                LocalDate currentdate = rs.getDate("Current_Date").toLocalDate();
                return currentdate;
            }

        } catch (SQLException | DALException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * sender en dato og personID ind i DB'en for at registrere personen er
     * tilstede på denne dato.
     *
     * @param date
     */
    @Override
    public void studentIsPresent(LocalDate date, int personID)
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO ATTENDANCE (person_id,date,last_changed) VALUES (?,?,CURRENT_TIMESTAMP)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);
            st.setDate(2, Date.valueOf(date));

            st.executeUpdate();

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * checks if student already registered today, returns boolean value
     * accordingly
     *
     * @param personID
     * @return
     */
    @Override
    public boolean studentAlreadyRegistered(int personID)
    {

        Boolean registeredStatus = false;

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT COUNT(*) as count FROM ATTENDANCE WHERE person_id = ? AND date = CONVERT(date, GETDATE())";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);

            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                int count = rs.getInt("count");

                if (count == 1)
                {
                    System.out.println("true");
                    registeredStatus = true;
                } else
                {
                    registeredStatus = false;
                }
            }
        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registeredStatus;
    }


    /*
        tager det info fra brugeren og sammenligner det med data på serveren for
        at se om det input brugeren har puttet ind findes i databasen
     */
    public boolean Login(String email, byte[] password)
    {

        boolean verifiedLogin = true;

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT email, password FROM PERSON WHERE email = ? AND password = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, email);
            st.setBytes(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next() == false)
            {
                System.out.println("ResultSet is empty");
                verifiedLogin = false;
            } else
            {
                do
                {
                    String emailDAO = rs.getString("email");
                    byte[] passwordDAO = rs.getBytes("password");

                    if (emailDAO.equals(email) && Arrays.equals(passwordDAO, password))
                    {
                        verifiedLogin = true;
                        break;
                    }
                } while (rs.next());
            }

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return verifiedLogin;
    }

    /*
        henter saltet fra serveren så den kan bruges til at hashe et identisk
        hash fra serveren.
     */
    public byte[] getSalt(String email)
    {

        byte[] salt = null;

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT email, salt FROM PERSON WHERE email = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                salt = rs.getBytes("salt");
            }

        } catch (Exception e)
        {
        }

        return salt;

    }

    /*
        finder rollen på brugeren som har succesfuldt logget ind.
     */
    @Override

    public int getRole(String email)
    {
        int role = 0;

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT email, role_id FROM PERSON WHERE email = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                role = rs.getInt("role_id");
            }

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role;
    }

    /**
     * Get a list of set schooldays off from DB.
     *
     * @return
     */
    @Override
    public List<LocalDate> schoolDaysOff()
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT date FROM SCHOOL_DAYS_OFF";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            ArrayList<LocalDate> daysOff = new ArrayList<>();
            while (rs.next())
            {
                LocalDate date = rs.getDate("date").toLocalDate();
                daysOff.add(date);

            }

            return daysOff;

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get a list of all days student(personID) was present.
     *
     * @return
     */
    @Override
    public List<LocalDate> daysPresent(int personID)
    {

        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT date FROM ATTENDANCE WHERE person_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);

            ResultSet rs = st.executeQuery();

            ArrayList<LocalDate> daysPresent = new ArrayList<>();
            while (rs.next())
            {
                LocalDate date = rs.getDate("date").toLocalDate();
                daysPresent.add(date);

            }

            return daysPresent;

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Returnerer liste med hvilke classes lærerne har.
     *
     * @return
     * @throws DALException
     */
    public List<Classes> getTeacherClasses()
    {
        ArrayList<Classes> teacherClasses = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM CLASS;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Add all to a list
                Classes classes = new Classes();
                classes.setId(rs.getInt("class_id"));
                classes.setClassName(rs.getString("class_name"));

                teacherClasses.add(classes);
            }
            //Return
            return teacherClasses;

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Student> getStudentsInClass(Classes choiceBoxChosenClass) throws DALException
    {
        ArrayList<Student> allStudentsInClass = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            Integer idClasses = choiceBoxChosenClass.getId();
            // SQL code. 
            String sql = "select * from person p, PERSON_CLASS pc\n"
                    + "where p.person_id = pc.person_id\n"
                    + "and pc.class_id = " + idClasses + "\n"
                    + "and p.role_id = 1;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {

                // Add all to a list
                Student student = new Student();
                student.setPersonID(rs.getInt("person_id"));
                student.setName(rs.getString("name"));

                allStudentsInClass.add(student);
            }
            //Return
            return allStudentsInClass;

        } catch (SQLException ex)
        {
            Logger.getLogger(DataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DALException("Can´t do that");
        }
    }

    /**
     * Get a list of x days student(personID) was present.
     *
     * @return
     */
    @Override
    public List<LocalDate> xDaysPresent(int personID, int x)
    {
        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT date FROM ATTENDANCE WHERE person_id = ? AND date >= DATEADD(day, -?, GETDATE())";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);
            st.setInt(2, x);

            ResultSet rs = st.executeQuery();

            ArrayList<LocalDate> daysPresent = new ArrayList<>();
            while (rs.next())
            {
                LocalDate date = rs.getDate("date").toLocalDate();
                daysPresent.add(date);

            }

            return daysPresent;

        } catch (DALException | SQLException ex)
        {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /*
        denne funktion bliver ikke brugt lige nu. Den tager det hashet password
        og salt og gemmer det på serveren
     */
    @Override
    public void setPasswordandSalt(byte[] HashedPassword, byte[] salt)
    {
        try ( Connection con = dbCon.getConnection())
        {

            String sql = "UPDATE PERSON SET salt = ?, password = ? WHERE person_id = ? ";

            PreparedStatement st = con.prepareStatement(sql);

            st.setBytes(1, salt);
            st.setBytes(2, HashedPassword);
            st.setInt(3, 5);

            st.executeQuery();

        } catch (Exception e)
        {
        }
    }

    public Student getStudentInfo(Student selectedStudent) throws DALException
    {
        Student allStudentInfo = new Student();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            int studId = selectedStudent.getPersonID();
            // SQL code. 
            String sql = "SELECT * FROM PERSON WHERE person_id = " + studId + ";";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Add all to a list
                Student student = new Student();
                student.setPersonID(rs.getInt("person_id"));
                student.setName(rs.getString("name"));
                student.setPhoneNumber(rs.getInt("phone"));
                student.setEmail(rs.getString("email"));

                allStudentInfo = student;
            }
            //Return
            return allStudentInfo;

        } catch (SQLException ex)
        {
            Logger.getLogger(DataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DALException("Can´t make Student");
        }
    }

    @Override
    public Student getCurrentStudent(String username, byte[] password)
    {
        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM PERSON WHERE email = ? AND password = ?;";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, username);
            st.setBytes(2, password);

            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                Student student = new Student();
                student.setPersonID(rs.getInt("person_id"));
                student.setName(rs.getString("name"));
                student.setPhoneNumber(rs.getInt("phone"));
                student.setEmail(rs.getString("email"));
                return student;
            }

        } catch (Exception ex)
        {

        }
        return null;
    }

    public Teacher getClassTeacher(Classes choiceBoxChosenClass) throws DALException
    {
        Teacher studentsTeacher = new Teacher();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            int classId = choiceBoxChosenClass.getId();
            // SQL code. 
            String sql = "SELECT name FROM person p, PERSON_CLASS pc\n"
                    + "where p.person_id = pc.person_id\n"
                    + "and pc.class_id = " + classId + "\n" 
                    + "and p.role_id = 2;";

            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Add all to a list
                Teacher teacher = new Teacher();
                teacher.setName(rs.getString("name"));
                
                studentsTeacher = teacher;
            }
            //Return
            return studentsTeacher;

        } catch (SQLException ex)
        {
            Logger.getLogger(DataDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new DALException("Can´t make Student");
        }
    }
    
}
