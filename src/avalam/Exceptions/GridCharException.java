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
package avalam.Exceptions;

/**
 * Exception - Grid contains an illegal character
 * @author Team 7
 */
public class GridCharException extends Exception {

    /**
     * Constructor
     * @param c Name of the invalid character
     * @param pos Position of the invalid character
     */
    public GridCharException(char c, int pos) {
        System.err.println("Grid contains an illegal character: " + c + " [" + pos + "]");
    }
}
