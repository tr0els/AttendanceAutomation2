/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

/**
 *
 * @author Charlotte
 */

public class DALException extends Exception
{   /*
    * our own DALException is made here.
    */
    public DALException (String msg)
    {
    super( msg); 
    }
    
    
}