/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.Timer;
import avalam_s6.GUI.GUI_INTERFACE;

/**
 *
 * @author sazeratj
 */
public class Local_Avalam_Game implements Game_INTERFACE, ActionListener {
    private static final int NB_PLAYERS = 2;
    private final GUI_INTERFACE gui;
    private final Timer t;    
    private Grid grid;
    private Player[] players;
    private Stack<Move> history;
    private Stack<Move> cancelled_moves;
    private int current_player;
    private boolean isTurnFinished;
    private int nbTurns;
    
    public Local_Avalam_Game(Grid gr, Player p1, Player p2, GUI_INTERFACE gui) {
        this.grid = gr;
        this.players[0] = p1;
        this.players[1] = p2;
        this.history = new Stack<>();
        this.cancelled_moves = new Stack<>();
        this.current_player = 0;
        this.isTurnFinished = false;
        this.gui = gui;
        this.nbTurns = 0;
        t = new Timer(100, (ActionListener) this);
    }
    
    //TODO: Check user is able to undo (GUI check if history is empty and call or not this function)
    @Override
    public void undo() {
        this.cancelled_moves.add(this.history.pop());
        this.grid.undoMove(this.cancelled_moves.lastElement());
        if (this.history.isEmpty()) {
            this.gui.enableUndo(false);
        }
        this.gui.enableRedo(true);
    }
        
    //TODO: Check user is able to redo (GUI check if cancelled_moves is empty and call or not this function)
    @Override
    public void redo() {
        this.history.add(this.cancelled_moves.pop());
        this.grid.moveCell(this.history.lastElement().getC_src(), this.history.lastElement().getC_src());
        if (this.history.isEmpty()) {
            this.gui.enableRedo(false);            
        }
        this.gui.enableUndo(true);
    }

    @Override
    public void save(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timer getTimer() {
        return this.t;
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.players[this.current_player];
    }
    
    /**
     * Getter
     * @return history
     */
    public Stack<Move> getHistory() {
        return this.history;
    }
    
    /**
     * Getter
     * @return cancelled_moves
     */
    public Stack<Move> getCancelled_moves() {
        return this.cancelled_moves;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playATurn();
    }

    //TODO: EGALITE
    private void playATurn() {
        if(this.isTurnFinished){
            this.nbTurns++;
            this.current_player = this.nbTurns%NB_PLAYERS;
            int w = winCheck();
            switch (w) {
                case 1:
                case 2:
                case 3:
                    t.stop();
                    this.winningProcedure(w);
                    this.gui.render(); // we don't need to play after winning.
                    return;
                default:
                    this.isTurnFinished = false;
                    break;
            }
        }
        //TODO : turns logic goes here
        this.gui.render();
    }

    
    /**
     * tells if a game has been won.
     * @return 1 or 2 if player 1 or 2 won, 3 in case of a null match, 0 if game isn't finished.
     */
    private int winCheck() {
        Coordinate[] c = new Coordinate[9];
        for(int i=0;i<9;i++) {
            c[i] = new Coordinate();
        }
        int score_p1 = 0;
        
        for(int x=0;x<9;x++) {
            for(int y=0;y<9;y++) {
                c[0].setX(x);c[0].setY(y);
                c[1].setX(x-1);c[1].setY(y-1);
                c[2].setX(x-1);c[2].setY(y);
                c[3].setX(x-1);c[3].setY(y+1);
                c[4].setX(x);c[4].setY(y-1);
                c[5].setX(x);c[5].setY(y+1);
                c[6].setX(x+1);c[6].setY(y-1);
                c[7].setX(x+1);c[7].setY(y);
                c[8].setX(x+1);c[8].setY(y+1);
                for (int i=1;i<9;i++) {
                    if(c[i].isValid()) {
                        if(this.grid.canStack(this.grid.getCellAt(c[0]),this.grid.getCellAt(c[i]))) {                      
                          return 0; 
                        }
                    }                     
                }
                if(this.grid.getCellAt(c[0]).getOwner() == Pawn.PLAYER_1) {
                    score_p1++;
                } else if(this.grid.getCellAt(c[0]).getOwner() == Pawn.PLAYER_2) {
                    score_p1--;
                }
            }
        }
        if (score_p1 > 0) {
            return 1;
        } else if (score_p1 == 0) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * takes care of what happens when someone wins.
     * @param i the id of the winner (3 if null match).
     */
    private void winningProcedure(int i) {
        System.out.println("player "+ i +" won !");
    }
}