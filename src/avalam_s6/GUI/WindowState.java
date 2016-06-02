/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

/**
 * Enum used to list and change panels (GUIs)
 *
 * @author Team 7
 */
public enum WindowState {

    MAIN(0), // Homepage with access to other windows
    BOARD(1), //Game window
    PLAYERSELECT(2), //New Custom Game or Load Game
    SETTINGS(3), //Setting window
    ABOUT(4), //Credits
    SAVE(5), //Save/Load window
    LOAD(6), //Save/Load window
    RULES(7), //Rules Page
    YESNO(8); // Confirm Window

    private final int id;

    /**
     * Constructor.
     *
     * @param id The id to use
     */
    WindowState(int id) {
        this.id = id;
    }

    /**
     * Return the value of the selected element.
     *
     * @return The value of the Selected element
     */
    public int getValue() {
        return this.id;
    }

}
