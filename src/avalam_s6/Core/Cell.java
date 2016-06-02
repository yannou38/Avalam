/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

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
