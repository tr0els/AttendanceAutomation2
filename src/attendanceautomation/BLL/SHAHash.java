/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitystuff;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * @author Draik
 */
public class SHAHash {

    public void HashPassword(String passwordToHash) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(createSalt());

            byte[] HashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            bytesToStringHex(HashedPassword);

        } catch (Exception e) {
        }
    }

    public byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        bytesToStringHex(salt);
        return salt;
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void bytesToStringHex(byte[] bytes) {

        char[] hexChars = new char[bytes.length * 2];

        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];

        }
        System.out.println(hexChars);
    }

}
