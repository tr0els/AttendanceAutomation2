/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Charlotte
 */
public abstract class User {
    
    private final IntegerProperty userID;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty phoneNr;
    private final StringProperty password;
    
  

    public User(int id, String name, String email, String phonenr, String password) {
        this.userID = new  SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phoneNr = new SimpleStringProperty(phonenr);
        this.password = new SimpleStringProperty(password);
    }
    //Get integerProperty for userID
    public IntegerProperty getUserIDProperty() {
        return userID;
    }
    //get UserId as int
    public int getUserIDint(){
        return userID.get();
    }
    
    //get name as StringProperty
    public StringProperty getNameProperty() {
        return name;
    }

    //get name as String
    public String getName() {
        return name.get();
    }
    
    //Set name via String
    public void setName(String newName) {
        name.set(newName);
    }
    
    //get email as StringProperty
     public StringProperty getEmailProperty() {
        return email;
    }
     //get email as String
     public String getEmail() {
        return email.get();
    }
    // set email via String 
    public void setEmail(String newEmail) {
        email.set(newEmail);
    }
    
    //get phonenr as StringProperty
    public StringProperty getPhoneNrProperty() {
        return phoneNr;
    }
    //get phonenr as String
    public String getPhoneNr() {
        return phoneNr.get();
    }
    //set phonenr via String
    public void setPhoneNr(String newPhonenr){
       phoneNr.set(newPhonenr);
    }
    
    //get password as StringProperty
    public StringProperty getPasswordProperty() {
        return password;
    }
  
    //get passwors as String
     public String getPassword() {
        return password.get();
    }
    
    public void setPassword(String newPassword){
        password.set(newPassword);
    }
    
}
