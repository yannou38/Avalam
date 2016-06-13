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
