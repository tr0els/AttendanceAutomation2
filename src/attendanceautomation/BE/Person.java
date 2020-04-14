/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author Kim
 */
public class Person

    {
    
    private int personID;
    private String name;
    public String email;
    public String password;
    private int phoneNumber;

    public Person()
    {
        
    }
    
    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //using the Role interface to dynamically change the role of the person
    public Role personRole;

    /*
        returnere personens rolle
    */
    public String CheckRole() {
        return personRole.Role();
    }

    /*
        denne metode lader os skifte rollen på et object fx. student -> teacher
    */
    public void setRole(Role setRole) {
        personRole = setRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setPersonID(int personID){
        this.personID = personID;
    }

    public int getPersonID(){
        return personID;
    }

    @Override
    public String toString()
    {
        return name;
    }

    

}