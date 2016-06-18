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
package avalam.Core;

/**
 * Define the owner of a cell/pawn.
 *
 * @author Team 7
 */
public enum Owner {
    PLAYER_1(1), //P1
    PLAYER_2(2), //P2
    NO_OWNER(3); //No Owner

    private final int value;

    /**
     * Constructor.
     * @param v the value of the owner.
     */
    private Owner(int v) {
        this.value = v;
    }

    /**
     * Gets the owner's value (player 1 = 1, player 2 = 2, no owner = 3).
     * @return the owner's value.
     */
    public int getValue() {
        return this.value;
    }
}
