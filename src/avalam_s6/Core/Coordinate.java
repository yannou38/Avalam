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
 * The Coordinate system. X is for columns, Y is for lines.
 * @author Team 7
 */
public class Coordinate {

    private int x;
    private int y;

    /**
     * Default constructor. Value is (-1;-1).
     */
    public Coordinate() {
        this(-1, -1);
    }

    /**
     * Constructor.
     * @param x the x value of the Coordinate (column).
     * @param y the y value of the Coordinate (line).
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }

    /**
     * Getter x.
     *
     * @return the x value of the Coordinate (column).
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter y.
     *
     * @return the y value of the Coordinate (line).
     */
    public int getY() {
        return this.y;
    }

    /**
     * Compares two Coordinates.
     *
     * @param r the coordinate to compare with.
     * @return true if current Coordinate is equal to r, false otherwise.
     */
    public boolean equals(Coordinate r) {
        return (this.x == r.getX() && this.y == r.getY());
    }

    /**
     * Test if the coordinate is valid on the grid.
     *
     * @return true if the coordinate is valid, false otherwise.
     */
    public boolean isValid() {
        return !(this.x < 0 || this.y < 0 || this.x > 8 || this.y > 8);
    }

    /**
     * Setter x.
     *
     * @param x the new x value of the Coordinate (column).
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter y.
     *
     * @param y the new y value of the Coordinate (line).
     */
    public void setY(int y) {
        this.y = y;
    }
}
