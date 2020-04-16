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
public class Classes
{

    private int id;
    private String className;

    /**
     * Constructor for Classes som tager imod id og classname
     *
     * @param id
     * @param className
     */
    public Classes(int id, String className)
    {
        this.id = id;
        this.className = className;
    }

    /**
     * Constructor for Classes
     */
    public Classes()
    {

    }

    /**
     * Sætter id på Classes
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Returnerer id'et for Classes
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sætter navnet på Classes
     *
     * @param className
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * Returnerer navet for Classes
     *
     * @return
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * Definerer en toString metode til at tilgå Classes
     *
     * @return
     */
    @Override
    public String toString()
    {
        return className;
    }
}
