/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Player.Player_INTERFACE;
import javax.swing.Timer;

/**
 * The Game interface. Acts as the Model abstraction in the MVC system.
 * @author Team 7
 */
public interface Game_INTERFACE {

    /**
     * Undo the last move from the history.
     */
    public void undo();

    /**
     * Redo the last canceled move. You can redo plural times, but not if you
     * played a new move after a cancel.
     */
    public void redo();

    /**
     * Add a move to the history so it can be canceled if necessary.
     * @param m the Move to add to the moves' history.
     */
    public void addMoveToHistory(Move m);

    /**
     * Gets the game's timer.
     *
     * @return the game's timer.
     */
    public Timer getTimer();

    /**
     * Gets the game's grid.
     *
     * @return the game's grid.
     */
    public Grid getGrid();

    /**
     * Sets the game's grid.
     * @param g the new grid.
     */
    public void setGrid(Grid g);

    /**
     * Get the current Player.
     *
     * @return the current player.
     */
    public Player_INTERFACE getCurrentPlayer();

    /**
     * Calls the garbage collector. Used for memory optimisation.
     */
    public void clean();
}
