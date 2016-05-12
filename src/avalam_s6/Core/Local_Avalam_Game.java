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
    private Grid grid;
    private Player[] players;
    private Stack<Move> history;
    private Stack<Move> cancelled_moves;
    private int current_player;
    private boolean isTurnFinished;
    private Timer t;
    private GUI_INTERFACE gui;
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
    }
    
    //TODO: Check user is able to redo (GUI check if cancelled_moves is empty and call or not this function)
    @Override
    public void redo() {
        this.history.add(this.cancelled_moves.pop());
        this.grid.moveCell(this.history.lastElement().getC_src(), this.history.lastElement().getC_src());
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
     * @return cqncelled_moves
     */
    public Stack<Move> getCancelled_moves() {
        return this.cancelled_moves;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playATurn();
    }

    private void playATurn() {
        if(this.isTurnFinished){
            this.nbTurns++;
            this.current_player = this.nbTurns%NB_PLAYERS;
            switch (winCheck()) {
                case 1:
                    this.winningProcedure(this.nbTurns%NB_PLAYERS);
                    t.stop();
                    break;
                case 2:
                    this.winningProcedure((this.nbTurns+1)%NB_PLAYERS);
                    t.stop();
                    break;
                default:
                    this.isTurnFinished = false;
                    break;
            }
        } else {
            
            this.gui.render();
        }
    }

    private int winCheck() {
        return 0;
    }

    private void winningProcedure(int i) {
        System.out.println("player "+ i +" won !");
    }
    
    
    
    
    
}
