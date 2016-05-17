/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

/**
 *
 * @author ducruyy
 */
public enum WindowState {
    MAIN(0), // Homepage with access to other windows
    PLAYERSELECT(6), //New Custom Game or Load Game
    RULES(2), //Rules Page
    TUTORIAL(3), //Tutorial window
    SETTINGS(4), //Setting window
    ABOUT(5), //Credits
    BOARD(1); //Game window
    
    private final int id;
    WindowState(int id) { this.id = id; }
    public int getValue() { return id; }
    
}
