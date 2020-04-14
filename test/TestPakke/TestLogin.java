/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPakke;

import  attendanceautomation.GUI.Model.AttendanceAutomationModel;
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
public class TestLogin {
    
  
    
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
    
    //teste at vi får false når vi ikke ligger en korrekt email og password ind
    @Test
    public void TestLoginFalse(){
    
        AttendanceAutomationModel INSTANCE = AttendanceAutomationModel.getInstance();
        
        
        boolean Can_i_get_in = INSTANCE.loginModel("Student@easv.dk", "1234");
         
        //assertTrue(Can_i_get_in);
        assertFalse(Can_i_get_in);
        
    
    } 
    
    
        @Test
    public void TestLoginTrue(){
    
        AttendanceAutomationModel INSTANCE = AttendanceAutomationModel.getInstance();
        
        
        
        boolean Can_i_get_in = INSTANCE.loginModel("student@email.com", "1234");
         
        //assertTrue(Can_i_get_in);
        assertTrue(Can_i_get_in);
        
    
    } 
    
}
