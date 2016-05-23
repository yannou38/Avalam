/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.*;
import avalam_s6.Core.Globals.AvalamColor;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class DevourerOfNoobs extends AIPlayer {

    public DevourerOfNoobs(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
    }

    /**
     * Prioritize move with high/ok value ignore move with bad value unless it's
     * impossible to not do so
     *
     * @return
     */
    @Override
    public Move play() {
        System.out.println("Je suis " + this.name + " , we dark souls 3 now");
        ArrayList<Move> mesCoups = new ArrayList<>();
        ArrayList<Move> mesCoupsHighValue = new ArrayList<>();
        ArrayList<Move> mesCoupsOkValue = new ArrayList<>();
        ArrayList<Move> mesCoupsMehValue = new ArrayList<>();
        ArrayList<Move> mesCoupsBadValue = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        Grid g = this.game.getGrid();
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = new Coordinate(j, i);
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

                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {

                    for (int k = 0; k < 8; k++) {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                //we gain a point for free, great !
                                if (completeTourUsVsOp(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k])) || createAloneUs(c0, tabCoord[k])) {
                                    mesCoupsHighValue.add(m);
                                } //we can secure a point
                                else if (completeTourUs(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                    mesCoupsOkValue.add(m);
                                } else if (suppressAPawn(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                    mesCoupsMehValue.add(m);
                                } //whatever 
                                else if (completeTourOp(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k])) || createAloneOp(c0, tabCoord[k])) {
                                    mesCoupsBadValue.add(m);
                                } else {
                                    mesCoups.add(m);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.game.setGrid(g);
        Random r = new Random();
        if (!mesCoupsHighValue.isEmpty()) {
            System.out.println("Je joue un coup genial");
            return mesCoupsHighValue.get(r.nextInt(mesCoupsHighValue.size()));
        }
        if (!mesCoupsOkValue.isEmpty()) {
            System.out.println("Je joue un coup ok");
            return mesCoupsOkValue.get(r.nextInt(mesCoupsOkValue.size()));
        }
        if (!mesCoupsMehValue.isEmpty()) {
            System.out.println("Je joue un coup meh");
            Move meh = mesCoupsMehValue.get(r.nextInt(mesCoupsMehValue.size()));
            System.out.println(" " + meh.getC_src().getX() + " " + meh.getC_src().getY() + " " + meh.getC_dst().getX() + " " + meh.getC_dst().getY());
            return meh;
        }
        if (!mesCoups.isEmpty()) {
            System.out.println("Je joue un coup");
            return mesCoups.get(r.nextInt(mesCoups.size()));
        }
        System.out.println("Je joue un mauvais coup");
        return mesCoupsBadValue.get(r.nextInt(mesCoupsBadValue.size()));
    }

}
