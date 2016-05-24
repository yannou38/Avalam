/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Player.Player;

/**
 *
 * @author sazeratj
 */
public class Move {

    private final Coordinate c_src; //Coordinate
    private final Coordinate c_dst;
    private final int h_src; //Height of the tower a
    private final int h_dst;//Height of the tower b
    private final Player playedBy; //Move played by ...

    public Move(Coordinate a, int b, Coordinate c, int d, Player e) {
        this.c_src = a;
        this.c_dst = c;
        this.h_src = b;
        this.h_dst = d;
        this.playedBy = e;
    }

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

    @Override
    public String toString() {
        return (this.c_src + " " + this.h_src + " " + this.c_dst + " " + this.h_dst + " " + this.playedBy.getName());
    }

    /**
     * Getter
     *
     * @return Source Coordinate
     */
    public Coordinate getC_src() {
        return this.c_src;
    }

    /**
     * Getter
     *
     * @return Destination Coordinate
     */
    public Coordinate getC_dst() {
        return this.c_dst;
    }

    /**
     * Getter
     *
     * @return Source height
     */
    public int getH_src() {
        return this.h_src;
    }

    /**
     * Getter
     *
     * @return Destination height
     */
    public int getH_dst() {
        return this.h_dst;
    }

    /**
     * Getter
     *
     * @return Player which played the move
     */
    public Player getPlayedBy() {
        return this.playedBy;
    }

}
