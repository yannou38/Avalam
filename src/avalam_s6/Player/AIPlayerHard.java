package avalam_s6.Player;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.util.ArrayList;
import java.util.Random;

/**
 * hard AI
 * @author Team 7
 */
public class AIPlayerHard extends AIPlayer {

    //Increase the value to increase the difficulty of the AI
    private final static int BUFF = 105;

    public AIPlayerHard(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
        
    }
    
    /**
     * does simple moves at first, then start to do a minmax when the number of moves is small enough
     * possible to beat early
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
                                //System.out.println("Ma pronfondeur actuelle est de " + (1+(BUFF / coups)));
                                value = miniMaxUs(m, 1 + (BUFF / coups));
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
    private double miniMaxUs(Move move, int profondeur) {

        Coordinate[] tabCoord = new Coordinate[8];
        double minValue = 9999;
        boolean coupjoue = false;
        double value = 0;
        double minmaxValue = 0;
        
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
                                minmaxValue = miniMaxOp(m, profondeur - 1);
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
        private double miniMaxOp(Move move, int profondeur) {

        Coordinate[] tabCoord = new Coordinate[8];
        double maxValue = -9999;
        double value = 0;
        double minmaxValue = 0;
        boolean coupjoue = false;

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
                                minmaxValue = miniMaxUs(m, profondeur - 1);
                                //on récupère le max
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
}
