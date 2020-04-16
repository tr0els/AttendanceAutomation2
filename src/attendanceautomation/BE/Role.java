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
public interface Role
{

    String Role();
}

class IsTeacher implements Role
{

    /**
     * Sætter Role() til at sende Teacher tilbage
     *
     * @return
     */
    public String Role()
    {
        return "Teacher";
    }
}

class IsStudent implements Role
{

    /**
     * Sætter Role() til at sende Student tilbage
     *
     * @return
     */
    public String Role()
    {
        return "Student";
    }
}
