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
package avalam.Player;

import avalam.Core.CellState;
import avalam.Core.Coordinate;
import avalam.Core.Globals.AvalamColor;
import avalam.Core.Move;
import avalam.Core.Owner;
import java.util.ArrayList;
import java.util.Random;

/**
 * medium AI
 * @author Team 7
 */
public class AIPlayerMedium extends AIPlayer {

    private int nbtours;

    public AIPlayerMedium(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
    }
    
     /**
     * calculate the average value of upcoming moves
     * @return the best play in an optimistict PoV
     */
    @Override
    public Move play() {
        ArrayList<Move> mesCoups = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        double maxvalue = -9999;
        double value;
         coord = new Coordinate[this.game.getGrid().getHeight()][this.game.getGrid().getWidth()];
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                coord[j][i] = new Coordinate(j,i);
            }
        }
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = new Coordinate(j, i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {
                    doCoord(i,j,tabCoord);
                    for (int k = 0; k < 8; k++) {
                        //un coup est possible
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                value = miniMax(m, 1 + (nbtours / 3));
                                if (value > maxvalue) {
                                    maxvalue = value;
                                    mesCoups.clear();
                                    mesCoups.add(m);
                                } else if (value == maxvalue) {
                                    mesCoups.add(m);
                                }
                                else{
                                }
                                //sinon on ignore, le coups est moins bon
                            }
                        }
                    }
                }
            }
        }
        if(mesCoups.isEmpty())
            return null;
        Random r = new Random();
        int monrand = r.nextInt(mesCoups.size());
        return mesCoups.get(monrand);

    }

    /**
     * give the most probable value of the move (optimistic, let the opponent
     * outvalue us if he plays well)
     *
     * @param move move
     * @param profondeur horizon
     * @return average value of the move
     */
    private double miniMax(Move move, int profondeur) {

        Coordinate[] tabCoord = new Coordinate[8];
        double value = 0;
        double minmaxValue = 0;
        double nbCoups = 0;

        if (completeTourUsVsOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst())) || createAloneUs(move.getC_src(), move.getC_dst())) {
            value = 1;
        } //we can secure a point
        else if (completeTourUs(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value = 0.75;
        } else if (suppressAPawn(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value = 0.50;
        } //whatever 
        else if (completeTourOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst())) || createAloneOp(move.getC_src(), move.getC_dst())) {
            value = 0;
            return value;
        } else {
            value = 0.25;
        }
        if (profondeur == 0) {
            return value;
        }
        this.game.getGrid().moveCell(move.getC_src(), move.getC_dst());
        this.game.addMoveToHistory(move);

        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = new Coordinate(j, i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {
                    doCoord(i,j,tabCoord);
                    for (int k = 0; k < 8; k++) {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                //un coup est possible
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                minmaxValue += 3 - miniMax(m, profondeur - 1);
                                nbCoups++;
                            }
                        }
                    }
                }
            }
        }
        if (nbCoups == 0){
            nbCoups = 1;
        }
            
        this.game.undo();
        return (value + 2 * (minmaxValue / nbCoups));
    }

   

}
