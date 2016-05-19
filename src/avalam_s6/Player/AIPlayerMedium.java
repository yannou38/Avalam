/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerMedium extends AIPlayer {

    public AIPlayerMedium(String name, Color color, Owner owner) {
        super(name, color, owner);
    }

    @Override
    public Move play() {
        System.out.println("Je suis " + this.name + " je vais jouer des coups moyens");
        ArrayList<Move> mesCoups = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        double maxvalue = 0;
        double value;
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = new Coordinate(j, i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {
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
                        //un coup est possible
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                value = miniMax(m, 1);
                                if (value > maxvalue) {
                                    maxvalue = value;
                                    mesCoups.clear();
                                    mesCoups.add(m);
                                } else if (value == maxvalue) {
                                    mesCoups.add(m);
                                }
                                //sinon on ignore, le coups est moins bon
                            }
                        }
                    }
                }
            }
        }

        Random r = new Random();
        int monrand = r.nextInt(mesCoups.size());
        System.out.println("Ce coup vaut " + maxvalue);
        System.out.println("" + mesCoups.get(monrand).getC_src().getX() + " " + mesCoups.get(monrand).getC_src().getY() + " " + mesCoups.get(monrand).getC_dst().getX() + " " + mesCoups.get(monrand).getC_dst().getY());
        return mesCoups.get(monrand);

    }

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
                        //un coup est possible
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                //we gain a point for free, great !
                                minmaxValue += 2-miniMax(m, profondeur - 1);
                                nbCoups++;
                            }
                        }
                    }
                }
            }
        }
        this.game.undo();
        return (value + (minmaxValue / nbCoups));
    }

}
