/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author Brian Brandt, Kim Christensen, Troels Klein, René Jørgensen &
 * Charlotte Christensen
 */
public class Teacher extends Person
{

    private String students;

    /**
     * Constructer for Teacher. Teacher klassen er extended af person så vi
     * kalder med "super" for at få dens variabler personrole blive sat til
     * Teacher ved at bruge "IsTeacher"
     *
     * @param email
     * @param password
     */
    public Teacher(String email, String password)
    {
        super(email, password);

        personRole = new IsTeacher();
    }

    /**
     * Constructor for Teacher
     */
    public Teacher()
    {

    }

}
