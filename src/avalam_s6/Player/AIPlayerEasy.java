/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerEasy extends AIPlayer {

    public AIPlayerEasy(String name, Color color, Owner owner) {
        super(name, color, owner);
    }
    
    /**
     * Prioritize move with high/ok value
     * ignore move with bad value unless it's impossible to not do so
     * @return 
     */
    @Override
   public Move play() {
        ArrayList<Move> mesCoups = new ArrayList<>();
        ArrayList<Move> mesCoupsHighValue = new ArrayList<>();
        ArrayList<Move> mesCoupsOkValue = new ArrayList<>();
        ArrayList<Move> mesCoupsBadValue = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        for (int i = 0; i < this.game.getGrid().getWidth(); i++)
        {
            /**
             *    1 2 3
             *    4 0 5
             *    6 7 8
             */   
            for (int j = 0; j < this.game.getGrid().getHeight(); j++)
            {
                Coordinate c0 = new Coordinate(j,i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState() == CellState.TOWER)
                {
                    Coordinate c1 = new Coordinate(j-1,i-1);
                    Coordinate c2 = new Coordinate(j,i-1);
                    Coordinate c3 = new Coordinate(j+1,i-1);
                    Coordinate c4 = new Coordinate(j-1,i);
                    Coordinate c5 = new Coordinate(j+1,i);
                    Coordinate c6 = new Coordinate(j-1,i+1);
                    Coordinate c7 = new Coordinate(j,i+1);
                    Coordinate c8 = new Coordinate(j+1,i+1);
                    tabCoord[0] = c1;
                    tabCoord[1] = c2;
                    tabCoord[2] = c3;
                    tabCoord[3] = c4;
                    tabCoord[4] = c5;
                    tabCoord[5] = c6;
                    tabCoord[6] = c7;
                    tabCoord[7] = c8;
                    for (int k = 0; k <8;k++)
                    {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER)
                        {
                            if(this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])))
                            {
                                Move m = new Move(c0,this.game.getGrid().getCellAt(c0).getSize(),tabCoord[k],this.game.getGrid().getCellAt(tabCoord[k]).getSize(),this);
                                //we gain a point for free, great !
                                if (completeTourUsVsOp(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])) || createAloneUs(c0,tabCoord[k] )){
                                    mesCoupsHighValue.add(m);
                                }
                                //we can secure a point
                                else if (completeTourUs(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k]))){
                                     mesCoupsOkValue.add(m);
                                }
                                //whatever 
                                else if(completeTourOp(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])) || createAloneOp(c0,tabCoord[k] )){
                                     mesCoupsBadValue.add(m);
                                }
                                else{
                                      mesCoups.add(m);
                                }
                            }
                        }
                    }
                }
            }
        }
        Random r = new Random();
        if (!mesCoupsHighValue.isEmpty()){
            return mesCoupsHighValue.get(r.nextInt(mesCoups.size()));
        }
        if(!mesCoupsOkValue.isEmpty()){
            return mesCoupsOkValue.get(r.nextInt(mesCoups.size()));
        }
        if(!mesCoups.isEmpty()){
                return mesCoups.get(r.nextInt(mesCoups.size()));
        }
        return mesCoupsBadValue.get(r.nextInt(mesCoups.size()));
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
     * bad value
    */
    private boolean completeTourOp(Cell a, Cell b) {
        if (this.owner != a.getOwner()) {
            if (a.getSize() + b.getSize() == 5) {
                return true;
            }
        } else {
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
            if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER) {
                if(this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k]))){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean createAloneUs(Coordinate c0, Coordinate dest)
    {
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
            this.game.getGrid().moveCell(c0, dest);
            if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER
                    && this.owner == this.game.getGrid().getCellAt(tabCoord[k]).getOwner() && alone(tabCoord[k])){
                    this.game.undo();
                    return true;
                }
            this.game.undo();
        }
        
        return false;
    }
    
     private boolean createAloneOp(Coordinate c0, Coordinate dest)
    {
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
            this.game.getGrid().moveCell(c0, dest);
            if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER
                    && this.owner != this.game.getGrid().getCellAt(tabCoord[k]).getOwner() && alone(tabCoord[k])){ 
                    this.game.undo();
                    return true;
                }
            this.game.undo();
        }
        
        return false;
    }
}
