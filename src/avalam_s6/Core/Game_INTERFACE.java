/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Player.Player;
import avalam_s6.Player.Player_INTERFACE;
import javax.swing.Timer;

/**
 *
 * @author TheDoctor
 */
public interface Game_INTERFACE {        
    
    /**
     * Undo the last move. You can undo plural times.
     */
    public void undo();
    
    /**
     * Redo the last canceled move. You can redo plural times, but not if you played a new move after a cancel.
     */
    public void redo();

    /**
     * Get the game's timer.
     * @return the game's timer.
     */
    public Timer getTimer();
    
    /**
     * Get the game's grid.
     * @return the game's grid.
     */
    public Grid getGrid();
    public void setGrid(Grid g);
    
    /**
     * Get the current Player.
     * @return the current player.
     */
    public Player_INTERFACE getCurrentPlayer();
    
    public void addMoveToHistory(Move m);
}
