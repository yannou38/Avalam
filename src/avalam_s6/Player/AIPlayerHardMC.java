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
package avalam_s6.Player;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Random;

/**
 * AI HardAB, with MonteCarlo during the start of the game to increase the odds to do a good move while limiting exec time
 * @author Team 7
 */
public class AIPlayerHardMC extends AIPlayer {

    //Increase the value to increase the difficulty of the AI
    private final static int BUFF = 160;

    public AIPlayerHardMC(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
        
    }
    
    /**
     * does a montecarlo at first unless a very good move can be spotted, then start to do a minmax when the number of moves is small enough
     * @return the choosen move
     */
    @Override
    public Move play() {
        coord = new Coordinate[this.game.getGrid().getHeight()][this.game.getGrid().getWidth()];
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                coord[j][i] = new Coordinate(j,i);
            }
        }
        int coups = nbCoupsJouables();
        int profondeur = 1 + (BUFF / coups);
        ArrayList<Move> mesCoups = new ArrayList<>();
        double maxvalue = -999999;
        double value;
        Coordinate[] tabCoord = new Coordinate[8];
        
        
        
        
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = coord[j][i];
                doCoord(i,j,tabCoord);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {
                    //les destinations possibles (avant vérification)
                    for (int k = 0; k < 8; k++) {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                //un coup est possible, on l'évalue
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                //On augmente l'horizon avec l'avancement de la partie (l'ia devient de plus en plus forte)
                                value = miniMaxUs(m, profondeur,-99999,99999);
                                if(profondeur <2){
                                    if (value <0.5)
                                        return monteCarlo();
                                }
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
        if(mesCoups.isEmpty())
            return null;
        Random r = new Random();
        int monrand = r.nextInt(mesCoups.size());
        return mesCoups.get(monrand);

    }
    
    /**
     *
     * @param move expected move
     * @param profondeur horizon
     * @return min value (we expect the Op to do the best move)
     */
    private double miniMaxUs(Move move, int profondeur, double alpha, double beta) {

        Coordinate[] tabCoord = new Coordinate[8];
        double minValue = 9999;
        boolean coupjoue = false;
        double value = 0;
        double minmaxValue = 0;
        double val = 9999;
        
        value += 1*nbCreateAloneUsV2(move.getC_src(), move.getC_dst());
        value -= 1*nbCreateAloneOpV2(move.getC_src(), move.getC_dst());

        if (completeTourUsVsOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 1;
        } //we can secure a point
        else if (completeTourUs(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.9;
        }
        else if (completeTourOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += -1;
        }
        else if (suppressAPawnCreate3Op(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.25;

        } else if (suppressAPawn(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.45;
        } //whatever 
         else {
            value += 0.05;
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
                                coupjoue = true;
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                minmaxValue = miniMaxOp(m, profondeur - 1,alpha,beta);
                                val = min(val,minmaxValue);
                                if(alpha >= val)
                                {
                                    this.game.undo();
                                    return(value+val);
                                }
                                beta = min(beta,val);
                                //on récupère le min
                                if (minmaxValue < minValue) {
                                    minValue = minmaxValue;
                                }
                                
                            }
                        }
                    }
                }
            }
        }
        this.game.undo();
        if (!coupjoue)
            minValue = 0;
        return (value + minValue);
    }
    
    /**
     *
     * @param move expected move
     * @param profondeur horizon
     * @return max value (we do the best move)
     */
        private double miniMaxOp(Move move, int profondeur, double alpha, double beta) {

        Coordinate[] tabCoord = new Coordinate[8];
        double maxValue = -9999;
        double value = 0;
        double minmaxValue = 0;
        boolean coupjoue = false;
        double val = -9999;

        value += 1*nbCreateAloneUsV2(move.getC_src(), move.getC_dst());
        value -= 1*nbCreateAloneOpV2(move.getC_src(), move.getC_dst());
        
        if (completeTourUsVsOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 1;
        } //we can secure a point
        else if (completeTourUs(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.9;
        }
        else if (completeTourOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += -1;
        }
        else if (suppressAPawnCreate3Op(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.25;

        } else if (suppressAPawn(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.45;
        } //whatever 
         else {
            value += 0.1;
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
                                coupjoue = true;
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                minmaxValue = miniMaxUs(m, profondeur - 1,alpha,beta);
                                //on récupère le max
                                val = max(val,minmaxValue);
                                if (val >= beta){
                                    this.game.undo();
                                    return (value+val);
                                }
                                alpha = max(alpha,val);
                                if (minmaxValue > maxValue) {
                                    maxValue = minmaxValue;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!coupjoue)
            maxValue = 0;
        this.game.undo();
        return (value + maxValue);
    }
        
        /**
         * basic MonteCarlo algorithm
         * @return best move given we play a random game
         */
        private Move monteCarlo()
        {
            //jouer des parties random, retenir le premier coup
            //faire la moyenne des résultats pour chaque coups
            //renvoyer le coup le meilleur résultat
            int [][][][] tab = new int[9][9][9][9];
            for (int sx = 0; sx < 9; sx++){
                for (int sy = 0; sy < 9; sy++){
                    for (int dx = 0; dx < 9; dx++){
                        for (int dy = 0; dy < 9; dy++){
                            tab[sx][sy][dx][dy] = -10000;
                        }
                    }
                }
            }
            ArrayList<Move> mesCoups = new ArrayList<>();
            Move m = null;
            Move mInit = null;
            int compteur = 0;
            for(int k = 0; k<25000;k++)
            {
                if(winCheck() == 1337){
                    mInit = coupRandom();
                    this.game.getGrid().moveCell(mInit.getC_src(), mInit.getC_dst());
                    this.game.addMoveToHistory(mInit);
                    compteur++;
                }
                while(winCheck() == 1337)
                {
                    m = coupRandom();
                    this.game.getGrid().moveCell(m.getC_src(), m.getC_dst());
                    this.game.addMoveToHistory(m);
                    compteur++;
                }
                if (compteur == 0)
                    return null;
                if (tab[mInit.getC_src().getY()][mInit.getC_src().getX()][(mInit.getC_dst().getY())][(mInit.getC_dst().getX())] == -10000)
                    tab[mInit.getC_src().getY()][mInit.getC_src().getX()][(mInit.getC_dst().getY())][(mInit.getC_dst().getX())] = 0;
                tab[mInit.getC_src().getY()][mInit.getC_src().getX()][(mInit.getC_dst().getY())][(mInit.getC_dst().getX())] += winCheck();
                while(compteur != 0)
                {
                    this.game.undo();
                    compteur--;
                }
            }
            int max = -9999;
            
            for (int sx = 0; sx < 9; sx++){
                for (int sy = 0; sy < 9; sy++){
                    for (int dx = 0; dx < 9; dx++){
                        for (int dy = 0; dy < 9; dy++){
                            if(tab[sx][sy][dx][dy] > max){
                                max = tab[sx][sy][dx][dy];
                                mesCoups.clear();
                                Coordinate c0 = new Coordinate(sy,sx);
                                Coordinate c1 = new Coordinate(dy,dx);
                                m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), c1, this.game.getGrid().getCellAt(c1).getSize(), this);
                                mesCoups.add(m);
                            }
                            else if(tab[sx][sy][dx][dy] ==  max){
                                Coordinate c0 = new Coordinate(sy,sx);
                                Coordinate c1 = new Coordinate(dy,dx);
                                m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), c1, this.game.getGrid().getCellAt(c1).getSize(), this);
                                mesCoups.add(m);
                            }
                                
                        }
                    }
                }
            }
            Random r = new Random();
            int monrand = r.nextInt(mesCoups.size());return mesCoups.get(monrand);
        }
        
        /**
         * random move
         * @return a random move
         */
        private Move coupRandom(){
        ArrayList<Move> mesCoups = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        for (int i = 0; i < this.game.getGrid().getWidth(); i++) {
            /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
            for (int j = 0; j < this.game.getGrid().getHeight(); j++) {
                Coordinate c0 = new Coordinate(j, i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState() == CellState.TOWER) {
                    doCoord(i,j,tabCoord);
                    for (int k = 0; k < 8; k++) {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                mesCoups.add(m);
                            }
                        }
                    }
                }
            }
        }
        Random r = new Random();
        return mesCoups.get(r.nextInt(mesCoups.size()));
        }
        
        private int winCheck() {
        Coordinate[] c = new Coordinate[9];
        for (int i = 0; i < 9; i++) {
            c[i] = new Coordinate();
        }
        int score_p1 = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                c[0].setX(x);
                c[0].setY(y);
                c[1].setX(x - 1);
                c[1].setY(y - 1);
                c[2].setX(x - 1);
                c[2].setY(y);
                c[3].setX(x - 1);
                c[3].setY(y + 1);
                c[4].setX(x);
                c[4].setY(y - 1);
                c[5].setX(x);
                c[5].setY(y + 1);
                c[6].setX(x + 1);
                c[6].setY(y - 1);
                c[7].setX(x + 1);
                c[7].setY(y);
                c[8].setX(x + 1);
                c[8].setY(y + 1);
                for (int i = 1; i < 9; i++) {
                    if (this.game.getGrid().getCellAt(c[0]).getState().getValue() != CellState.RESTRICTED.getValue() || this.game.getGrid().getCellAt(c[0]).getState().getValue() != CellState.EMPTY.getValue()) {
                        if (c[i].isValid() && this.game.getGrid().getCellAt(c[i]).getState().getValue() != CellState.RESTRICTED.getValue() && this.game.getGrid().getCellAt(c[i]).getState().getValue() != CellState.EMPTY.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c[0]), this.game.getGrid().getCellAt(c[i]))) {
//                                System.out.println("x = "+ x+ ", y = "+y+", c[0] = "+ c[0]+", cell = "+this.grid.getCellAt(c[0]).getState().getValue()+"c["+i+"] = "+c[i]+", cell = "+this.grid.getCellAt(c[i]).getState().getValue()+".");
                                return 1337;
                            }
                        }
                    }
                }
                if (this.game.getGrid().getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                    score_p1++;
                } else if (this.game.getGrid().getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                    score_p1--;
                }
                
            }
        }
        if(this.owner.getValue() == Owner.PLAYER_2.getValue())
            score_p1 = -score_p1;
        if (score_p1 > 0) {
            return 1;
        } else if (score_p1 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
