/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import attendanceautomation.DAL.DALException;
import attendanceautomation.DAL.database.DataDAO;
import attendanceautomation.DAL.iDataDAO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author BBran
 */
public class BLLManager {

    private iDataDAO datadao;

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
