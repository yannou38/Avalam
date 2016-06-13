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
package avalam_s6.Core;

/**
 * Define the state of the cell (tower, empty, restricted or full).
 *
 * @author Team 7
 */
public enum CellState {
    TOWER(2), // Tower 1-4
    EMPTY(1), // Empty Cell
    RESTRICTED(0), // Game Board
    FULL(3); // Tower 5

    private final int value;

    /**
     * Constructor.
     * @param v the value of the cell's state.
     */
    private CellState(int v) {
        this.value = v;
    }

    /**
     * Cell state value getter.
     * @return the value of the cell's state.
     */
    public int getValue() {
        return this.value;
    }
}
