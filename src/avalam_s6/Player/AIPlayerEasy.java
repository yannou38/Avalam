/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.*;
import java.awt.Color;

/**
 *
 * @author Seawolf
 */
public class AIPlayerEasy extends AIPlayer {

    public AIPlayerEasy(String name, Color color, Owner owner) {
        super(name, color, owner);
    }

    @Override
    public Move play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Max value
     *
     * @param a origin
     * @param b target
     * @return true if the move a on b would give us a 5 tour, b is opponent
     */
    private boolean completeTourUsVsOp(Cell a, Cell b) {
        if (this.owner == a.getOwner()) {
            if (this.owner != b.getOwner()) {
                if (a.getSize() + b.getSize() == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ok value
     *
     * @param a origin
     * @param b target
     * @return true if the move a on b would give us a 5 tour
     */
    private boolean completeTourUs(Cell a, Cell b) {
        if (this.owner == a.getOwner()) {
            if (a.getSize() + b.getSize() == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Max value, we created a tower
     * @param c0
     * @return true if the coordinate is alone (no move possible) else false
     */
    private boolean alone(Coordinate c0) {
        Coordinate[] tabCoord = new Coordinate[8];
        int i = c0.getY();
        int j = c0.getX();
        /**
         * 1 2 3
         * 4 0 5
         * 6 7 8
         */

        Coordinate c1 = new Coordinate(j - 1, i - 1);
        Coordinate c2 = new Coordinate(j, i - 1);
        Coordinate c3 = new Coordinate(j + 1, i - 1);
        Coordinate c4 = new Coordinate(j - 1, i);
        Coordinate c5 = new Coordinate(j + 1, i);
        Coordinate c6 = new Coordinate(j - 1, i + 1);
        Coordinate c7 = new Coordinate(j, i + 1);
        Coordinate c8 = new Coordinate(j + 1, i + 1);
        tabCoord[0] = c1;
        tabCoord[1] = c2;
        tabCoord[2] = c3;
        tabCoord[3] = c4;
        tabCoord[4] = c5;
        tabCoord[5] = c6;
        tabCoord[6] = c7;
        tabCoord[7] = c8;
        for (int k = 0; k < 8; k++) {
            if (tabCoord[k].isValid() && grid.getCellAt(tabCoord[k]).getState() == State.TOWER) {
                if(grid.canStack(grid.getCellAt(c0),grid.getCellAt(tabCoord[k]))){
                    return false;
                }
            }
        }
        return true;
    }

}
