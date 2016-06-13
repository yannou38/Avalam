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
package avalam_s6.Core.Globals;

/**
 * Color Enumeration used by the Avalam Game.
 * We didn't use standards colors here because it do not provide any getter by the name.
 * To save a player's color it requires the color name and the Default Java Colors do not allow it :)
 * @author Team 7
 */
public enum AvalamColor {
    WHITE("white"),
    BLACK("black"),
    BLUE("blue"),
    YELLOW("yellow"),
    RED("red"),
    GREEN("green"),
    PURPLE("purple"),
    CYAN("cyan");

    private final String value;

    /**
     * Constructor
     * @param v Value of the enumerate
     */
    private AvalamColor(String v) {
        this.value = v;
    }

    /**
     * Getter
     * @return The value of the enumerate 
     */
    public String getValue() {
        return this.value;
    }
}
