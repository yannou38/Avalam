/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 *
 * @author TheDoctor
 */

public class Input 
{
    private static boolean mouseClicked = false;	
    private static boolean hasClicked = true;
    private static Coordinate c_src = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private static Coordinate c_dest = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);	
	
    public static boolean isButtonClicked()
    {
        return Input.mouseClicked;
    }
    
    public static boolean hasClicked(){
        return Input.hasClicked;
    }

    public static void setButtonClicked(){
        Input.mouseClicked = true;
    }
    
    public static void setButtonReleased(){        
        Input.mouseClicked = false;
        Input.hasClicked = true;
    }    

    public static Coordinate getMouseSrcPosition()
    {
        return Input.c_src;
    }

    public static void updateMouseSrcPosition(Coordinate pos)
    {
        Input.c_src = pos;
    }
        
    public static Coordinate getMouseDestPosition()
    {
        return Input.c_dest;
    }

    public static void updateMouseDestPosition(Coordinate pos)
    {
        Input.c_dest = pos;
    }
}
