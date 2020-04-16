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
public class Person
{

    private int personID;
    private String name;
    public String email;
    public String password;
    private int phoneNumber;
    public Role personRole;

    /**
     * Constructor for Person
     */
    public Person()
    {

    }

    /**
     * Constructor for Person som tager imod email og password
     *
     * @param email
     * @param password
     */
    public Person(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    /**
     * Returnerer personens rolle
     *
     * @return
     */
    public String CheckRole()
    {
        return personRole.Role();
    }

    /**
     * Denne metode lader os skifte rollen på et object fx. student -> teacher
     *
     * @param setRole
     */
    public void setRole(Role setRole)
    {
        personRole = setRole;
    }

    /**
     * Returnerer Person navn
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sætter Person navn
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returnerer Person email
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sætter Person email
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Returnerer Person password
     *
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sætter Person password
     *
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returnerer Person phoneNumber
     *
     * @return
     */
    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sætter Person phonenumber
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sætter Person personId
     *
     * @param personID
     */
    public void setPersonID(int personID)
    {
        this.personID = personID;
    }

    /**
     * Returnerer Person personId
     *
     * @return
     */
    public int getPersonID()
    {
        return personID;
    }

    /**
     * Definerer en toString metode til at tilgå Classes
     *
     * @return
     */
    @Override
    public String toString()
    {
        return name;
    }

}
