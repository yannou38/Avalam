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

import java.util.ArrayList;

/**
 * The Cell system. A Cell contains a Tower of pawns and a cell state.
 * @author Team 7
 */
public class Cell {

    private final ArrayList<Owner> contenu;
    private CellState etat;

    /**
     * Constructor.
     * @param size the size of the tower inside the cell.
     * @param owner the owner of the cell's content 
     * (see Owner Enum for details).
     */
    public Cell(int size, int owner) {
        this.contenu = new ArrayList<>();
        if (owner == 0) {
            this.contenu.add(Owner.NO_OWNER);
            if (size == 0) {
                this.etat = CellState.EMPTY;
            } else {
                this.etat = CellState.RESTRICTED;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (owner == 1) {
                    this.contenu.add(Owner.PLAYER_1);
                } else {
                    this.contenu.add(Owner.PLAYER_2);
                }
            }
            this.etat = CellState.TOWER;
        }
    }

    /**
     * Add a pawn on top of the tower.
     *
     * @param p the pawn added to the top of tower.
     */
    public void add(Owner p) {
        if (this.contenu.size() < 5) {
            this.contenu.add(p);
            if (this.contenu.size() == 5) {
                this.etat = CellState.FULL; // NORMAL
            }
            if (this.contenu.size() == 1) {
                this.etat = CellState.TOWER; // UNDO
            }
        }
    }

    /**
     * Remove the element at the i index of the tower (it doesn't remove all 
     * tower at once because the undo function wouldn't work otherwise).
     * @param i the index of the element to remove.
     * @return the owner of the pawn removed.
     */
    public Owner removeAt(int i) {
        if (this.contenu.size() > 0) {
            if (this.contenu.size() == 5) {
                this.etat = CellState.TOWER;
            }
            if (this.contenu.size() == 1) {
                this.etat = CellState.EMPTY;
            }
            return this.contenu.remove(i);
        }
        return null;
    }

    /**
     * Get the height of the tower.
     *
     * @return the height of the tower.
     */
    public int getSize() {
        return this.contenu.size();
    }

    /**
     * Get the owner of the tower.
     *
     * @return the owner of the tower.
     */
    public Owner getOwner() {
        if (!this.contenu.isEmpty()) {
            return this.contenu.get(getSize() - 1);
        }
        return Owner.NO_OWNER;
    }

    /**
     * Cell's state getter.
     *
     * @return the cell's state.
     */
    public CellState getState() {
        return etat;
    }
}
