/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Player.Player;

/**
 * The moves' system.
 * @author Team 7
 */
public class Move {

    private final Coordinate c_src; //Coordinate
    private final Coordinate c_dst;
    private final int h_src; //Height of the tower a
    private final int h_dst;//Height of the tower b
    private final Player playedBy; //Move played by ...

    /**
     * Constructor.
     * @param a the source's coordinate.
     * @param b the source's tower height.
     * @param c the destination's coordinate.
     * @param d the destination's height.
     * @param e the player who plays the move.
     */
    public Move(Coordinate a, int b, Coordinate c, int d, Player e) {
        this.c_src = a;
        this.c_dst = c;
        this.h_src = b;
        this.h_dst = d;
        this.playedBy = e;
    }

    /**
     * Tells if the move is valid.
     * @param grid the grid on which the move is applied.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValid(Grid grid) {
        if (this.c_src.isValid() && this.c_dst.isValid()) {
            if ((Math.abs(this.c_src.getX() - this.c_dst.getX()) <= 1) && (Math.abs(this.c_src.getY() - this.c_dst.getY()) <= 1)) { //adjacence d'une case
                return (grid.canStack(grid.getCellAt(this.c_src), grid.getCellAt(this.c_dst)));
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Source's coordinate getter.
     *
     * @return the source's coordinate.
     */
    public Coordinate getC_src() {
        return this.c_src;
    }

    /**
     * Destination's coordinate getter.
     *
     * @return the destination's coordinate.
     */
    public Coordinate getC_dst() {
        return this.c_dst;
    }

    /**
     * Source's height getter.
     *
     * @return the source's tower height.
     */
    public int getH_src() {
        return this.h_src;
    }

    /**
     * Destination's height getter.
     *
     * @return the destination's tower height.
     */
    public int getH_dst() {
        return this.h_dst;
    }

    /**
     * Player getter.
     *
     * @return the player who played the move.
     */
    public Player getPlayedBy() {
        return this.playedBy;
    }

    @Override
    public String toString() {
        return (this.c_src + " " + this.h_src + " " + this.c_dst + " " + this.h_dst + " | " + this.playedBy.getName());
    }
}
