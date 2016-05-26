package avalam_s6.Player;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerHard extends AIPlayer {

    //Increase the value to increase the difficulty of the AI
    private final static int BUFF = 85;
    private Coordinate[][] coord;
    private final Coordinate setInvalid;

    public AIPlayerHard(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
        setInvalid = new Coordinate(-1,-1);
        
    }
    
    @Override
    public Move play() {
        System.out.println("Je suis " + this.name + " je vais jouer des coups difficiles ");
        int coups = nbCoupsJouables();
        ArrayList<Move> mesCoups = new ArrayList<>();
        double maxvalue = -999999;
        double value;
        Coordinate[] tabCoord = new Coordinate[8];
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
                Coordinate c0 = coord[j][i];
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState().getValue() == CellState.TOWER.getValue()) {
                    //les destinations possibles (avant vérification)
                    doCoord(i,j,tabCoord);
                    for (int k = 0; k < 8; k++) {
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState().getValue() == CellState.TOWER.getValue()) {
                            if (this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0), this.game.getGrid().getCellAt(tabCoord[k]))) {
                                //un coup est possible, on l'évalue
                                Move m = new Move(c0, this.game.getGrid().getCellAt(c0).getSize(), tabCoord[k], this.game.getGrid().getCellAt(tabCoord[k]).getSize(), this);
                                //On augmente l'horizon avec l'avancement de la partie (l'ia devient de plus en plus forte)
                                //System.out.println("Ma pronfondeur actuelle est de " + (1+(BUFF / coups)));
                                value = miniMaxUs(m, 1 + (BUFF / coups));
                                //System.out.println("Je considère le coup " + c0.getX()+ " " + c0.getY() + " "+tabCoord[k].getX() + " " + tabCoord[k].getY()+ " il vaut " +value);
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
        //System.out.println("Ce coup vaut " + maxvalue);
        //System.out.println("" + mesCoups.get(monrand).getC_src().getX() + " " + mesCoups.get(monrand).getC_src().getY() + " " + mesCoups.get(monrand).getC_dst().getX() + " " + mesCoups.get(monrand).getC_dst().getY());
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

        if (createAloneUs(move.getC_src(), move.getC_dst())) {
            value += 1;
        }
        if (completeTourUsVsOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 1;
        } //we can secure a point
        else if (completeTourUs(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.9;
        }
        else if (completeTourOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst())) || createAloneOp(move.getC_src(), move.getC_dst())) {
            value =+ -1;
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

        if (createAloneUs(move.getC_src(), move.getC_dst())) {
            value += 1;
        }
        if (completeTourUsVsOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 1;
        } //we can secure a point
        else if (completeTourUs(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst()))) {
            value += 0.9;
        }
        else if (completeTourOp(this.game.getGrid().getCellAt(move.getC_src()), this.game.getGrid().getCellAt(move.getC_dst())) || createAloneOp(move.getC_src(), move.getC_dst())) {
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

        
    private boolean coordValid(int i, int j)
    {
        return !(i < 0 || j<0 || this.game.getGrid().getHeight()-1 < j || this.game.getGrid().getWidth()-1 < i);
    }
    
    private void doCoord(int i,int j, Coordinate[] tabCoord){
        Coordinate c1 = setInvalid;
        Coordinate c2 = setInvalid;
        Coordinate c3 = setInvalid;
        Coordinate c4 = setInvalid;
        Coordinate c5 = setInvalid;
        Coordinate c6 = setInvalid;
        Coordinate c7 = setInvalid;
        Coordinate c8 = setInvalid;
        
        /**
             * 1 2 3
             * 4 0 5
             * 6 7 8
             */
        if (coordValid(j-1,i-1)){
            c1 = coord[j-1][i-1];
        }
        if (coordValid(j,i-1)){
            c2 = coord[j][i-1];
        }
        if (coordValid(j+1,i-1)){
            c3 = coord[j+1][i-1];
        }
        if (coordValid(j-1,i)){
            c4 = coord[j-1][i];
        }
        if (coordValid(j+1,i)){
            c5 = coord[j+1][i];
        }
        if (coordValid(j-1,i+1)){
            c6 = coord[j-1][i+1];
        }
        if (coordValid(j,i+1)){
            c7 = coord[j][i+1];
        }
        if (coordValid(j+1,i+1)){
            c8 = coord[j+1][i+1];
        }
        tabCoord[0] = c1;
        tabCoord[1] = c2;
        tabCoord[2] = c3;
        tabCoord[3] = c4;
        tabCoord[4] = c5;
        tabCoord[5] = c6;
        tabCoord[6] = c7;
        tabCoord[7] = c8;
    }
}
