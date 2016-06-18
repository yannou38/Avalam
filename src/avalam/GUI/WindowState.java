/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam.GUI;

/**
 * Enum used to list and change panels (GUIs)
 *
 * @author Team 7
 */
public enum WindowState {

    MAIN(0,"Home"), // Homepage with access to other windows
    BOARD(1, "Game in progress"), //Game window
    PLAYERSELECT(2,"Creating a custom game"), //New Custom Game or Load Game
    SETTINGS(3,"Settings"), //Setting window
    ABOUT(4,"Credits"), //Credits
    SAVE(5,"Save your game"), //Save/Load window
    LOAD(6,"Load a game"), //Save/Load window
    RULES(7,"Rules"), //Rules Page
    YESNO(8,"Confirm"); // Confirm Window

    private final int id;
    private final String name;

    /**
     * Constructor.
     *
     * @param id The id to use
     */
    WindowState(int id,String name) {
        this.id = id;
        this.name =name;
    }

    /**
     * Return the value of the selected element.
     *
     * @return The value of the Selected element
     */
    public int getValue() {
        return this.id;
    }
    
    /**
     * Return the name of the selected element.
     * 
     * @return The name of the selected element
     */
    public String getName() {
        return this.name;
    }
    
    

}
