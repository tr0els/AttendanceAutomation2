/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import attendanceautomation.BE.Student;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zilot
 */
public class NewEmptyJUnitTest
{
    
   
    
    
    @Test
    public void testgetname(){
    Student stud = new Student("Stud@gmail.com", "1234");
    stud.setName("gurli hansen");
    
    assertEquals("gurli hansen", stud.getName());
    
    }
    
}
