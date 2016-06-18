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
package avalam.Core;

import avalam.Player.Player_INTERFACE;
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
