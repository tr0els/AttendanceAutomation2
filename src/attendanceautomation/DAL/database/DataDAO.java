/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL.database;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BBran
 */
public class DataDAO implements iDataDAO {

    Student stud = new Student("Studentemail", "123");
    Teacher teach = new Teacher("Teacheremail", "123");

    private DatabaseConnector dbCon;

    public DataDAO() throws DALException {

        dbCon = new DatabaseConnector();

    }

    /**
     * returnere dagens dato via SQL now
     *
     * @return
     */
    @Override
    public LocalDate getCurrentDate() {

        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT CONVERT(date, GETDATE()) as [Current_Date]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                LocalDate currentdate = rs.getDate("Current_Date").toLocalDate();
                return currentdate;
            }

        } catch (SQLException | DALException ex) {
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
    public void studentIsPresent(LocalDate date, int personID) {

        try ( Connection con = dbCon.getConnection()) {

            String sql = "INSERT INTO ATTENDANCE (person_id,date,last_changed) VALUES (?,?,CURRENT_TIMESTAMP)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);
            st.setDate(2, Date.valueOf(date));

            st.executeUpdate();

        } catch (DALException | SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(date);
        System.out.println(personID);
    }

    /**
     * checks if student already registered today, returns boolean value
     * accordingly
     *
     * @param personID
     * @return
     */
    @Override
    public boolean studentAlreadyRegistered(int personID) {

        Boolean registeredStatus = false;

        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT COUNT(*) as count FROM ATTENDANCE WHERE person_id = ? AND date = CONVERT(date, GETDATE())";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, personID);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                System.out.println(count);

                if (count == 1) {
                    System.out.println("true");
                    registeredStatus = true;
                } else {
                    registeredStatus = false;
                }
            }
        } catch (DALException | SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registeredStatus;
    }

    
    public boolean Login(String email, byte[] password) {

        boolean verifiedLogin = true;

        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT email, password FROM PERSON WHERE email = ? AND password = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, email);
            st.setBytes(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next() == false) {
                System.out.println("ResultSet is empty");
                verifiedLogin = false;
            } else {
                do {
                    String emailDAO = rs.getString("email");
                    String passwordDAO = rs.getString("password");

                    if (emailDAO.equals(emailDAO) && passwordDAO.equals(passwordDAO)) {
                        verifiedLogin = true;
                    }
                } while (rs.next());
            }

        } catch (DALException | SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return verifiedLogin;
    }
    
    public byte[] getSalt(String email){
        
        byte[] salt = null;
        
        try (Connection con = dbCon.getConnection()){
            
            String sql = "SELECT email, salt FROM PERSON WHERE email = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setNString(1, email);
            
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                salt = rs.getBytes("salt");
            }
            
        } catch (Exception e) {
        }
        
         return salt;
         
    }

    @Override
    public int getRole(String email, String password) {
        int role = 0;

        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT email, password, role_id FROM PERSON WHERE email = ? AND password = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setNString(1, email);
            st.setNString(2, password);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                role = rs.getInt("role_id");
            }

        } catch (DALException | SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role;
    }

    public void hashPassword() {
        try ( Connection con = dbCon.getConnection()) {

            String sql = "UPDATE PERSON SET password = ? WHERE person_id = ?";

        } catch (Exception e) {
        }
    }

    @Override
    public void setPasswordandSalt(byte[] HashedPassword, byte[] salt) {
        try ( Connection con = dbCon.getConnection()) {

            String sql = "UPDATE PERSON SET salt = ?, password = ? WHERE person_id = ? ";

            PreparedStatement st = con.prepareStatement(sql);

            st.setBytes(1, salt);
            st.setBytes(2, HashedPassword);
            st.setInt(3, 5);

            st.executeQuery();

        } catch (Exception e) {
        }
    }

    @Override
    public boolean Login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
