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
    private final int h_src; //Height
    private final int h_dst;
    private final Player playedBy; //Move played by ...
    
    public Move(Coordinate a, int b, Coordinate c, int d, Player e) {
        this.c_src = a;
        this.c_dst = c;
        this.h_src = b;
        this.h_dst = d;
        this.playedBy = e;
    }

    /**
     * Getter
     * @return Source Coordinate
     */
    public Coordinate getC_src() {
        return this.c_src;
    }
    
    /**
     * Getter
     * @return Destination Coordinate 
     */
    public Coordinate getC_dst() {
        return this.c_dst;
    }
    
    /**
     * Getter
     * @return Source height 
     */
    public int getH_src() {
        return this.h_src;
    }
    
    /**
     * Getter
     * @return Destination height
     */
    public int getH_dst() {
        return this.h_dst;
    }
    
    /**
     * Getter
     * @return Player which played the move 
     */
    public Player getPlayedBy() {
        return this.playedBy;
    }
    
    
}
