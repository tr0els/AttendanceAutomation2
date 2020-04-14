/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPakke;

import attendanceautomation.BE.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Charlotte
 */
public class TestStudent {
    
    public TestStudent() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
 
    @Test
    public void testgetname(){
    Student stud = new Student("Stud@gmail.com", "1234");
    stud.setName("gurli hansen");
    
    assertEquals("gurli hansen", stud.getName());
    
    }
    
    
    @Test
    public void testgetemail(){
    Student stud = new Student("Stud@gmail.com", "1234");
   
    
    assertEquals("Stud@gmail.com", stud.getEmail());
    
    }
    
    
    @Test
    public void testgetPassword(){
    Student stud = new Student("Stud@gmail.com", "1234");
  
    
    assertEquals("1234", stud.getPassword());
    
    }
    
    
    
}

