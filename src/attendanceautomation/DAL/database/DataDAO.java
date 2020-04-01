/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL.database;

import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.iDataDAO;
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
     * checks if student already registered today, returns boolean value accordingly
     * @param personID
     * @return 
     */
    @Override
    public boolean studentAlreadyRegistered(int personID) {
        try ( Connection con = dbCon.getConnection()) {
           
            String sql = "SELECT COUNT(*) as count FROM ATTENDANCE WHERE person_id = ? AND date = CONVERT(date, GETDATE())";
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, personID);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next())
            {
                int count = rs.getInt("count");
                
                System.out.println(count);
                
                if (count == 1)
                {
                    System.out.println("true");
                    return true;
                    
                }
                
            }
            
        } catch (DALException | SQLException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
