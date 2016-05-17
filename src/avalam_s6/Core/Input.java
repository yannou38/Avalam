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
    private static boolean hasClicked = false;
    private static Coordinate c_src = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private static Coordinate c_dest = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);	
	
    /**
     * Tells if button has been clicked.
     * @return true if button has been clicked.
     */
    public static boolean isButtonClicked()
    {
        return Input.mouseClicked;
    }
    
    /**
     * Tells if button has clicked AND released.
     * @return true if button has been clicked and released.
     */
    public static boolean hasClicked(){
        return Input.hasClicked;
    }

    /**
     * Tells the Input class that the button has been clicked.
     */
    public static void setButtonClicked(){
        Input.mouseClicked = true;
    }
    
    /**
     * Tells the Input class that the button has been released.
     */
    public static void setButtonReleased(){        
        Input.mouseClicked = false;
        Input.hasClicked = true;
    }    

    /**
     * Get the position of the click.
     * @return the position of the click.
     */
    public static Coordinate getMouseSrcPosition()
    {
        return Input.c_src;
    }

    /**
     * Set the position of the click.
     * @param pos the position of the click.
     */
    public static void updateMouseSrcPosition(Coordinate pos)
    {
        Input.c_src = pos;
    }
        
    /**
     * Get the position of the release.
     * @return the position of the release.
     */
    public static Coordinate getMouseDestPosition()
    {
        return Input.c_dest;
    }

    /**
     * Set the position of the release.
     * @param pos the position of the release.
     */
    public static void updateMouseDestPosition(Coordinate pos)
    {
        Input.c_dest = pos;
    }
}
