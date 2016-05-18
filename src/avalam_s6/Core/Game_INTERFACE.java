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
     * Saves the current state of the game in a given file.
     * @param filePath the path of the file in which the game will be saved.
     */
    public void save(String filePath);    
    
    /**
     * Load a game from a given file.
     * @param filePath the path of the saved game which will be loaded.
     */
    public void load(String filePath);

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
