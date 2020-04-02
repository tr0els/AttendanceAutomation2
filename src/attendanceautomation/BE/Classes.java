/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

/**
 *
 * @author René Jørgensen
 */
public class Classes
{

    private int id;
    private String className;

    public Classes(int id, String className)
    {
        this.id = id;
        this.className = className;
    }

    public Classes()
    {
        
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    
    public int getId()
    {
        return id;
    }

    public String getClassName()
    {
        return className;
    }

    @Override
    public String toString()
    {
        return className;
    }
    
    
}
