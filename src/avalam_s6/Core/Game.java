/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.GUI.GUI;
import avalam_s6.Player.Player;
import javax.swing.Timer;

/**
 *
 * @author TheDoctor
 */
public interface Game {        
    
    /**
     * cancels the last move. You can cancel plural times.
     */
    public void cancel();
    
    /**
     * redo the last canceled move. You can redo plural times, but not if you played a new move after a cancel.
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
    public Timer getT();
    
    /**
     * Get the game's grid.
     * @return the game's grid.
     */
    public Grid getGrid();
    
    /**
     * Get the current Player.
     * @return the current player.
     */
    public Player getCurrentPlayer();
}
