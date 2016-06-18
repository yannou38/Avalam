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
 * Exception - Grid is oversized or undersized.
 * Grid must be 9*9.
 * @author sazeratj
 */
public class GridSizeException extends Exception {

    public GridSizeException() {
        System.err.println("Error: Grid is undersized or oversized.");
        System.err.println("       Please, check that your grid contain exactly 81 characters (Read the Read_Me.txt file)");
    }
}
