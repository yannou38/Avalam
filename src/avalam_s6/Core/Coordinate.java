/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
