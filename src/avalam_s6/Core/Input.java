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
    private static Game_INTERFACE game;
    private static boolean mouseClicked = false;	
    private static boolean hasClicked = false;
    private static Coordinate c_src = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private static Coordinate c_dest = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);	
    private static int src_size = -1;
    private static int dest_size = -1;
	
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

    public static void resetClick() {
        Input.hasClicked = false;
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
     * Get the height of the source tower.
     * @return the size of the source tower.
     */
    public static int getSrcSize() {
        return Input.src_size;
    }

    /**
     * Set the position of the click.
     * @param pos the position of the click.
     */
    public static void updateMouseSrcPosition(Coordinate pos)
    {
        Input.c_src = pos;
        if(pos.isValid())
            Input.src_size = Input.game.getGrid().getCellAt(pos).getSize();
        else
            Input.src_size = -1;
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
     * Get the height of the destination tower.
     * @return the size of the destination tower.
     */
    public static int getDestSize(){
        return Input.dest_size;
    }

    /**
     * Set the position of the release.
     * @param pos the position of the release.
     */
    public static void updateMouseDestPosition(Coordinate pos)
    {
        Input.c_dest = pos;
        if(pos.isValid())
            Input.dest_size = Input.game.getGrid().getCellAt(pos).getSize();
        else
            Input.dest_size = -1;
    }
    
    public static void setInputGame(Game_INTERFACE g){
        Input.game = g;
    }

}
